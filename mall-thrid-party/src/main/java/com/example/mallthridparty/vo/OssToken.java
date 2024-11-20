package com.example.mallthridparty.vo;/**
 * @Author:zhoayu
 * @Date:2024/11/19 12:27
 * @Description:com.example.mallthridparty.vo
 * @version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName OssToken
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssToken {

    private String dir;

    private String host;

    private String accessid;

    private String stsToken;

    private String policy;

    private String signature;

}
