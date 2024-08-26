package fastcampus.ad.migration.application.legacy.keyword;

import fastcampus.ad.migration.application.LegacyMigrationService;
import fastcampus.ad.migration.domain.legacyad.keyword.LegacyKeyword;
import fastcampus.ad.migration.domain.legacyad.keyword.LegacyKeywordRepository;
import fastcampus.ad.migration.domain.recentad.keyword.RecentKeyword;
import fastcampus.ad.migration.domain.recentad.keyword.RecentKeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyKeywordMigrationService extends
    LegacyMigrationService<LegacyKeyword, RecentKeyword> {

  public LegacyKeywordMigrationService(
      LegacyKeywordConverter converter,
      RecentKeywordRepository recentRepository,
      LegacyKeywordRepository legacyRepository) {
    super(converter, recentRepository, legacyRepository);
  }
}
