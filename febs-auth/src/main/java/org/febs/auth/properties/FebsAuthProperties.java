package org.febs.auth.properties;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:febs-auth.properties"})
@ConfigurationProperties(prefix = "febs.auth")
/**
 * 把对应配置文件中的属性映射到此类中
 */
public class FebsAuthProperties {

	private FebsClientsProperties[] clients = {};  //因为一个认证服务器可以根据多种Client来发放对应的令牌，所以这个属性使用的是数组形式
    private int accessTokenValiditySeconds = 60 * 60 * 24;  //指定access_token的有效时间,默认值是60 * 60 * 24
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;  //指定refresh_token的有效时间,默认值是60 * 60 * 24 * 7
    // 免认证路径
    private String anonUrl;

    //验证码配置类
    private FebsValidateCodeProperties code = new FebsValidateCodeProperties();
    
}
