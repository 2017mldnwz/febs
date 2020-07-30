package org.febs.server.system.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.febs.server.system.properties.FebsServerSystemProperties;
import org.febs.server.system.properties.FebsSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2  //表示开启Swagger功能
public class FebsWebConfigure {

    @Autowired
    private FebsServerSystemProperties properties;

    @Bean
    //MyBatis Plus分页插件配置
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        //此类的作用是解析 delete update 语句，防止小白或者恶意进行delete update 全表操作。
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    /**
     * securitySchemes：用于配置安全策略，比如配置认证模型，scope等内容；
     * securityContexts：用于配置安全上下文，只有配置了安全上下文的接口才能使用令牌获取资源。
     * @return
     */
    @Bean
    public Docket swaggerApi() {
        FebsSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger))
                .securitySchemes(Collections.singletonList(securityScheme(swagger)))
                .securityContexts(Collections.singletonList(securityContext(swagger)));
    }

    private ApiInfo apiInfo(FebsSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

    /**
     * 我们通过OAuthBuilder对象构建了安全策略，主要配置了认证类型为ResourceOwnerPasswordCredentialsGrant（即密码模式），
     * 认证地址为http://localhost:8301/auth/oauth/token（即通过网关转发到认证服务器），scope为test，和febs-auth模块里定义的一致。
     * 这个安全策略我们将其命名为febs_oauth_swagger。
     * @param swagger
     * @return
     */
    private SecurityScheme securityScheme(FebsSwaggerProperties swagger) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());

        return new OAuthBuilder()
                .name(swagger.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    /**
     * 我们通过febs_oauth_swagger名称关联了上面定义的安全策略，并且通过forPaths(PathSelectors.any())设置所有API接口都用这个安全上下文。
     * @param swagger
     * @return
     */
    private SecurityContext securityContext(FebsSwaggerProperties swagger) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(FebsSwaggerProperties swagger) {
        return new AuthorizationScope[]{
                new AuthorizationScope(swagger.getScope(), "")
        };
    }
}
