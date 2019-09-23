package com.xzg.mall.common.bean;

import lombok.Data;

/**
 * 七牛云存储配置信息
 * @author hutao
 */
@Data
public class Qiniu {

	private String accessKey;

	private String secretKey;

	private String bucket;

	private String resourcesUrl;

}
