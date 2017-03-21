package com.loozb.core.shiro.realm;

import com.baomidou.mybatisplus.plugins.Page;
import com.loozb.core.shiro.token.StatelessToken;
import com.loozb.core.util.WebUtil;
import com.loozb.model.sys.SysUser;
import com.loozb.service.sys.SysAuthService;
import com.loozb.service.sys.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 此信息用来登录
 */
@Component
public class StatelessRealm extends AuthorizingRealm {

    private static final Logger log = Logger.getLogger(StatelessRealm.class);

    @Autowired
    private SysAuthService sysAuthService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;// 表示此Realm只支持OAuth2Token类型
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();
        if (StringUtils.isNotBlank(username)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("available", "1");
            params.put("account", username);
            Page<?> pageInfo = sysUserService.query(params);
            if (pageInfo.getTotal() == 1) {
                SysUser user = (SysUser) pageInfo.getRecords().get(0);
                Set<String> roles = (Set<String>) sysAuthService.findRoles(user.getId());

                //获取权限信息
                Set<String> permissions = (Set<String>) sysAuthService.findPermissions(user.getId());
                // 添加用户权限
                authorizationInfo.setRoles(roles);
                authorizationInfo.setStringPermissions(permissions);
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelesstoken = (StatelessToken) token;
        String accessToken = statelesstoken.getAccessToken();
        // 执行登录
        String accounts = extractUsername(accessToken);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                accounts, accessToken, getName());
        return authenticationInfo;
    }

    /**
     * 通过token获取用户名，如果获取不到，则为授权过期了，获取到并更新redis失效时间
     * @param accessToken
     * @return
     */
    private String extractUsername(String accessToken) {
        return WebUtil.getUsernameByToken(accessToken);
    }

}
