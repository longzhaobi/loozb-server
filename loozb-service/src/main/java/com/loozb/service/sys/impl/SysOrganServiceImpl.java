package com.loozb.service.sys.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.model.sys.SysOrgan;
import com.loozb.service.sys.SysOrganService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织机构部门信息表 服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
@Service
@CacheConfig(cacheNames = "SysOrgan")
public class SysOrganServiceImpl extends BaseServiceImpl<SysOrgan> implements SysOrganService {
	
}
