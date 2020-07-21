package org.febs.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.febs.common.entity.FebsConstant;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class FebsGatewayRequestFilter extends ZuulFilter {
    @Override
    //对应Zuul生命周期的四个阶段：pre、post、route和error，我们要在请求转发出去前添加请求头，所以这里指定为pre
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    //过滤器的优先级，数字越小，优先级越高。PreDecorationFilter过滤器的优先级为5，所以我们可以指定为6让我们的过滤器优先级比它低
    public int filterOrder() {
        return 6;
    }

    @Override
    //方法返回boolean类型，true时表示是否执行该过滤器的run方法，false则表示不执行
    public boolean shouldFilter() {
        return true;
    }

    @Override
    //定义过滤器的主要逻辑。这里我们通过请求上下文RequestContext获取了转发的服务名称serviceId和请求对象HttpServletRequest，
    // 并打印请求日志。随后往请求上下文的头部添加了Key为ZuulToken，Value为febs:zuul:123456的信息。
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);

        byte[] token = Base64Utils.encode((FebsConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(FebsConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
