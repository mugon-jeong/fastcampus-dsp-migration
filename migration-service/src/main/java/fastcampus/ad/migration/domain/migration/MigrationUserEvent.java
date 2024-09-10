package fastcampus.ad.migration.domain.migration;

public class MigrationUserEvent {

  protected MigrationUser migrationUser;

  public MigrationUserEvent(MigrationUser migrationUser) {
    this.migrationUser = migrationUser;
  }

  public Long getUserId() {
    return migrationUser.getId();
  }

  public MigrationUserStatus getStatus() {
    return migrationUser.getStatus();
  }

  public MigrationUserStatus getPrevStatus() {
    return migrationUser.getPrevStatus();
  }

}
