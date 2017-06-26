package com.loozb.service.sys;

import com.loozb.core.base.BaseService;
import com.loozb.model.sys.SysSession;

import java.util.List;

/**
 * <p>
 * 用户角色信息表 服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
public interface SysSessionService extends BaseService<SysSession> {


    // 系统触发,由系统自动管理缓存
    public void deleteBySessionId(final SysSession sysSession);

    public void deleteByUserId(final SysSession session);

    public List<String> querySessionIdByAccount(SysSession sysSession);
    //
    public void cleanExpiredSessions();
}
