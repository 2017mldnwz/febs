package org.febs.auth.handler;

import org.febs.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
/**
 * 当前微服务系统独有的异常类型捕获可以在GlobalExceptionHandler中定义
 */
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
