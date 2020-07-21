package org.febs.common.annotation;

import org.febs.common.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsServerProtectConfigure.class)
public @interface EnableFebsServerProtect {
}
