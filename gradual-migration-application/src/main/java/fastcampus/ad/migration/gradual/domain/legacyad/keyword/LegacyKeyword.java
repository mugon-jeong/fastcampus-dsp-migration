package fastcampus.ad.migration.gradual.domain.legacyad.keyword;

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
public class LegacyKeyword extends AbstractAggregateRoot<LegacyKeyword> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;
  private Long adGroupId;
  private Long userId;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;
}
