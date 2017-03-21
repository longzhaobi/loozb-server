package com.loozb.service.sys;

import com.loozb.core.base.BaseService;
import com.loozb.model.sys.SysResource;
import com.loozb.model.sys.ext.AuthList;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源管理信息表 服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
public interface SysResourceService extends BaseService<SysResource> {

    @Override
    public List<SysResource> queryList(Map<String, Object> params);

    /**
     * 获取菜单，用于登录时用户去数据库查询
     *
     * @param userId
     * @return
     */
    public List<SysResource> getMenus(Long userId);


    public List<AuthList> queryResourceByPidAndRoleId(Map<String, Object> params);

    public List<SysResource> queryListByAuth(Map<String, Object> p1);
}
