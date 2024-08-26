package fastcampus.ad.migration.application.user;

import fastcampus.ad.migration.domain.migration.MigrationUser;
import fastcampus.ad.migration.domain.migration.MigrationUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MigrationUserService {

  private final MigrationUserRepository repository;

  @Transactional
  public MigrationUserResult agree(Long userId) {
    repository.findById(userId).ifPresent(migrationUser -> {
      throw new AlreadyAgreedException(String.format("User [ID: %d] already agreed", userId));
    });
    return MigrationUserResult.from(repository.save(MigrationUser.agreed(userId)));
  }

  private MigrationUser find(Long userId) {
    return repository.findById(userId)
        .orElseThrow(EntityNotFoundException::new);
  }

  public MigrationUserResult findById(Long userId) {
    return MigrationUserResult.from(find(userId));
  }

  public boolean isDisagreed(Long migrationUserId) {
    return repository.findById(migrationUserId).isEmpty();
  }
}
