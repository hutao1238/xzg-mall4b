/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.ProdProp;

import java.util.List;

/**
 *
 * Created by lgh on 2018/07/06.
 */
public interface ProdPropService extends IService<ProdProp> {

	/**
	 * 获取属性与属性值
	 * @param prodProp
	 * @param page
	 * @return
	 */
	IPage<ProdProp> pagePropAndValue(ProdProp prodProp, Page<ProdProp> page);

	/**
	 * 保存属性、属性值
	 * @param prodProp
	 */
	void saveProdPropAndValues(ProdProp prodProp);

	/**
	 * 更新属性、属性值
	 * @param prodProp
	 */
	void updateProdPropAndValues(ProdProp prodProp);

	/**
	 * 删除属性、属性值
	 * 如果propRule为2，同时删除分类与属性值之间的关联关系
	 */
	void deleteProdPropAndValues(Long propId,Integer propRule,Long shopId);

	/**
	 * 根据分类id获取分类所关联的属性
	 * @param categoryId
	 * @return
	 */
	List<ProdProp> listByCategoryId(Long categoryId);

}