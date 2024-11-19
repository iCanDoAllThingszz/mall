package com.example.mallthridparty.service;/**
 * @Author:zhoayu
 * @Date:2024/11/19 12:26
 * @Description:com.example.mallthridparty.service
 * @version:1.0
 */

import com.aliyuncs.exceptions.ClientException;
import com.example.mallthridparty.vo.OssToken;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName OssService
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/19
 */
public interface OssService {

    public OssToken getOssToken() throws ClientException, UnsupportedEncodingException;

}
