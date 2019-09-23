package com.xzg.mall.service;

import java.io.IOException;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.AttachFile;

/**
 *
 * Created by lgh on 2018/07/27.
 */
public interface AttachFileService extends IService<AttachFile> {

	String uploadFile(byte[] bytes,String originalName) throws IOException;
	
	void deleteFile(String fileName);
}
