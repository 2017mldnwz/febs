package org.febs.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.febs.common.entity.FebsResponse;
import org.febs.common.entity.QueryRequest;
import org.febs.common.entity.system.SystemUser;
import org.febs.common.exception.FebsException;
import org.febs.common.utils.FebsUtil;
import org.febs.server.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
/**
 * @Valid对应实体对象传参时的参数校验
 * @Validated对应普通参数的校验
 */
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public FebsResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(userService.findUserDetail(user, queryRequest));
        return new FebsResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void addUser(@Valid SystemUser user) throws FebsException {
        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws FebsException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    /**
     * @Validated的作用是让@NotBlank注解生效
     * {required}提示内容需要在febs-server-system模块的resources路径下创建ValidationMessages.properties配置文件
     * ValidationMessages.properties中的内容为中文转Unicode后的值
     */
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FebsException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}