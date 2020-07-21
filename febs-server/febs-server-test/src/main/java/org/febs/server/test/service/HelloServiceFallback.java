package org.febs.server.test.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
/**
 * HelloServiceFallback实现FallbackFactory，泛型指定为上面定义的IHelloService。
 */
public class HelloServiceFallback implements FallbackFactory<IHelloService> {
    @Override
    public IHelloService create(Throwable throwable) {
        return new IHelloService() {
            @Override
            public String hello(String name) {
                log.error("调用febs-server-system服务出错", throwable);
                return "调用出错";
            }
        };
    }
}
