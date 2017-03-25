package com.loozb.mapper.blog;

import com.loozb.model.blog.TbClassification;
import com.loozb.core.base.BaseMapper;

/**
 * <p>
  * 文章分类 Mapper 接口
 * </p>
 *
 * @author 龙召碧
 * @since 2017-03-25
 */
public interface TbClassificationMapper extends BaseMapper<TbClassification> {

    int queryArticleCount(String id);
}