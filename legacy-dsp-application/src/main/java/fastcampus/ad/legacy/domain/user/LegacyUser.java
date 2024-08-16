package fastcampus.ad.legacy.domain.user;

import fastcampus.ad.legacy.domain.user.event.LegacyUserCreateEvent;
import fastcampus.ad.legacy.domain.user.event.LegacyUserDeleteEvent;
import fastcampus.ad.legacy.domain.user.event.LegacyUserNameUpdateEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@NoArgsConstructor
@Getter
/**
 * AbstractAggregateRoot registerEvent 메서드로 이벤트 등록
 *  - 이벤트 등록 시, domain event 배열에 저장됨
 *  - domain event 들은 repository에 save가 일어날때 event가 실행됨
 *  - event가 발행되면 clear 함수를 통해 초기화됨
 */
public class LegacyUser extends AbstractAggregateRoot<LegacyUser> {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public LegacyUser(String name, LocalDateTime createdAt) {
    this.name = name;
    this.createdAt = createdAt;
    this.updatedAt = createdAt;
    this.deletedAt = null;
    registerEvent(new LegacyUserCreateEvent(this));
  }

  public static LegacyUser of(String name) {
    return new LegacyUser(name, LocalDateTime.now());
  }

  public void update(String name) {
    this.name = name;
    this.updatedAt = LocalDateTime.now();
    registerEvent(new LegacyUserNameUpdateEvent(this));
  }

  public void delete() {
    this.deletedAt = LocalDateTime.now();
    registerEvent(new LegacyUserDeleteEvent(this));
  }
}
