package fastcampus.ad.migration.domain.migration;

public class MigrationProgressedEvent extends MigrationUserEvent {

  public MigrationProgressedEvent(MigrationUser migrationUser) {
    super(migrationUser);
  }
}
