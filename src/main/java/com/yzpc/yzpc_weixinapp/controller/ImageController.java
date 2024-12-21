package com.yzpc.yzpc_weixinapp.controller;

import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.manager.CosManager;
import com.yzpc.yzpc_weixinapp.service.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author wq
 * @description 图片方法
 * @date 2024/12/21 15:12:34
 */
@CrossOrigin
@RestController
@Slf4j
public class ImageController {
    @Resource
    public ImagesService imagesService;
    @Resource
    public CosManager cosManager;

    /**
     * 上传图片
     * @return
     */
    @PostMapping("/img/upload")
    public Result uploadImg(){
        return Result.success();
    }

    /**
     * 测试文件上传
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/img/test/upload")
    public Result testUploadFile(@RequestPart("file") MultipartFile multipartFile) {
        // 文件目录
        String filename = multipartFile.getOriginalFilename();
        String filepath = String.format("/test/%s", filename);
        File file = null;
        try {
            // 上传文件
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filepath, file);
            // 返回可访问地址
            return Result.success(filepath);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
        return Result.error("上传失败");
    }


}
