package com.loozb.web.sys;

import com.loozb.core.base.AbstractController;
import com.loozb.core.support.Assert;
import com.loozb.service.sys.SysAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限信息表 前端控制器
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
@RestController
@Api(value = "授权管理", description = "授权管理")
@RequestMapping(value = "/auths")
public class SysAuthController extends AbstractController<SysAuthService> {

    /**
     * 用户授权
     * @param modelMap
     * @param id
     * @return
     */
    @PostMapping("allot/{id}")
    @ApiOperation(value = "用户授权")
    @RequiresPermissions("user:allot")
    public Object allot(ModelMap modelMap, @PathVariable Long id, String roleIds) {
        Assert.notNull(id, "UISERID");
        service.allot(id, roleIds);
        return setSuccessModelMap(modelMap);
    }
}
