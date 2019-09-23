package com.xzg.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzg.mall.bean.model.TransfeeFree;
import com.xzg.mall.bean.model.Transport;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface TransportMapper extends BaseMapper<Transport> {

	Transport getTransportAndTransfeeAndTranscity(@Param("id") Long id);

	void deleteTransports(@Param("ids") Long[] ids);

	List<TransfeeFree> getTransfeeFreeAndTranscityFreeByTransportId(@Param("transportId") Long transportId);

}