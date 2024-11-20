package com.example.mallthridparty.service.impl;/**
 * @Author:zhoayu
 * @Date:2024/11/19 12:26
 * @Description:com.example.mallthridparty.service.impl
 * @version:1.0
 */

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.example.mallthridparty.config.OSSConfig;
import com.example.mallthridparty.service.OssService;
import com.example.mallthridparty.vo.OssToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName OssServiceImpl
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/19
 */
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OSSConfig ossConfig;

    @Autowired
    private IAcsClient ascClient;


    /**
     * 获得oss服务端签名
     * */
    @Override
    public OssToken getOssToken() throws ClientException, UnsupportedEncodingException {

        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = today + "/";

        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndPoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());

        // 请求有效期。
        long expireEndTime = System.currentTimeMillis() + 3600 * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        // 设置上传文件的大小限制。
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
//        // 表单域的值必须以指定前缀开始。
//        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        String policy = ossClient.generatePostPolicy(expiration, policyConds);


        byte[] binaryData = policy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(policy);

        // host的格式为 bucketName.endpoint
        return new OssToken(dir, "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndPoint(), ossConfig.getAccessKeyId(), null, encodedPolicy, postSignature);
    }

}

 