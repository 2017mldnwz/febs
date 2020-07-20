package org.febs.auth;

import org.febs.common.annotation.EnableFebsLettuceRedis;
import org.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@FebsCloudApplication
@EnableFebsLettuceRedis
@MapperScan("org.febs.auth.mapper")  //作用为将路径下的Mapper类都注册到IOC容器中
public class FebsAuthApplication {

	public static void main(String[] args) {
        SpringApplication.run(FebsAuthApplication.class, args);
    }
}
