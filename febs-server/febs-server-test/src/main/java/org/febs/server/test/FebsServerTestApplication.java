package org.febs.server.test;

import org.febs.common.annotation.EnableFebsOauth2FeignClient;
import org.febs.common.annotation.FebsCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
@FebsCloudApplication
@EnableFebsOauth2FeignClient
public class FebsServerTestApplication {
    public static void main( String[] args ){
    	SpringApplication.run(FebsServerTestApplication.class, args);
    }
}
