package com.loozb.web.sys;

import com.loozb.core.base.AbstractController;
import com.loozb.service.sys.SysLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 龙召碧
 * @since 2017-02-26
 */
@RestController
@Api(value = "日志管理", description = "日志管理")
@RequestMapping(value = "/logs")
public class SysLogController extends AbstractController<SysLogService> {

}
