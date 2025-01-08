package com.project.transportation.config;

import com.project.transportation.repository.CompanyRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;

@TestConfiguration
@Import(CompanyRepository.class)
@ComponentScan("com.project.transportation")  // Scan the necessary packages
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)  // Use H2 in-memory DB
                .build();
    }
}
