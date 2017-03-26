package com.loozb.web.sys;

import com.loozb.core.base.BaseController;
import com.loozb.model.PictureResult;
import com.loozb.service.blog.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author： 龙召碧
 * @Date: Created in 2017-3-26 12:06
 */
@RestController
@Api(value = "文件上传接口", description = "文件上传接口")
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController extends BaseController {

    @Autowired(required = false)
    private UploadService uploadService;

    @RequestMapping("/images")
    @ApiOperation(value = "上传图片")
    public Object uploadImage(ModelMap modelMap, @RequestParam MultipartFile uploadFile) {
        PictureResult result = uploadService.uploadImage(uploadFile);
        return setSuccessModelMap(modelMap, result);
    }
}
