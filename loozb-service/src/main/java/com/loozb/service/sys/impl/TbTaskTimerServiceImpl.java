package com.loozb.service.sys.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.model.sys.TbTaskTimer;
import com.loozb.service.sys.TbTaskTimerService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时器任务表 服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
@Service
@CacheConfig(cacheNames = "TbTaskTimer")
public class TbTaskTimerServiceImpl extends BaseServiceImpl<TbTaskTimer> implements TbTaskTimerService{
	
}
