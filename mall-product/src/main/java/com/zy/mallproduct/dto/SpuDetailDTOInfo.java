package com.zy.mallproduct.dto;/**
 * @Author:zhoayu
 * @Date:2024/11/30 18:11
 * @Description:com.zy.mallproduct.dto
 * @version:1.0
 */

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SpuDetailDTOInfo
 * @Description //详细SPU商品信息
 * @Author zhaoyu
 * @Date 2024/11/30
 */

@Data
public class SpuDetailDTOInfo {

    private String spuName;

    private String spuDescription;

    private Integer categoryId;

    private Integer brandId;

    private BigDecimal weight;

    private Integer publishStatus;

    private List<String> descript;

    private List<String> images;

    private List<AttrDTO> attrs;

    private List<SkuInfoDTO> skuInfoDTOS;

}

 