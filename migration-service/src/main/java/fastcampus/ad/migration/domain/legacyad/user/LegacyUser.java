package fastcampus.ad.migration.domain.legacyad.user;

import fastcampus.ad.migration.domain.legacyad.DeletableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
/**
 * AbstractAggregateRoot registerEvent 메서드로 이벤트 등록
 *  - 이벤트 등록 시, domain event 배열에 저장됨
 *  - domain event 들은 repository에 save가 일어날때 event가 실행됨
 *  - event가 발행되면 clear 함수를 통해 초기화됨
 */
public class LegacyUser implements DeletableEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

}
