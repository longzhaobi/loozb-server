package com.loozb.service.sys;

import com.loozb.model.sys.SysColumn;
import com.loozb.model.sys.SysTable;

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
public interface SysTableService {


    public List<SysTable> selectTable(Map<String, Object> params);

    public List<SysColumn> selectColumns(Map<String, Object> params);


}
