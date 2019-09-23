package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.User;

/**
 *
 * @author lgh on 2018/09/11.
 */
public interface UserService extends IService<User> {

    User getUserByUserId(String userId);
}
