package org.febs.common.selector;

import org.febs.common.configure.FebsAuthExceptionConfigure;
import org.febs.common.configure.FebsOAuth2FeignConfigure;
import org.febs.common.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class FebsCloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                FebsAuthExceptionConfigure.class.getName(),  //认证类型异常翻译。
                FebsOAuth2FeignConfigure.class.getName(),  //开启带令牌的Feign请求，避免微服务内部调用出现401异常；
                FebsServerProtectConfigure.class.getName() //开启微服务防护，避免客户端绕过网关直接请求微服务
        };
    }
}
