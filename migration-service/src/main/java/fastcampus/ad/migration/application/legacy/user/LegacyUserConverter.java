package fastcampus.ad.migration.application.legacy.user;

import fastcampus.ad.migration.application.LegacyConverter;
import fastcampus.ad.migration.domain.legacyad.user.LegacyUser;
import fastcampus.ad.migration.domain.recentad.user.RecentUser;
import org.springframework.stereotype.Component;

@Component
public class LegacyUserConverter implements LegacyConverter<LegacyUser, RecentUser> {

  @Override
  public RecentUser convert(LegacyUser legacyUser) {
    return RecentUser.migrated(legacyUser.getId(), legacyUser.getName(),
        legacyUser.getCreatedAt(), legacyUser.getUpdatedAt(), legacyUser.getDeletedAt());
  }
}
