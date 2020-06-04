package org.febs.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 认证服务器本身也可以对外提供REST服务，比如通过Token获取当前登录用户信息，注销当前Token等，所以它也是一台资源服务器
 * 资源服务器
 * ResourceServerConfigurerAdapter 默认优先级3
 * 用于处理非/oauth/开头的请求，其主要用于资源的保护，客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源
 * @author 王哲
 *
 */
@Configuration
@EnableResourceServer
public class FebsResourceServerConfigure extends ResourceServerConfigurerAdapter{

	@Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }
}
