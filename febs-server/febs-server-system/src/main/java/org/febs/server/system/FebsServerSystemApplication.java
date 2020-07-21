package org.febs.server.system;

import org.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)  //表示开启Spring Cloud Security权限注解
@FebsCloudApplication
@MapperScan("org.febs.server.system.mapper")
@EnableTransactionManagement
public class FebsServerSystemApplication {
    public static void main( String[] args ){
        SpringApplication.run(FebsServerSystemApplication.class, args);
    }
}
