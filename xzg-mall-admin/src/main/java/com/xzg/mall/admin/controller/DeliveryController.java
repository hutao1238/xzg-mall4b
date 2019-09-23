package com.xzg.mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xzg.mall.bean.model.Delivery;
import com.xzg.mall.service.DeliveryService;

/**
 *
 * @author lgh on 2018/11/26.
 */
@RestController
@RequestMapping("/admin/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

	/**
	 * 分页获取
	 */
    @GetMapping("/list")
	public ResponseEntity<List<Delivery>> page(){
		
		List<Delivery> list = deliveryService.list();
		return ResponseEntity.ok(list);
	}

}
