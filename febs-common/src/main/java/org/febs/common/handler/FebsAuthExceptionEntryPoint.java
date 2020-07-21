package org.febs.common.handler;

import org.febs.common.entity.FebsResponse;
import org.febs.common.utils.FebsUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FebsAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        FebsResponse febsResponse = new FebsResponse();
        FebsUtil.makeResponse(
                httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, febsResponse.message("token无效")
        );
    }
}
