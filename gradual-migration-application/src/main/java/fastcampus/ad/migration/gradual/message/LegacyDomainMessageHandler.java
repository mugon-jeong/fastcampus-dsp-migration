package fastcampus.ad.migration.gradual.message;

import fastcampus.ad.migration.application.dispatcher.MigrationDispatcher;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LegacyDomainMessageHandler {

  private final MigrationDispatcher dispatcher;

  @Bean
  public Consumer<LegacyDomainMessage> legacyConsumer() {
    return message -> {
      System.out.println("Received message: " + message);
      dispatcher.dispatch(message.aggregateId(), message.aggregateType());
    };
  }
}
