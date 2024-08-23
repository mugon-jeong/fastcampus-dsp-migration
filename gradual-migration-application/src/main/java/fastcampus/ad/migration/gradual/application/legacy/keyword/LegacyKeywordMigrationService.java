package fastcampus.ad.migration.gradual.application.legacy.keyword;

import fastcampus.ad.migration.gradual.application.LegacyMigrationService;
import fastcampus.ad.migration.gradual.domain.legacyad.keyword.LegacyKeyword;
import fastcampus.ad.migration.gradual.domain.legacyad.keyword.LegacyKeywordRepository;
import fastcampus.ad.migration.gradual.domain.recentad.keyword.RecentKeyword;
import fastcampus.ad.migration.gradual.domain.recentad.keyword.RecentKeywordRepository;
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
