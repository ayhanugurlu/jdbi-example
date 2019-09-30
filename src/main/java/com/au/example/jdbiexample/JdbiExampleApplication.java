package com.au.example.jdbiexample;

import com.au.example.jdbiexample.repository.model.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@SpringBootApplication
public class JdbiExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbiExampleApplication.class, args);
    }




    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        // JDBI wants to control the Connection wrap the datasource in a proxy
        // That is aware of the Spring managed transaction
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(dataSourceProxy);
        jdbi.installPlugins();
        jdbi.installPlugin(new H2DatabasePlugin());

        jdbi.registerRowMapper(User.class, ConstructorMapper.of(User.class));

        return jdbi;
    }

}
