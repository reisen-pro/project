package com.project.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.xxx.xxx.xxx", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    // 将这个对象放入Spring容器中
    @Bean(name = "dataSource")
    // 读取application.properties中的配置参数映射成为一个对象
    // prefix表示参数的前缀\
    @Primary
    // 表示这个数据源是默认数据源
    //@ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory")
    // 表示这个数据源是默认数据源
    @Primary
    // @Qualifier表示查找Spring容器中名字为test1DataSource的对象
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/xxx/*.xml"));
        return bean.getObject();
    }

    @Bean("sqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate test1SqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

}
