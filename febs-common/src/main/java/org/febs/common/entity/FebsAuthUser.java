package org.febs.common.entity;

import java.io.Serializable;

import lombok.Data;

@Data  //注解为lombok注解，用于自动生成get，set方法
public class FebsAuthUser implements Serializable{

	private static final long serialVersionUID = 1L;

	private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
    
}
