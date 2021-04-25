package com.insight.board.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/*
  application.properties를 사용할 수 있게 설정 파일의 위치를 지정
  @PropertySource를 이용하여 다른 설정파일도 사용가능함. 현재는 1개만..
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    /*
      application.properties에 spring.datasource.hikari로 prifix를 정해놓아서
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    /*
    앞에서 만든 히카리CP 설정 파일을 이용해 DB와 연결하는 dataSource 생성
     */
    @Bean
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println("Datasource conntection:"+dataSource.toString());
        return dataSource;
    }


}
