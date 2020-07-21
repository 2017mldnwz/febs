package org.febs.common.configure;

import org.febs.common.interceptor.FebsServerProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class FebsServerProtectConfigure implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor handlerInterceptor;

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)  //表示当IOC容器里没有PasswordEncoder类型的Bean的时候，则将BCryptPasswordEncoder注册到IOC容器中
    /**
     * BCryptPasswordEncoder的特点就是，对于一个相同的密码，每次加密出来的加密串都不同,但是其中的hash值相同，所以是通过hash值来判断密码是否正确
     * @return
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //让该过滤器生效
    //网关拦截器，目的是让没有通过网关访问的路径不通过
    public HandlerInterceptor febsServerProtectInterceptor() {
        return new FebsServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor);
    }
}
