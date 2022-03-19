package com.wizardry.qingyou.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@Component
public class OSSUtil implements InitializingBean {
    // 外网域名
    @Value("${aliyun.oss.config.endpoint}")
    private String endpoint;
    //private String endpoint = "oss-cn-chengdu.aliyuncs.com";

    @Value("${aliyun.oss.config.key-id}")
    // Accesskey-Secret
    private String accessKeyId;

    @Value("${aliyun.oss.config.key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.config.bucket-name}")
    // 桶名
    private String bucketName;

    // 文件完整路径
    private String objectname = "wizardry/imgs/";

    // 设置共有访问变量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
    }

    public OSS buildOSSClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public PutObjectRequest packObject(String objName, MultipartFile file) throws IOException {
        return new PutObjectRequest(bucketName, objName, file.getInputStream());
    }
}
