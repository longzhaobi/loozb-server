package com.loozb.service.blog.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.model.blog.TbArticle;
import com.loozb.model.blog.TbClassification;
import com.loozb.service.blog.TbArticleService;
import com.loozb.service.blog.TbClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

    @Autowired
    private TbClassificationService tbClassificationService;

    @Override
    public TbArticle queryById(Long id) {
        TbArticle article = super.queryById(id);
        if(article != null) {
            TbClassification classification = tbClassificationService.queryById(Long.parseLong(article.getClassification()));
            if(classification != null) {
                article.setClassificationName(classification.getName());
            }
        }
        return article;
    }
}
