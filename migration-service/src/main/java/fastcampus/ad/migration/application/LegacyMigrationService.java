package fastcampus.ad.migration.application;

import fastcampus.ad.migration.gradual.domain.legacyad.DeletableEntity;
import fastcampus.ad.migration.gradual.domain.recentad.MigratedEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

@Slf4j
public abstract class LegacyMigrationService<Legacy extends DeletableEntity, Recent extends MigratedEntity> implements
    MigrationService {

  protected LegacyConverter<Legacy, Recent> converter;
  protected CrudRepository<Recent, Long> recentRepository;
  protected CrudRepository<Legacy, Long> legacyRepository;

  public LegacyMigrationService(LegacyConverter<Legacy, Recent> converter,
      CrudRepository<Recent, Long> recentRepository,
      CrudRepository<Legacy, Long> legacyRepository) {
    this.converter = converter;
    this.recentRepository = recentRepository;
    this.legacyRepository = legacyRepository;
  }

  @Override
  public boolean migrate(Long id) {
    try {
      migrate(findLegacy(id));
      return true;
    } catch (EntityNotFoundException e) {
      log.error("migration error", e);
      return false;
    }
  }

  protected void migrate(Legacy legacy) {
    if (legacy.isDeleted()) {
      deleteRecent(legacy.getId());
    } else {
      saveRecent(convert(legacy));
    }
  }

  private void deleteRecent(Long id) {
    recentRepository.findById(id)
        .ifPresent(recent -> recentRepository.delete(recent));
  }

  private void saveRecent(Recent recent) {
    recentRepository.save(recent);
  }

  private Recent convert(Legacy legacy) {
    return converter.convert(legacy);
  }

  private Legacy findLegacy(Long id) {
    return legacyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
