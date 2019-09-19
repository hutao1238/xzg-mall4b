/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzg.mall.bean.model.Brand;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface BrandMapper extends BaseMapper<Brand> {

	Brand getByBrandName(String brandName);

	/**
	 * 根据分类id获取品牌列表
	 *
	 * @param categoryId 分类id
	 * @return
	 */
	List<Brand> listByCategoryId(@Param("categoryId")Long categoryId);
}