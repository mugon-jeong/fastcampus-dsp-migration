package fastcampus.ad.migration.batch.application.message;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationMessageConsumer {

  @Bean
  public Consumer<MigrationUserMessage> migrationUserConsumer() {
    return message -> {
      log.info("migration user consumer: {}", message);
    };
  }
}
