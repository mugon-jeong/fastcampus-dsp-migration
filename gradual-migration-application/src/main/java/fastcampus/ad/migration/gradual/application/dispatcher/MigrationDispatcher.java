package fastcampus.ad.migration.gradual.application.dispatcher;

import fastcampus.ad.migration.gradual.application.MigrationService;
import fastcampus.ad.migration.gradual.application.legacy.adgroup.LegacyAdGroupMigrationService;
import fastcampus.ad.migration.gradual.application.legacy.campaign.LegacyCampaignMigrationService;
import fastcampus.ad.migration.gradual.application.legacy.keyword.LegacyKeywordMigrationService;
import fastcampus.ad.migration.gradual.application.legacy.user.LegacyUserMigrationService;
import fastcampus.ad.migration.gradual.message.AggregateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationDispatcher {

  private final LegacyUserMigrationService legacyUserMigrationService;
  private final LegacyCampaignMigrationService legacyCampaignMigrationService;
  private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;
  private final LegacyKeywordMigrationService legacyKeywordMigrationService;

  public boolean dispatch(Long aggregateId, AggregateType aggregateType) {
    return migrate(aggregateId, aggregateType);
  }

  private boolean migrate(Long aggregateId, AggregateType aggregateType) {
    MigrationService service = switch (aggregateType) {
      case USER -> legacyUserMigrationService;
      case CAMPAIGN -> legacyCampaignMigrationService;
      case ADGROUP -> legacyAdGroupMigrationService;
      case KEYWORD -> legacyKeywordMigrationService;
    };

    boolean result = service.migrate(aggregateId);
    logMigrationResult(result, aggregateType, aggregateId);
    return result;
  }

  private void logMigrationResult(boolean result, AggregateType aggregateType, Long aggregateId) {
    if (result) {
      log.info("{}", LegacyMigrationLog.success(aggregateType, aggregateId));
    } else {
      log.error("{}", LegacyMigrationLog.fail(aggregateType, aggregateId));
    }
  }

}
