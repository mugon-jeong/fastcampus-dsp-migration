package fastcampus.ad.migration.gradual.application.legacy.adgroup;

import fastcampus.ad.migration.gradual.application.LegacyMigrationService;
import fastcampus.ad.migration.gradual.domain.legacyad.adgroup.LegacyAdGroup;
import fastcampus.ad.migration.gradual.domain.legacyad.adgroup.LegacyAdGroupRepository;
import fastcampus.ad.migration.gradual.domain.recentad.campaign.RecentCampaign;
import fastcampus.ad.migration.gradual.domain.recentad.campaign.RecentCampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyAdGroupMigrationService extends
    LegacyMigrationService<LegacyAdGroup, RecentCampaign> {

  public LegacyAdGroupMigrationService(
      LegacyAdGroupConverter converter,
      RecentCampaignRepository recentRepository,
      LegacyAdGroupRepository legacyRepository) {
    super(converter, recentRepository, legacyRepository);
  }
}
