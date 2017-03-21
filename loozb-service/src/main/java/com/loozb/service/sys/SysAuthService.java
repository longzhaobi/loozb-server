package com.loozb.service.sys;

import com.loozb.core.base.BaseService;
import com.loozb.model.sys.SysAuth;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限信息表 服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
public interface SysAuthService extends BaseService<SysAuth> {

    /**
     * 通过用户ID获取角色集合
     * @param userId
     * @return
     */
    public Set<String> findRoles(Long userId);

    /**
     * 通过用户ID获取用户权限信息
     * @param userId
     * @return
     */
    public List<SysAuth> queryByUserId(Long userId);

    /**
     * 根据当前用户ID查找权限列表
     *
     * @param userId
     * @return
     */
    public Set<String> findPermissions(Long userId);

    /**
     * 授权
     * @param userId
     * @param roleIds
     */
    public void allot(Long userId, String roleIds);

    /**
     * 根据角色信息获取权限信息
     * @param roleId
     * @return
     */
    public List<SysAuth> queryByRoleId(Long roleId);
}
