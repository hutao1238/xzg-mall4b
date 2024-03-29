package com.xzg.mall.bean.app.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xzg.mall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDto {

	@ApiModelProperty(value = "分类id",required=true)
	private Long categoryId;

	@ApiModelProperty(value = "分类父id",required=true)
	private Long parentId;

	@ApiModelProperty(value = "分类名称",required=true)
	private String categoryName;

	@ApiModelProperty(value = "分类图片",required=true)
	@JsonSerialize(using = ImgJsonSerializer.class)
	private String pic;

}
