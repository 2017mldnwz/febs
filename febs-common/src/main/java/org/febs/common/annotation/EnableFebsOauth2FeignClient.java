package org.febs.common.annotation;

import org.febs.common.configure.FebsOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsOAuth2FeignConfigure.class)
public @interface EnableFebsOauth2FeignClient {
}
