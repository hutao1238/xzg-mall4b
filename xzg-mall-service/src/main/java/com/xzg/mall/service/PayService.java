package com.xzg.mall.service;

import com.xzg.mall.bean.app.param.PayParam;
import com.xzg.mall.bean.pay.PayInfoDto;

import java.util.List;

/**
 * @author lgh on 2018/09/15.
 */
public interface PayService {


    PayInfoDto pay(String userId, PayParam payParam);

    List<String> paySuccess(String payNo, String bizPayNo);

}
