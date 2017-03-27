package com.loozb.service.blog.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.model.blog.TbComment;
import com.loozb.service.blog.TbCommentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-03-27
 */
@Service
@CacheConfig(cacheNames = "TbComment")
public class TbCommentServiceImpl extends BaseServiceImpl<TbComment> implements TbCommentService {
	
}
