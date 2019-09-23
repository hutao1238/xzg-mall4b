package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.IndexImg;

import java.util.List;

/**
 *
 * @author hutao on 2018/11/26.
 */
public interface IndexImgService extends IService<IndexImg> {

	void deleteIndexImgsByIds(Long[] ids);

    List<IndexImg> listIndexImgs();

    void removeIndexImgs();
}
