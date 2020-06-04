package org.febs.auth.config;

import org.febs.auth.service.FebsUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用于处理/oauth开头的请求，Spring Cloud OAuth内部定义的获取令牌，刷新令牌的请求地址都是以/oauth/开头的，也就是说FebsSecurityConfigure用于处理和令牌相关的请求
 * @author 王哲
 *
 */
@Order(2)  //WebSecurityConfigurerAdapterm默认优先级100
@EnableWebSecurity  
public class FebsSecurityConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
    private FebsUserDetailService userDetailService;
	
	/**
	 * BCryptPasswordEncoder的特点就是，对于一个相同的密码，每次加密出来的加密串都不同,但是其中的hash值相同，所以是通过hash值来判断密码是否正确
	 * @return
	 */
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	//auth的账号密码模式需要
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/oauth/**")  //FebsSecurityConfigure安全配置类只对/oauth/开头的请求有效
            .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()  ///oauth/开头的请求需要认证
            .and()
                .csrf().disable();
    }
	
	//把从http获取到的用户信息和userDetailService查询到的用户信息作比较
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
	
}
