package com.yzpc.yzpc_weixinapp.utils;

/**
 * @author wq
 * @description 压缩图片
 * @date 2025/1/9 16:22:42
 */
import com.luciad.imageio.webp.WebPWriteParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCompressUtils {

    /**
     * 将 MultipartFile 压缩为 WebP 格式并保存到临时文件
     *
     * @param multipartFile 上传的文件
     * @param quality       压缩质量（0.0 - 1.0）
     * @return 压缩后的临时文件
     * @throws IOException 如果压缩失败
     */
    public static File compressToWebP(MultipartFile multipartFile, float quality) throws IOException {
        // 读取上传的图片
        BufferedImage image = ImageIO.read(multipartFile.getInputStream());

        // 创建临时文件
        File compressedFile = File.createTempFile("compressed-", ".webp");

        // 获取 WebP 图片写入器
        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();

        // 配置压缩参数
        WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSY_COMPRESSION]);
        writeParam.setCompressionQuality(quality); // 设置压缩质量
//        writeParam.setCompressionType();

        // 写入压缩后的图片
        try (FileImageOutputStream output = new FileImageOutputStream(compressedFile)) {
            writer.setOutput(output);
            writer.write(null, new IIOImage(image, null, null), writeParam);
        } finally {
            writer.dispose();
        }

        return compressedFile;
    }
}
