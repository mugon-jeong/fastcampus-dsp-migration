package fastcampus.ad.legacy.domain.user.event;

import fastcampus.ad.legacy.domain.user.LegacyUser;
import java.time.LocalDateTime;

public class LegacyUserCreateEvent extends LegacyUserEvent {

  public LegacyUserCreateEvent(LegacyUser legacyUser) {
    super(legacyUser);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyUser.getCreatedAt();
  }
}
