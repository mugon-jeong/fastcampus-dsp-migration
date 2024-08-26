package fastcampus.ad.migration.application.legacy.user;

import fastcampus.ad.migration.application.LegacyMigrationService;
import fastcampus.ad.migration.domain.legacyad.user.LegacyUser;
import fastcampus.ad.migration.domain.legacyad.user.LegacyUserRepository;
import fastcampus.ad.migration.domain.recentad.user.RecentUser;
import fastcampus.ad.migration.domain.recentad.user.RecentUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyUserMigrationService extends LegacyMigrationService<LegacyUser, RecentUser> {

  public LegacyUserMigrationService(
      LegacyUserConverter converter,
      RecentUserRepository recentRepository,
      LegacyUserRepository legacyRepository) {
    super(converter, recentRepository, legacyRepository);
  }
}
