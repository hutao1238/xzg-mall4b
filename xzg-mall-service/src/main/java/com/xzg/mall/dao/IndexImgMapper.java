package com.xzg.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzg.mall.bean.model.IndexImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface IndexImgMapper extends BaseMapper<IndexImg> {

	void deleteIndexImgsByIds(@Param("ids") Long[] ids);

	List<IndexImg> listIndexImgs();
}
