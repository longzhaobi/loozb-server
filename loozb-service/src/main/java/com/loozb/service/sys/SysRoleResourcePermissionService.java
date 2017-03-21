package com.loozb.service.sys;

import com.loozb.core.base.BaseService;
import com.loozb.model.sys.SysRoleResourcePermission;

import java.util.List;

/**
 * <p>
 * 角色资源权限表，用于描述角色拥有哪些资源和此资源对应的权限 服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
public interface SysRoleResourcePermissionService extends BaseService<SysRoleResourcePermission> {

    /**
     * 通过角色ID获取权限和资源
     * @param roleId
     * @return
     */
    public List<SysRoleResourcePermission> findSrrpByRoleId(Long roleId);

    /**
     * 根据userId获取拥有的资源信息
     *
     * @param userId
     * @return
     */
    public List<SysRoleResourcePermission> findSrrpByUserId(Long userId);

    public List<String> getPermissionByRoleIdAndResourceId(Long resourceId, Long roleId);

    public List<Long> selectIdsByResourceIdAndRoleId(Long resourceId, Long roleId);
}
