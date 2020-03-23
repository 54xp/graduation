package com.xp.graduation.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author XP
 * @create 2020-02-26 22:48
 */
public class UploadFileUtil {
    public static String uploadFile(MultipartFile upload) throws IOException {
        String path = "D:/uploads/";
        System.out.println("存储路径  "+path);
        System.out.println("文件名  "+upload.getOriginalFilename());
        File file = new File(path);
        // 判断该路径是否存在
        if(!file.exists()) {
            // 没有，则创建文件夹
            file.mkdirs();
        }

        // 上传文件项
        String filename = upload.getOriginalFilename();
        // 生成唯一文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        // 完成文件上传
        upload.transferTo(new File(path,filename));
        return filename;
    }
}
