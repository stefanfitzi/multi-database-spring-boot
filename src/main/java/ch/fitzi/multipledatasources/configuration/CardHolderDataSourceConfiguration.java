package ch.fitzi.multipledatasources.configuration;

import ch.fitzi.multipledatasources.model.cardholder.CardHolder;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "ch.fitzi.multipledatasources.repository.cardholder",
        entityManagerFactoryRef = "cardHolderEntityManagerFactory",
        transactionManagerRef= "cardHolderTransactionManager")
public class CardHolderDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.cardholder")
    public DataSourceProperties cardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.cardholder.configuration")
    public DataSource cardholderDataSource() {
        return cardHolderDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "cardHolderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        return builder
                .dataSource(cardholderDataSource())
                .packages(CardHolder.class)
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager cardHolderTransactionManager(
            final @Qualifier("cardHolderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory) {
        return new JpaTransactionManager(cardHolderEntityManagerFactory.getObject());
    }
}
