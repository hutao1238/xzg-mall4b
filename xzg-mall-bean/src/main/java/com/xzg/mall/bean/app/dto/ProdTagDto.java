package com.xzg.mall.bean.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProdTagDto {

    @ApiModelProperty(value = "分组标签id")
    private Long id;

    @ApiModelProperty(value = "分组标签标题")
    private String title;

    @ApiModelProperty(value = "排序（数值越高越靠前）")
    private String seq;

    @ApiModelProperty(value = "列表样式(0:一列一个,1:一列两个,2:一列三个)")
    private String style;

}
