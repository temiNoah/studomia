package com.studomia.studomia.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class StudomiaDatasource {

    @Value("${diverClassName}")
    private String driverClassName;

    @Value("${data.source}")
    private String hostName;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${max.pool.size}")
    private String maxPoolSize;

    @Bean(name="studomiaDatasource")
    public DataSource setUpDataSource()
    {
        HikariDataSource ds ;
        HikariConfig config= new HikariConfig()   ;

        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(hostName);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(Integer.parseInt(maxPoolSize));

        ds = new HikariDataSource(config);

        return ds;

    }
}
