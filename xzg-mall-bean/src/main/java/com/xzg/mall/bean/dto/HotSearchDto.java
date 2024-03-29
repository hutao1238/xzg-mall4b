package com.xzg.mall.bean.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("热搜数据")
@Data
public class HotSearchDto implements Serializable {

    @ApiModelProperty("热搜id")
    private Long hotSearchId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

}
