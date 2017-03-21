package com.loozb.web.sys;

import com.loozb.core.base.AbstractController;
import com.loozb.service.sys.SysOrganService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 组织机构部门信息表 前端控制器
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
@RestController
@Api(value = "机构管理", description = "机构管理")
@RequestMapping(value = "/organ")
public class SysOrganController extends AbstractController<SysOrganService> {

}
