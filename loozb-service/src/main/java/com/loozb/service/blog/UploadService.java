package com.loozb.service.blog;

import com.loozb.model.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author： 龙召碧
 * @Date: Created in 2017-3-26 17:16
 */
public interface UploadService {
    PictureResult uploadImage(MultipartFile uploadFile);
}
