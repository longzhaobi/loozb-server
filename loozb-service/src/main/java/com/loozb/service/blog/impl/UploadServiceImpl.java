package com.loozb.service.blog.impl;

import com.loozb.core.util.FastDFSClient;
import com.loozb.model.PictureResult;
import com.loozb.service.blog.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author： 龙召碧
 * @Date: Created in 2017-3-26 17:17
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @Override
    public PictureResult uploadImage(MultipartFile uploadFile) {
        PictureResult result = new PictureResult();
        //判断图片是否为空
        if (uploadFile.isEmpty()) {
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }
        //上传到图片服务器
        try {
            //取图片扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            //取扩展名不要“.”
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            FastDFSClient client = new FastDFSClient("classpath:client.conf");
            String url = client.uploadFile(uploadFile.getBytes(), extName);
            //把url响应给客户端
            result.setError(0);
            result.setUrl(IMAGE_SERVER_BASE_URL + url);
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("图片上传失败");
        }
        return result;
    }
}
