package org.febs.auth.properties;

import lombok.Data;

/**
 * 在认证服务器配置类FebsAuthorizationServerConfigure里使用硬编码的形式配置了client_id，client_secret等信息。
 * 硬编码的形式不利于代码维护和升级，所以我们需要将它改造为可配置的方式。
 * @author 王哲
 *
 */
@Data
public class FebsClientsProperties {

	private String client;  //对应client_id
    private String secret;  //对应client_secret
    private String grantType = "password,authorization_code,refresh_token";  //对应当前令牌支持的认证类型
    private String scope = "all";  //对应认证范围
    
}
