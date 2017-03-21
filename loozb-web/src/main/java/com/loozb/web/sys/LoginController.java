package com.loozb.web.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.loozb.core.base.AbstractController;
import com.loozb.core.support.Assert;
import com.loozb.core.util.CacheUtil;
import com.loozb.core.util.JsonUtil;
import com.loozb.core.util.WebUtil;
import com.loozb.core.utils.PasswordUtil;
import com.loozb.model.sys.SysResource;
import com.loozb.model.sys.SysUser;
import com.loozb.model.sys.ext.Authority;
import com.loozb.service.sys.SysAuthService;
import com.loozb.service.sys.SysResourceService;
import com.loozb.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 用户登录
 *
 * @Author： 龙召碧
 * @Date: Created in 2017-2-25 20:59
 */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class LoginController extends AbstractController<SysUserService> {

    @Autowired
    private SysAuthService sysAuthService;

    @Autowired
    private SysResourceService sysResourceService;
    // 登录
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Object login(ModelMap modelMap,
                        @ApiParam(required = true, value = "登录帐号") @RequestParam(value = "account") String account,
                        @ApiParam(required = true, value = "登录密码") @RequestParam(value = "password") String password) {
        Assert.notNull(account, "ACCOUNT");
        Assert.notNull(password, "PASSWORD");
        String error = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("available", "1");
        params.put("account", account);
        Page<?> pageInfo = service.query(params);
        if (pageInfo.getTotal() == 1) {
            SysUser user = (SysUser) pageInfo.getRecords().get(0);
            if(user == null) {
                error = "账号或者密码错误";
            }

            if("1".equals(user.getLocked())) {
                error = "该帐锁定";
            }

            //判断该用户是否已经登录，如果已经登录，则强制对方下线
            String token = WebUtil.getTokenByUsername(user.getUsername());

            if(StringUtils.isNotBlank(token)) {
                //假如token不为空，将其清空，使对方不能登录
                CacheUtil.getCache().del("REDIS_SESSION:TOKEN:" + token);
                CacheUtil.getCache().del("REDIS_SESSION:USERNAME:" + user.getUsername());
            }

            if(user.getPassword().equals(PasswordUtil.decryptPassword(password, user.getSalt()))) {
                //获取角色信息
                Set<String> roles = (Set<String>)sysAuthService.findRoles(user.getId());

                //获取权限信息
                Set<String> permissions = (Set<String>)sysAuthService.findPermissions(user.getId());

                //获取资源信息
                List<SysResource> menus = (List<SysResource>)sysResourceService.getMenus(user.getId());

                // 生成token
                String accessToken = UUID.randomUUID().toString();
                user.setPassword(null);
                user.setSalt(null);

                CacheUtil.getCache().set("REDIS_SESSION:TOKEN:" + accessToken, JsonUtil.objectToJson(user), 1800);
                CacheUtil.getCache().set("REDIS_SESSION:USERNAME:" + user.getUsername(), accessToken, 1800);
                CacheUtil.getCache().set("REDIS_SESSION:USERID:" + user.getUsername(), user.getId(), 1800);
                return setSuccessModelMap(modelMap, new Authority(accessToken, roles, permissions, menus,user));
            }

            logger.warn("USER [{}] PASSWORD IS WRONG: {}", user.getUsername(), password);
            return null;
        } else {
            logger.warn("No user: {}", account);
            return null;
        }
    }
}
