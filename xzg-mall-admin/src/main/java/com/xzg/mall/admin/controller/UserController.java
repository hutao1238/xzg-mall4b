package com.xzg.mall.admin.controller;

import java.util.Arrays;
import java.util.Date;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.xzg.mall.common.util.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzg.mall.bean.model.User;
import com.xzg.mall.service.UserService;

import cn.hutool.extra.emoji.EmojiUtil;


/**
 * @author hutao on 2018/10/16.
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin:user:page')")
    public ResponseEntity<IPage<User>> page(User user,PageParam<User> page) {
        IPage<User> userIPage = userService.page(page, new LambdaQueryWrapper<User>()
                .like(StrUtil.isNotBlank(user.getNickName()), User::getNickName, user.getNickName())
                .eq(user.getStatus() != null, User::getStatus, user.getStatus()));
        for (User userResult : userIPage.getRecords()) {
            userResult.setNickName(EmojiUtil.toUnicode(userResult.getNickName() == null ? "" : userResult.getNickName()));
        }
        return ResponseEntity.ok(userIPage);
    }

    /**
     * 获取信息
     */
    @GetMapping("/info/{userId}")
    @PreAuthorize("@pms.hasPermission('admin:user:info')")
    public ResponseEntity<User> info(@PathVariable("userId") String userId) {
        User user = userService.getById(userId);
        user.setNickName(EmojiUtil.toUnicode(user.getNickName() == null ? "" : user.getNickName()));
        return ResponseEntity.ok(user);
    }

    /**
     * 修改
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin:user:update')")
    public ResponseEntity<Void> update(@RequestBody User user) {
        user.setModifyTime(new Date());
        user.setNickName(EmojiUtil.toAlias(user.getNickName() == null ? "" : user.getNickName()));
        userService.updateById(user);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('admin:user:delete')")
    public ResponseEntity<Void> delete(@RequestBody String[] userIds) {
        userService.removeByIds(Arrays.asList(userIds));
        return ResponseEntity.ok().build();
    }
}
