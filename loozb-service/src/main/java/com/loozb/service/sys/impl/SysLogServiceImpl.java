package com.loozb.service.sys.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.model.sys.SysLog;
import com.loozb.service.sys.SysLogService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
@Service
@CacheConfig(cacheNames = "SysLog")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService{
	
}
