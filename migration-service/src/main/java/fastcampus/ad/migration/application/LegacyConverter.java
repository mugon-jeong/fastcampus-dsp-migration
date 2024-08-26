package fastcampus.ad.migration.application;


import fastcampus.ad.migration.domain.legacyad.DeletableEntity;
import fastcampus.ad.migration.domain.recentad.MigratedEntity;

public interface LegacyConverter<Legacy extends DeletableEntity, Recent extends MigratedEntity> {

  Recent convert(Legacy legacy);
}
