package com.loozb.service.blog.impl;

import com.loozb.core.base.BaseServiceImpl;
import com.loozb.mapper.blog.TbClassificationMapper;
import com.loozb.model.blog.TbClassification;
import com.loozb.service.blog.TbClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章分类 服务实现类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-03-25
 */
@Service
@CacheConfig(cacheNames = "TbClassification")
public class TbClassificationServiceImpl extends BaseServiceImpl<TbClassification> implements TbClassificationService {

    @Autowired(required = false)
    private TbClassificationMapper tbClassificationMapper;

    @Override
    public List<TbClassification> queryList(Map<String, Object> params) {
        List<TbClassification> list = super.queryList(params);
        List<TbClassification> myList = new ArrayList<TbClassification>();
        for (int i = 0; i < list.size(); i++) {
            TbClassification tc = list.get(i);
            int count = tbClassificationMapper.queryArticleCount(tc.getId().toString());
            tc.setArticleNum(count);
            myList.add(tc);
        }

        return myList;
    }
}
