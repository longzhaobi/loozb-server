package com.loozb.web.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.loozb.core.Constants;
import com.loozb.core.base.AbstractController;
import com.loozb.core.bind.CurrentUser;
import com.loozb.core.bind.Token;
import com.loozb.core.support.Assert;
import com.loozb.core.support.HttpCode;
import com.loozb.core.util.CacheUtil;
import com.loozb.core.util.JsonUtils;
import com.loozb.core.util.ParamUtil;
import com.loozb.core.util.WebUtil;
import com.loozb.core.utils.PasswordUtil;
import com.loozb.model.sys.SysResource;
import com.loozb.model.sys.SysSession;
import com.loozb.model.SysUser;
import com.loozb.model.sys.ext.Authority;
import com.loozb.service.sys.SysAuthService;
import com.loozb.service.sys.SysResourceService;
import com.loozb.service.sys.SysSessionService;
import com.loozb.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private SysSessionService sysSessionService;

    // 登录
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Object login(ModelMap modelMap, HttpServletRequest request, 
                        @ApiParam(required = true, value = "登录帐号") @RequestParam(value = "account") String account,
                        @ApiParam(required = true, value = "登录密码") @RequestParam(value = "password") String password) {
        Assert.notNull(account, "ACCOUNT");
        Assert.notNull(password, "PASSWORD");
        String error = null;

        Map<String, Object> params = ParamUtil.getMap();
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

            if(StringUtils.isNotBlank(error)) {
                return setModelMap(modelMap, HttpCode.FORBIDDEN, error);
            }
            Long userId = user.getId();
            //判断该用户是否已经登录，如果已经登录，则强制对方下线
            String token = WebUtil.getTokenByUserId(userId);

            if(StringUtils.isNotBlank(token)) {
                WebUtil.clear(token, userId);
            }

            if(user.getPassword().equals(PasswordUtil.decryptPassword(password, user.getSalt()))) {
                //获取角色信息
                Set<String> roles = sysAuthService.findRoles(userId);

                //获取权限信息
                Set<String> permissions = sysAuthService.findPermissions(userId);

                //获取资源信息
                List<SysResource> menus = null;
                String menuCacheKey = "REDIS:MENU:" + userId;
                String menuCache = (String) CacheUtil.getCache().get(menuCacheKey);
                if (StringUtils.isNotBlank(menuCache)) {
                    menus = JsonUtils.jsonToList(menuCache, SysResource.class);
                } else {
                    //获取资源信息
                    menus = sysResourceService.getMenus(userId);
                    if (menus != null) {
                        CacheUtil.getCache().set(menuCacheKey, JsonUtils.objectToJson(menus));
                    }
                }

                // 生成token
                String accessToken = UUID.randomUUID().toString();
                user.setPassword(null);
                user.setSalt(null);

                CacheUtil.getCache().set(Constants.REDIS_SESSION_TOKEN + accessToken, user, 1800);
                CacheUtil.getCache().set(Constants.REDIS_SESSION_ID + user.getId(), accessToken, 1800);
                saveSession(account, request, accessToken, user);
                return setSuccessModelMap(modelMap, new Authority(accessToken, roles, permissions, menus,user));
            }

            logger.warn("USER [{}] PASSWORD IS WRONG: {}", user.getUsername(), password);
            return null;
        } else {
            logger.warn("No user: {}", account);
            return null;
        }
    }

    private void saveSession(String account, HttpServletRequest request, String accessToken, SysUser user) {
        // 踢出用户
        SysSession record = new SysSession();
        record.setAccount(account);
        List<?> sessions = sysSessionService.querySessionIdByAccount(record);

        //到达这一步时，说明用户已经在线了，此时通过account查处SysSessioin后，如果有值进行更新，如果没有新增
        if(sessions != null && sessions.size() > 0) {
            //删除
            record.setUserId(user.getId());
            sysSessionService.deleteByUserId(record);
        }
        // 保存用户
        record.setSessionId(accessToken);
        String host = WebUtil.getHost(request);
        record.setIp(host);
        record.setUserId(user.getId());
        record.setStartTime(new Date());
        sysSessionService.update(record);
    }

    // 登出
    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    public Object logout(ModelMap modelMap, HttpServletRequest request, @CurrentUser SysUser user, @Token String token) {
        WebUtil.clear(token, user.getId());
        return setSuccessModelMap(modelMap);
    }

    // 没有登录
    @ApiOperation(value = "没有登录")
    @RequestMapping(value = "/unauthorized", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public Object unauthorized(ModelMap modelMap) throws Exception {
        return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
    }

    // 没有权限
    @ApiOperation(value = "没有权限")
    @RequestMapping(value = "/forbidden", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public Object forbidden(ModelMap modelMap) {
        return setModelMap(modelMap, HttpCode.FORBIDDEN);
    }
}
