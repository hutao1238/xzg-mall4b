package com.xzg.mall.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzg.mall.bean.app.dto.SkuDto;
import com.xzg.mall.bean.model.Sku;
import com.xzg.mall.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sku")
@Api(tags = "sku规格接口")
@AllArgsConstructor
public class SkuController {

    private final SkuService skuService;

    private final MapperFacade mapperFacade;

    @GetMapping("/getSkuList")
    @ApiOperation(value = "通过prodId获取商品全部规格列表", notes = "通过prodId获取商品全部规格列表")
    @ApiImplicitParam(name = "prodId", value = "商品id", dataType = "Long")
    public ResponseEntity<List<SkuDto>> getSkuListByProdId(Long prodId) {
        List<Sku> skus = skuService.list(new LambdaQueryWrapper<Sku>()
                .eq(Sku::getStatus, 1)
                .eq(Sku::getIsDelete, 0)
                .eq(Sku::getProdId, prodId)
        );
        List<SkuDto> skuDtoList = mapperFacade.mapAsList(skus, SkuDto.class);
        return ResponseEntity.ok(skuDtoList);
    }
}
