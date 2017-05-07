package com.loozb.service.sys.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.core.util.CacheUtil;
import com.loozb.core.util.JsonUtil;
import com.loozb.model.sys.SysAuth;
import com.loozb.model.sys.SysRole;
import com.loozb.model.sys.SysUser;
import com.loozb.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-25
 */
@Service
@CacheConfig(cacheNames = "SysUser")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService{

    @Autowired
    private SysAuthServiceImpl sysAuthService;

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @Override
    @Transactional
    public SysUser queryById(Long id) {
        try {
            String key = getCacheKey(id);
            SysUser record = (SysUser) CacheUtil.getCache().get(key);
            if (record == null) {
                record = mapper.selectById(id);
                packageAuthInfo(record);
                CacheUtil.getCache().set(key, record);
            }
            return record;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void packageAuthInfo(SysUser user) {
        List<SysAuth> auths = sysAuthService.queryByUserId(user.getId());
        String roleIds = "";
        String roleNames = "";
        if (auths != null && auths.size() > 0) {
            for (int i = 0; i < auths.size(); i++) {
                SysAuth auth = auths.get(i);
                SysRole role = sysRoleService.queryById(auth.getRoleId());
                if (i == 0) {
                    roleIds += auth.getRoleId();
                    roleNames += role.getName();
                } else {
                    roleIds += "," + auth.getRoleId();
                    roleNames += "," + role.getName();
                }
            }
        }
        user.setRoleIds(roleIds);
        user.setRoleNames(roleNames);
    }

    @Override
    public SysUser getUserInfoByToken(String token) {
        String result = (String)CacheUtil.getCache().get("REDIS_SESSION:TOKEN:" + token);
        return JsonUtil.jsonToPojo(result, SysUser.class);
    }
}
