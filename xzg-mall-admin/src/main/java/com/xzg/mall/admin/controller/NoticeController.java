/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.xzg.mall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzg.mall.common.util.PageParam;
import com.xzg.mall.bean.model.Notice;
import com.xzg.mall.common.annotation.SysLog;
import com.xzg.mall.security.util.SecurityUtils;
import com.xzg.mall.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 公告管理
 *
 * @author hzm
 * @date
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shop/notice")
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 分页查询
     *
     * @param page   分页对象
     * @param notice 公告管理
     * @return 分页数据
     */
    @GetMapping("/page")
    public ResponseEntity<IPage<Notice>> getNoticePage(PageParam<Notice> page, Notice notice) {
        IPage<Notice> noticeIPage = noticeService.page(page, new LambdaQueryWrapper<Notice>()
                .eq(notice.getStatus() != null, Notice::getStatus, notice.getStatus())
                .like(notice.getTitle() != null, Notice::getTitle, notice.getTitle()).orderByDesc(Notice::getUpdateTime));
        return ResponseEntity.ok(noticeIPage);
    }


    /**
     * 通过id查询公告管理
     *
     * @param id id
     * @return 单个数据
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<Notice> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(noticeService.getById(id));
    }

    /**
     * 新增公告管理
     *
     * @param notice 公告管理
     * @return 是否新增成功
     */
    @SysLog("新增公告管理")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('shop:notice:save')")
    public ResponseEntity<Boolean> save(@RequestBody @Valid Notice notice) {
        notice.setShopId(SecurityUtils.getSysUser().getShopId());
        if (notice.getStatus() == 1) {
            notice.setPublishTime(new Date());
        }
        notice.setUpdateTime(new Date());
        noticeService.removeNoticeList();
        return ResponseEntity.ok(noticeService.save(notice));
    }

    /**
     * 修改公告管理
     *
     * @param notice 公告管理
     * @return 是否修改成功
     */
    @SysLog("修改公告管理")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('shop:notice:update')")
    public ResponseEntity<Boolean> updateById(@RequestBody @Valid Notice notice) {
        Notice oldNotice = noticeService.getById(notice.getId());
        if (oldNotice.getStatus() == 0 && notice.getStatus() == 1) {
            notice.setPublishTime(new Date());
        }
        notice.setUpdateTime(new Date());
        noticeService.removeNoticeList();
        noticeService.removeNoticeById(notice.getId());
        return ResponseEntity.ok(noticeService.updateById(notice));
    }

    /**
     * 通过id删除公告管理
     *
     * @param id id
     * @return 是否删除成功
     */
    @SysLog("删除公告管理")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('shop:notice:delete')")
    public ResponseEntity<Boolean> removeById(@PathVariable Long id) {
        noticeService.removeNoticeList();
        noticeService.removeNoticeById(id);
        return ResponseEntity.ok(noticeService.removeById(id));
    }

}