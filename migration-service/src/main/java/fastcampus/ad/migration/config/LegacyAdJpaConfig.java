package fastcampus.ad.migration.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "fastcampus.ad.migration.domain.legacyad",
    entityManagerFactoryRef = "legacyAdEntityManagerFactory",
    transactionManagerRef = "legacyAdTransactionManager"
)
public class LegacyAdJpaConfig {

  @Bean("legacyAdDataSource")
  @ConfigurationProperties(prefix = "spring.jpa.legacy-ad.hikari")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean("legacyAdJpaProperties")
  @ConfigurationProperties(prefix = "spring.jpa.legacy-ad.properties")
  public Properties legacyAdJpaProperties() {
    return new Properties();
  }

  @Bean("legacyAdEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean legacyAdEntityManagerFactory(
      @Qualifier("legacyAdDataSource") DataSource dataSource,
      @Qualifier("legacyAdJpaProperties") Properties jpaProperties,
      EntityManagerFactoryBuilder builder
  ) {
    LocalContainerEntityManagerFactoryBean factory = builder
        .dataSource(dataSource)
        .packages("fastcampus.ad.migration.domain.legacyad")
        .build();

    factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    factory.setJpaProperties(jpaProperties);
    return factory;
  }

  @Bean("legacyAdTransactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("legacyAdEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
