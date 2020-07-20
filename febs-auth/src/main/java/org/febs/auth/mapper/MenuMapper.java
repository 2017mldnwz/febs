package org.febs.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.febs.common.entity.system.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    //通过用户名查找用户权限集合
    List<Menu> findUserPermissions(String Username);
}
