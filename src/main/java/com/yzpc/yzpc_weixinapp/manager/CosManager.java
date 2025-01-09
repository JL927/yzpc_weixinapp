package com.yzpc.yzpc_weixinapp.manager;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import com.yzpc.yzpc_weixinapp.config.CosClientConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wq
 * @description 腾讯云服务
 * @date 2024/12/21 16:09:36
 */
@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;

    /**
     * 上传文件
     * @param key 唯一键
     * @param file 文件
     * @return
     */
    public PutObjectResult putObject(String key, File file){
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 删除文件
     * @param key 唯一键
     */
    public void deleteObject(String key){
        cosClient.deleteObject(cosClientConfig.getBucket(),key);
    }

    public void deleteObjects(List<String> keylist){
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(cosClientConfig.getBucket());
        List<DeleteObjectsRequest.KeyVersion> deleteKeylist = new ArrayList<>();
        for (String key:keylist){
            deleteKeylist.add(new DeleteObjectsRequest.KeyVersion(key));
        }

        deleteObjectsRequest.setKeys(deleteKeylist);
        cosClient.deleteObjects(deleteObjectsRequest);

    }

    /**
     * 获取文件夹下图片的路径
     * @param prefix 文件夹前缀
     * @return 图片路径列表
     */
    public List<String> listAllImagesInFolder(String prefix) {
        List<String> imageList = new ArrayList<>();

        // 创建列出对象请求对象，指定要遍历的前缀（这里是test/，代表test文件夹下的内容）
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(cosClientConfig.getBucket());
//        listObjectsRequest.setPrefix("test/");
        listObjectsRequest.setPrefix(prefix);

        // 发起列出对象请求并获取结果对象
        ObjectListing objectListing = cosClient.listObjects(listObjectsRequest);
        // 获取对象列表（包含文件夹下所有文件和文件夹信息等）
        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();

        // 遍历对象列表，筛选出图片文件（根据后缀判断）并添加到结果列表中
        for (COSObjectSummary objectSummary : objectSummaries) {
            String key = objectSummary.getKey();
            if (key.endsWith(".jpg") || key.endsWith(".png") || key.endsWith(".jpeg") || key.endsWith(".webp")) {
                imageList.add(key);
            }
        }

        return imageList;
    }
}

