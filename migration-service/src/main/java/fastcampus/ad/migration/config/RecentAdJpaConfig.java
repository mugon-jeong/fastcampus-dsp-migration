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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackages = {"fastcampus.ad.migration.domain.recentad",
        "fastcampus.ad.migration.domain.migration"},
    entityManagerFactoryRef = "recentAdEntityManagerFactory",
    transactionManagerRef = "recentAdTransactionManager"
)
@EnableTransactionManagement
public class RecentAdJpaConfig {

  @Primary
  @Bean("recentAdDataSource")
  @ConfigurationProperties(prefix = "spring.jpa.recent-ad.hikari")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean("recentAdJpaProperties")
  @ConfigurationProperties(prefix = "spring.jpa.recent-ad.properties")
  public Properties recentAdJpaProperties() {
    return new Properties();
  }

  @Primary
  @Bean("recentAdEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean recentAdEntityManagerFactory(
      @Qualifier("recentAdDataSource") DataSource dataSource,
      @Qualifier("recentAdJpaProperties") Properties jpaProperties,
      EntityManagerFactoryBuilder builder
  ) {
    LocalContainerEntityManagerFactoryBean factory = builder
        .dataSource(dataSource)
        .packages("fastcampus.ad.migration.domain.recentad",
            "fastcampus.ad.migration.domain.migration")
        .build();

    factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    factory.setJpaProperties(jpaProperties);
    return factory;
  }

  @Primary
  @Bean("recentAdTransactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("recentAdEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
