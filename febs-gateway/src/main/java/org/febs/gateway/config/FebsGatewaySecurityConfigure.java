package org.febs.gateway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 我们需要定义一个自己的WebSecurity配置类，来覆盖默认的。这里主要是关闭了csrf功能，否则会报csrf相关异常
 * @author 王哲
 *
 */
@EnableWebSecurity
public class FebsGatewaySecurityConfigure extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
