package com.loozb.core.base;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * service接口层统一接口
 * @Author： 龙召碧
 * @Date: Created in 2017-3-21 12:36
 */
public interface BaseService<T extends BaseModel> {
    /** 根据Id查询(默认类型T) */
    public List<T> getList(List<Long> ids);

    /** 根据Id查询(cls返回类型Class) */
    public <K> List<K> getList(List<Long> ids, Class<K> cls);

    
    public void del(Long id);

    public void delete(Long id);

    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<T> query(Map<String, Object> params);

    /**
     * 新增 or 删除
     * @param record
     * @return
     */
    public T update(T record);

    /**
     * 通过主键查询，统一使用主键查询，实现缓存
     * @param id
     * @return
     */
    public T queryById(Long id);

    //查询list
    public List<T> queryList(Map<String, Object> params);

}
