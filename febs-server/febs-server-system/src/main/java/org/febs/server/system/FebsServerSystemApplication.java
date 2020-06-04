package org.febs.server.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)  //表示开启Spring Cloud Security权限注解
public class FebsServerSystemApplication {
    public static void main( String[] args ){
        SpringApplication.run(FebsServerSystemApplication.class, args);
    }
}
