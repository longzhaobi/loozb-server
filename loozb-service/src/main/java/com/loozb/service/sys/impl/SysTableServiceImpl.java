package com.loozb.service.sys.impl;

import com.loozb.mapper.sys.SysTableMapper;
import com.loozb.model.sys.SysColumn;
import com.loozb.model.sys.SysTable;
import com.loozb.service.sys.SysTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-25
 */
@Service
@CacheConfig(cacheNames = "SysTable")
public class SysTableServiceImpl implements SysTableService{

    @Autowired(required = false)
    private SysTableMapper sysTableMapper;

    public List<SysTable> selectTable(Map<String, Object> params) {
        return sysTableMapper.selectTable(params);
    }

    public List<SysColumn> selectColumns(Map<String, Object> params) {
        return sysTableMapper.selectColumns(params);
    }


}
