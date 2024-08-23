package fastcampus.ad.migration.gradual.message;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LegacyDomainMessageHandler {

  @Bean
  public Consumer<LegacyDomainMessage> legacyConsumer() {
    return message -> {
      System.out.println("Received message: " + message);
    };
  }
}
