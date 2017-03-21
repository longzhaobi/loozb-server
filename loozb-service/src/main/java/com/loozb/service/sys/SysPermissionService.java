package com.loozb.service.sys;

import com.loozb.core.base.BaseService;
import com.loozb.model.sys.SysPermission;
import com.loozb.model.sys.ext.Columns;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
public interface SysPermissionService extends BaseService<SysPermission> {

    public List<SysPermission> findPermissionByIds(String permissionIds);

    public List<Columns> queryColumns(Map<String, Object> params);
}
