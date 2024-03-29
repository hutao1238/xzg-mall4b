package com.xzg.mall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzg.mall.common.util.PageParam;
import com.xzg.mall.bean.model.UserAddr;
import com.xzg.mall.common.annotation.SysLog;
import com.xzg.mall.service.UserAddrService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户地址管理
 *
 * @author hutao
 * @date 2019-04-15 10:49:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user/addr")
public class UserAddrController {

    private final UserAddrService userAddrService;

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param userAddr 用户地址管理
     * @return 分页数据
     */
    @GetMapping("/page")
    public ResponseEntity<IPage<UserAddr>> getUserAddrPage(PageParam page, UserAddr userAddr) {
        return ResponseEntity.ok(userAddrService.page(page, new LambdaQueryWrapper<UserAddr>()));
    }


    /**
     * 通过id查询用户地址管理
     *
     * @param addrId id
     * @return 单个数据
     */
    @GetMapping("/info/{addrId}")
    public ResponseEntity<UserAddr> getById(@PathVariable("addrId") Long addrId) {
        return ResponseEntity.ok(userAddrService.getById(addrId));
    }

    /**
     * 新增用户地址管理
     *
     * @param userAddr 用户地址管理
     * @return 是否新增成功
     */
    @SysLog("新增用户地址管理")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('user:addr:save')")
    public ResponseEntity<Boolean> save(@RequestBody @Valid UserAddr userAddr) {
        return ResponseEntity.ok(userAddrService.save(userAddr));
    }

    /**
     * 修改用户地址管理
     *
     * @param userAddr 用户地址管理
     * @return 是否修改成功
     */
    @SysLog("修改用户地址管理")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('user:addr:update')")
    public ResponseEntity<Boolean> updateById(@RequestBody @Valid UserAddr userAddr) {
        return ResponseEntity.ok(userAddrService.updateById(userAddr));
    }

    /**
     * 通过id删除用户地址管理
     *
     * @param addrId id
     * @return 是否删除成功
     */
    @SysLog("删除用户地址管理")
    @DeleteMapping("/{addrId}")
    @PreAuthorize("@pms.hasPermission('user:addr:delete')")
    public ResponseEntity<Boolean> removeById(@PathVariable Long addrId) {
        return ResponseEntity.ok(userAddrService.removeById(addrId));
    }

}
