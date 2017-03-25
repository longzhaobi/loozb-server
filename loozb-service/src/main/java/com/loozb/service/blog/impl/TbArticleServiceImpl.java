package com.loozb.service.blog.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.loozb.core.base.BaseServiceImpl;
import com.loozb.model.blog.TbArticle;
import com.loozb.service.blog.TbArticleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Map;

/**
 * <p>
 * 博客列表 服务实现类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-03-22
 */
@Service
@CacheConfig(cacheNames = "TbArticle")
public class TbArticleServiceImpl extends BaseServiceImpl<TbArticle> implements TbArticleService {

}
