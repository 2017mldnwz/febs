package org.febs.common.annotation;

import org.febs.common.selector.FebsCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsCloudApplicationSelector.class)
public @interface FebsCloudApplication {
}
