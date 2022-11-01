package com.dcu.wfd.spring;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 현 프로젝트의 Global 자원 관련 Bean 설정 Class
 *
 * @author 김건우
 * @since 2022.10.21
 * 
 */

@Configuration 
public class RootContext {
	/**
     * HikariCP(DBCP) Config(설정 세팅) 객체 Bean 설정
     *
     * @author 김건우
     * @since 2022.10.21
     */
    @Bean(name = "mainHikariConfig")
    public HikariConfig getMainHikariConfig () {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        hikariConfig.setUsername("user");
        hikariConfig.setPassword("user");
        return hikariConfig;
    }

    /**
     * HikariCP(DBCP) 객체 Bean 설정
     *
     * @author 김건우	
     * @since 2022.10.21
     */
    @Bean(name = "mainHikariDataSource")
    public HikariDataSource getMainHikariDataSource () {
        HikariDataSource hikariDataSource = new HikariDataSource(getMainHikariConfig());
        return hikariDataSource;
    }
    
  //Mybatis 동작 설정
    //mybatis-config.xml 작성 대신 Class 활용
    private class Configuration extends org.apache.ibatis.session.Configuration {
        private Configuration() {
            super();
            super.setCacheEnabled(true);//mapper 캐시 전역 사용여부
            super.setLazyLoadingEnabled(false);//mybatis 지연 로딩 사용여부
            super.setMultipleResultSetsEnabled(true);//한개의 구문에서 여러개의 ResultSet 허용 여부
            super.setUseColumnLabel(true);//컬럼명 대신 컬럼라벨 사용 여부
            super.setUseGeneratedKeys(false);//키 자동 생성
            super.setDefaultExecutorType(ExecutorType.SIMPLE);
            super.setDefaultStatementTimeout(25000);
            super.setCallSettersOnNulls(true);//조회 조회시, null값 무조건 허용

            //Mybatis SQL 작성시, 자주 활용하는 Class 별칭 세팅
            TypeAliasRegistry typeAliasRegistry = super.getTypeAliasRegistry();
            typeAliasRegistry.registerAlias("Test", com.dcu.wfd.common.vo.Test.class);
        }
    }
    //Mybatis 동작 설정 객체 생성
    private Configuration configuration = new Configuration();

    /**
     * Main
     * Mapper : SQL이 작성된 문서와, 작성된 SQL을 실행시킬 class(Mapper)를 매핑 시켜주기 위한 객체 Been 설정 (Main)
     *
     * @author 김건우
     * @since 2022.10.21
     */
    @Bean(name = "mainMapperScannerConfigurer")
    public MapperScannerConfigurer getMainMapperScannerConfigurer () {
        MapperScannerConfigurer mapperScannerConfigurer = null;
        mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("kr.co.edu");
        mapperScannerConfigurer.setAnnotationClass(org.apache.ibatis.annotations.Mapper.class);//Annotation을 지정해 줘야 service interface 를 Mapper라고 인식하지 않음 
        mapperScannerConfigurer.setSqlSessionTemplateBeanName("mainSqlSessionTemplate");
        return mapperScannerConfigurer;
    }

    /**
     * Main
     * Mybatis SqlSessionFactory Bean 설정
     *
     * @author 최정우
     * @since 2022.09.01
     */
    @Bean(name = "mainSqlSessionFactory")
    public SqlSessionFactory getMainSqlSessionFactory (ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getMainHikariDataSource());
        sqlSessionFactoryBean.setConfiguration(this.configuration);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/*-SQL.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * Main
     * Mybatis SqlSessionTemplate Bean 설정
     *
     * @author 최정우
     * @since 2022.09.01
     */
    @Bean(name = "mainSqlSessionTemplate")
    public SqlSessionTemplate getMainSqlSessionTemplate (ApplicationContext applicationContext) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(getMainSqlSessionFactory(applicationContext));
        return sqlSessionTemplate;
    }
}
