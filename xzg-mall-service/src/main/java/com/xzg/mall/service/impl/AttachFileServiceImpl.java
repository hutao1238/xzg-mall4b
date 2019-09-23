package com.xzg.mall.service.impl;

import java.util.Date;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.xzg.mall.bean.model.AttachFile;
import com.xzg.mall.common.bean.Qiniu;
import com.xzg.mall.common.util.Json;
import com.xzg.mall.dao.AttachFileMapper;
import com.xzg.mall.service.AttachFileService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by lgh on 2018/07/27.
 */
@Service
public class AttachFileServiceImpl extends ServiceImpl<AttachFileMapper, AttachFile> implements AttachFileService {

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;
	@Autowired
	private Qiniu qiniu;

    @Autowired
    private Auth auth;

    public final static String NORM_MONTH_PATTERN = "yyyy/MM/";

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String uploadFile(byte[] bytes,String originalName) throws QiniuException {
		String extName = FileUtil.extName(originalName);
		String fileName =DateUtil.format(new Date(), NORM_MONTH_PATTERN)+ IdUtil.simpleUUID() + "." + extName;


		AttachFile attachFile = new AttachFile();
		attachFile.setFilePath(fileName);
		attachFile.setFileSize(bytes.length);
		attachFile.setFileType(extName);
		attachFile.setUploadTime(new Date());
		attachFileMapper.insert(attachFile);

		String upToken = auth.uploadToken(qiniu.getBucket(),fileName);
	    Response response = uploadManager.put(bytes, fileName, upToken);
	    Json.parseObject(response.bodyString(),  DefaultPutRet.class);
		return fileName;
	}

	@Override
	public void deleteFile(String fileName){
		attachFileMapper.delete(new LambdaQueryWrapper<AttachFile>().eq(AttachFile::getFilePath,fileName));
		try {
			bucketManager.delete(qiniu.getBucket(), fileName);
		} catch (QiniuException e) {
			throw new RuntimeException(e);
		}
	}


}
