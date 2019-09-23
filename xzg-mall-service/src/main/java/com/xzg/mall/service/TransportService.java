package com.xzg.mall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.Transport;
import org.springframework.cache.annotation.CacheEvict;

/**
 *
 * @author lgh on 2018/11/16.
 */
public interface TransportService extends IService<Transport> {

	void insertTransportAndTransfee(Transport transport);

	void updateTransportAndTransfee(Transport transport);

	void deleteTransportAndTransfeeAndTranscity(Long[] ids);

	Transport getTransportAndAllItems(Long transportId);

	@CacheEvict(cacheNames = "TransportAndAllItems", key = "#transportId")
	default void removeTransportAndAllItemsCache(Long transportId){}

}
