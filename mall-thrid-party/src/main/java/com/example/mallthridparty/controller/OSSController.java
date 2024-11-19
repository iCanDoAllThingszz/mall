package com.example.mallthridparty.controller;/**
 * @Author:zhoayu
 * @Date:2024/11/19 12:25
 * @Description:com.example.mallthridparty.controller
 * @version:1.0
 */

import com.aliyuncs.exceptions.ClientException;
import com.example.mallthridparty.service.OssService;
import com.example.mallthridparty.vo.OssToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName OSSController
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/19
 */

@RestController
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    private OssService ossService;

    @GetMapping("/token")
    public OssToken getOssToken() throws UnsupportedEncodingException, ClientException {
        return ossService.getOssToken();
    }

}

 