package com.xzg.mall.admin.controller;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzg.mall.security.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.xzg.mall.common.util.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.xzg.mall.bean.model.Transport;
import com.xzg.mall.service.TransportService;

/**
 * @author hutao on 2018/11/16.
 */
@RestController
@RequestMapping("/shop/transport")
public class TransportController {

    @Autowired
    private TransportService transportService;


    /**
     * 分页获取
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('shop:transport:page')")
    public ResponseEntity<IPage<Transport>> page(Transport transport,PageParam<Transport> page) {
        Long shopId = SecurityUtils.getSysUser().getShopId();
        IPage<Transport> transports = transportService.page(page,
                new LambdaQueryWrapper<Transport>()
                        .eq(Transport::getShopId, shopId)
                        .like(StringUtils.isNotBlank(transport.getTransName()), Transport::getTransName, transport.getTransName()));
        return ResponseEntity.ok(transports);
    }

    /**
     * 获取信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("@pms.hasPermission('shop:transport:info')")
    public ResponseEntity<Transport> info(@PathVariable("id") Long id) {
        Transport transport = transportService.getTransportAndAllItems(id);
        return ResponseEntity.ok(transport);
    }

    /**
     * 保存
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('shop:transport:save')")
    public ResponseEntity<Void> save(@RequestBody Transport transport) {
        Long shopId = SecurityUtils.getSysUser().getShopId();
        transport.setShopId(shopId);
        Date createTime = new Date();
        transport.setCreateTime(createTime);
        transportService.insertTransportAndTransfee(transport);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('shop:transport:update')")
    public ResponseEntity<Void> update(@RequestBody Transport transport) {
        transportService.updateTransportAndTransfee(transport);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('shop:transport:delete')")
    public ResponseEntity<Void> delete(@RequestBody Long[] ids) {
        transportService.deleteTransportAndTransfeeAndTranscity(ids);
        // 删除运费模板的缓存
        for (Long id : ids) {
            transportService.removeTransportAndAllItemsCache(id);
        }
        return ResponseEntity.ok().build();
    }


    /**
     * 获取运费模板列表
     */
    @GetMapping("/list")
    public ResponseEntity<List<Transport>> list() {
        Long shopId = SecurityUtils.getSysUser().getShopId();
        List<Transport> list = transportService.list(new LambdaQueryWrapper<Transport>().eq(Transport::getShopId, shopId));
        return ResponseEntity.ok(list);
    }

}
