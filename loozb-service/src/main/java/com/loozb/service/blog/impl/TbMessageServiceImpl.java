package com.loozb.service.blog.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.model.blog.TbMessage;
import com.loozb.service.blog.TbMessageService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言 服务实现类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-03-27
 */
@Service
@CacheConfig(cacheNames = "TbMessage")
public class TbMessageServiceImpl extends BaseServiceImpl<TbMessage> implements TbMessageService {
	
}
