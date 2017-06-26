package com.loozb.service.sys;

import com.loozb.core.base.BaseService;
import com.loozb.model.SysUser;

/**
 * @Author： 龙召碧
 * @Date: Created in 2017-3-21 14:14
 */
public interface SysUserService extends BaseService<SysUser> {
    SysUser getUserInfoByToken(String token);
}
