package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.Data;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Configuration
public class DatasourceConfig {
    /**
     * 创建一个主数据源的实例
     */
    @Primary
    @Bean(value = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从数据源1
     */
    @Bean(value = "slaver1")
    @ConfigurationProperties(prefix = "spring.datasource.slaver1")
    public DataSource slaver1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "dataSource")
    public DataSourceRoutingDataSource dataSource() {
        DataSourceRoutingDataSource dataSource = new DataSourceRoutingDataSource();
        // 数据源
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("master", master());
        dataSources.put("slaver1", slaver1());
        dataSource.setTargetDataSources(dataSources);
        dataSource.setDefaultTargetDataSource(master());
        // 设置主数据源的键值；
        Set<Object> masterKeys = new HashSet<>();
        masterKeys.add("master");
        dataSource.setMasterKeys(masterKeys);
        // 设置从数据源的键值；
        Set<Object> slaverKeys = new HashSet<>();
        slaverKeys.add("slaver1");
        dataSource.setSlaverKeys(slaverKeys);
        return dataSource;
    }

    /**
     * SqlSessionFactoryBean实例配置
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");
        factoryBean.setMapperLocations(resources);
        factoryBean.setTypeAliasesPackage("com.example.demo.domain");
        return factoryBean;
    }

}
