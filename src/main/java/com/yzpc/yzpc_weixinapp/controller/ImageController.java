package com.yzpc.yzpc_weixinapp.controller;

import com.qcloud.cos.model.DeleteObjectsRequest;
import com.yzpc.yzpc_weixinapp.common.Result;
import com.yzpc.yzpc_weixinapp.config.CosClientConfig;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.manager.CosManager;
import com.yzpc.yzpc_weixinapp.service.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

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

    @Resource
    public CosClientConfig cosClientConfig;


    /**
     * 图片上传
     * 输入图片数据，存储路径
     * @param multipartFile 图片
     * @return
     */
    @PostMapping("/img/upload")  //  " /img/upload?path=1  "
    public Result uploadImg(@RequestPart("file") MultipartFile multipartFile,
                            @RequestParam(name = "path") String path) {

        // 文件目录
        String filename = multipartFile.getOriginalFilename();
        String filepath = String.format("/%s/%s", path,filename);
        File file = null;
        try {
            // 上传文件
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filepath, file);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
        // return Result.error(filename+"图片上传失败");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }



        return Result.success();

    }

    /**
     * 查询某个请求包含的图片
     * @param prefix 文件夹名
     * @return 图片名列表
     */
    @GetMapping("/img/getImgList")// " /img/getImgList?key='test/' "
    public Result getImgList(@RequestParam(name = "prefix") String prefix){
        List<String> keys = cosManager.listAllImagesInFolder(prefix);
        return Result.success(keys);
    }

    /**
     * 获取文件的url前缀，前缀+key就是图片url
     * @return 'https://yzpc-weixinapp-1333205881.cos.ap-nanjing.myqcloud.com'
     */
    @GetMapping("/img/getHost")
    public Result getHost(){
        return Result.success(cosClientConfig.getHost());
    }

    /**
     * 删除单张图片
     * @param key 图片路径
     * @return
     */
    @DeleteMapping("/img/deleteImg") // " /img/deleteImg?key='/test/a.jpg' "
    public Result deleteImg(@RequestParam(name = "key") String key){
        try {
            cosManager.deleteObject(key);
        } catch (Exception e){
            log.error("file upload error");
            return Result.error(ErrorCode.OPERATION_ERROR,"删除失败");
        }

        return Result.success();
    }

    /**
     * 删除文件夹下所有图片
     * @param prefix 文件夹名
     * @return
     */
    @DeleteMapping("/img/deleteImgList") // " /img/deleteImgList?key='text/' "
    public Result deleteImgList(@RequestParam(name = "prefix") String prefix){

        List<String> keys = cosManager.listAllImagesInFolder(prefix);

        try {
            cosManager.deleteObjects(keys);
        } catch (Exception e){
            log.error("file delete error");
        }

        return Result.success();
    }


}
