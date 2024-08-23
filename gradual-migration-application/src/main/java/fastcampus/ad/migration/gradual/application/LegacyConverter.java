package fastcampus.ad.migration.gradual.application;

import fastcampus.ad.migration.gradual.domain.legacyad.DeletableEntity;
import fastcampus.ad.migration.gradual.domain.recentad.MigratedEntity;

public interface LegacyConverter<Legacy extends DeletableEntity, Recent extends MigratedEntity> {

  Recent convert(Legacy legacy);
}
