package com.xzg.mall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzg.mall.bean.model.IndexImg;
import com.xzg.mall.bean.model.Product;
import com.xzg.mall.common.util.PageParam;
import com.xzg.mall.security.util.SecurityUtils;
import com.xzg.mall.service.IndexImgService;
import com.xzg.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * @author hutao on 2018/11/26.
 */
@RestController
@RequestMapping("/admin/indexImg")
public class IndexImgController {

    @Autowired
    private IndexImgService indexImgService;

    @Autowired
    private ProductService productService;


    /**
     * 分页获取
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin:indexImg:page')")
    public ResponseEntity<IPage<IndexImg>> page(IndexImg indexImg, PageParam<IndexImg> page) {
        IPage<IndexImg> indexImgIPage = indexImgService.page(page,
                new LambdaQueryWrapper<IndexImg>()
                        .eq(indexImg.getStatus() != null, IndexImg::getStatus, indexImg.getStatus())
                        .orderByDesc(IndexImg::getSeq));
        return ResponseEntity.ok(indexImgIPage);
    }

    /**
     * 获取信息
     */
    @GetMapping("/info/{imgId}")
    @PreAuthorize("@pms.hasPermission('admin:indexImg:info')")
    public ResponseEntity<IndexImg> info(@PathVariable("imgId") Long imgId) {
        Long shopId = SecurityUtils.getSysUser().getShopId();
        IndexImg indexImg = indexImgService.getOne(new LambdaQueryWrapper<IndexImg>().eq(IndexImg::getShopId, shopId).eq(IndexImg::getImgId, imgId));
        if (Objects.nonNull(indexImg.getRelation())) {
            Product product = productService.getProductByProdId(indexImg.getRelation());
            indexImg.setPic(product.getPic());
            indexImg.setProdName(product.getProdName());
        }
        return ResponseEntity.ok(indexImg);
    }

    /**
     * 保存
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin:indexImg:save')")
    public ResponseEntity<Void> save(@RequestBody @Valid IndexImg indexImg) {
        Long shopId = SecurityUtils.getSysUser().getShopId();
        indexImg.setShopId(shopId);
        indexImg.setUploadTime(new Date());
        indexImgService.save(indexImg);
        indexImgService.removeIndexImgs();
        return ResponseEntity.ok().build();
    }

    /**
     * 修改
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin:indexImg:update')")
    public ResponseEntity<Void> update(@RequestBody @Valid IndexImg indexImg) {
        indexImgService.updateById(indexImg);
        indexImgService.removeIndexImgs();
        return ResponseEntity.ok().build();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('admin:indexImg:delete')")
    public ResponseEntity<Void> delete(@RequestBody Long[] ids) {
        indexImgService.deleteIndexImgsByIds(ids);
        indexImgService.removeIndexImgs();
        return ResponseEntity.ok().build();
    }
}
