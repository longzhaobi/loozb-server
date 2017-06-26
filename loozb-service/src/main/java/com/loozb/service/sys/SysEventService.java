package com.loozb.service.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.loozb.core.base.BaseService;
import com.loozb.model.sys.SysEvent;

import java.util.Map;

/**
 * <p>
 * 操作日志表  服务实现类
 * </p>
 *
 * @author LongZhaoBi
 * @since 2017-05-20
 */
public interface SysEventService extends BaseService<SysEvent> {

    public Page<SysEvent> query(Map<String, Object> params);
}