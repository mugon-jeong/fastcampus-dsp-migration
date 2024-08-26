package fastcampus.ad.migration.domain.migration;

public class MigrationAgreedEvent extends MigrationUserEvent {

  public MigrationAgreedEvent(MigrationUser migrationUser) {
    super(migrationUser);
  }
}
