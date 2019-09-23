package com.xzg.mall.service;

import com.xzg.mall.bean.app.dto.ProductItemDto;
import com.xzg.mall.bean.model.UserAddr;

public interface TransportManagerService {

	Double calculateTransfee(ProductItemDto productItem, UserAddr userAddr);
}
