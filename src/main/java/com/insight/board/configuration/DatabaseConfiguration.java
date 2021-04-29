package com.insight.board.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
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

    private final ApplicationContext applicationContext;

    public DatabaseConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /*
      application.properties에 spring.datasource.hikari로 prifix를 정해놓아서
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    /*
     properties 파일에 설정해준 mapUnderscoreToCamelCase을 이용하여 빈생성
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig(){
        return new org.apache.ibatis.session.Configuration();
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
    //젠킨스 테스트
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
        //spring-mybatis에서는 SqlsessionFactory를 생성하기 위해 SqlSessionFactoryBean을 사용한다.
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //마이바티스 매버 파일의 위치 설정
        //classpath = resources 폴더를 의미
        //mapper/**/ = mapper폴더 밑의 모든폴더
        //sql-*.xml = 이름이sql-로 시작하고 확장자가 xml인 모든파일
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
        sqlSessionFactoryBean.setConfiguration(mybatisConfig()); //위에 선언해준 mybatisConfig 를 셋팅해준다.
        return sqlSessionFactoryBean.getObject();
    }

    /*
      template를 빈으로 등록하고 추후에 연결하는 부분에서 사용할 것
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
