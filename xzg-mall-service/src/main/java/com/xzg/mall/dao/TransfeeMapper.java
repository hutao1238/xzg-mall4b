package com.xzg.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzg.mall.bean.model.Transfee;
import com.xzg.mall.bean.model.TransfeeFree;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface TransfeeMapper extends BaseMapper<Transfee> {

	void insertTransfees(List<Transfee> transfees);

	void insertTransfeeFrees(List<TransfeeFree> transfeeFrees);

	void deleteByTransportId(@Param("transportId") Long transportId);

	void deleteTransfeeFreesByTransportId(@Param("transportId") Long transportId);


}