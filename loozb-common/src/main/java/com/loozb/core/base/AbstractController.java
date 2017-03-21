package com.loozb.core.base;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * @Author： 龙召碧
 * @Date: Created in 2017-2-25 19:46
 */
public abstract class AbstractController<T extends BaseService> extends BaseController {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    protected T service;
    /**
     * 返回分页信息
     * @param modelMap
     * @param param
     * @return
     */
    public Object query(ModelMap modelMap, Map<String, Object> param) {
        Page<?> list = service.query(param);
        return setSuccessModelMap(modelMap, list);
    }

    /**
     * 获取所有列表
     * @param modelMap
     * @param param
     * @return
     */
    public Object queryList(ModelMap modelMap, Map<String, Object> param) {
        List<?> list = service.queryList(param);
        return setSuccessModelMap(modelMap, list);
    }

    /**
     * 获取单个数据
     * @param modelMap
     * @param id
     * @return
     */
    public Object queryById(ModelMap modelMap, Long id) {
        Object result = service.queryById(id);
        return setSuccessModelMap(modelMap, result);
    }

    /**
     * 新增或者更新
     * @param modelMap
     * @param param
     * @return
     */
    public Object update(ModelMap modelMap, BaseModel param) {
        Long userId = getCurrUser();
        if (param.getId() == null) {
            param.setCreateId(userId);
        }
        service.update(param);
        return setSuccessModelMap(modelMap,"更新成功");
    }

    /**
     * 物理删除
     * @param modelMap
     * @param id
     * @return
     */
    public Object delete(ModelMap modelMap, Long id) {
        service.delete(id);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 逻辑删除
     * @param modelMap
     * @param id
     * @return
     */
    public Object del(ModelMap modelMap, Long id) {
        service.del(id);
        return setSuccessModelMap(modelMap);
    }
}
