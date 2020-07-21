package org.febs.gateway.filter;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.febs.common.entity.FebsResponse;
import org.febs.common.utils.FebsUtil;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
/**
 * 在该过滤器中，我们可以通过RequestContext获取到当前请求上下文，通过请求上下文可以获取到当前请求的服务名称serviceId和当前请求的异常对象ExceptionHolder等信息。
 * 通过异常对象我们可以继续获取到异常内容，根据不同的异常内容我们可以自定义想要的响应。
 */
public class FebsGatewayErrorFilter extends SendErrorFilter {
    @Override
    public Object run() {
        try {
            FebsResponse febsResponse = new FebsResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            febsResponse = resolveExceptionMessage(message, serviceId, febsResponse);

            HttpServletResponse response = ctx.getResponse();
            FebsUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, febsResponse
            );
            log.error("Zull sendError：{}", febsResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private FebsResponse resolveExceptionMessage(String message, String serviceId, FebsResponse febsResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return febsResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return febsResponse.message(serviceId + "服务不可用");
        }
        return febsResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
