package com.xzg.mall.bean.app.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {


	@ApiModelProperty(value = "用户状态：0禁用 1正常",required=true)
	private Integer status;
	@ApiModelProperty(value = "token",required=true)
	private String token;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}