package com.example.mallthridparty;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.example.mallthridparty.config.OSSConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class MallThridPartyApplicationTests {

    @Autowired
    private OSSConfig ossConfig;

    @Test
    void contextLoads() {
    }

    /**
     * 阿里云oss, 简单上传文件测试
     * */
    @Test
    public void aliyunOssUploadFile() throws Exception {

        //获取相关配置
        String bucketName = ossConfig.getBucketName();
        String endPoint = ossConfig.getEndPoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        //获取原生文件名
        String filePath= "/Users/zhaoyu/Desktop/表格.pdf";

        //oss上存储的路径
        String objectName = "exampledir/表格混文字.pdf";

        try {
            InputStream inputStream = new FileInputStream(filePath);

            PutObjectResult result = ossClient.putObject(bucketName, objectName, inputStream);

            //拼装返回路径
            if (result != null) {
                System.out.println("https://"+bucketName+"."+endPoint+"/"+objectName);
                //https://mall-bucket-zy.https://oss-cn-beijing.aliyuncs.com/exampledir/表格混文字.pdf
            }
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException | FileNotFoundException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
