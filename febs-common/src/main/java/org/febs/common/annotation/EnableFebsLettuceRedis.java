package org.febs.common.annotation;

import org.febs.common.configure.FebsLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})  //注解的作用目标:接口、类、枚举
@Retention(RetentionPolicy.RUNTIME)  // 注解的保留位置:注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented //说明该注解将被包含在javadoc中
@Import(FebsLettuceRedisConfigure.class)
public @interface EnableFebsLettuceRedis {
}
