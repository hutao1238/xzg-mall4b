package com.xzg.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzg.mall.bean.model.User;

/**
 *
 * @author hutao
 */
public interface UserService extends IService<User> {

    User getUserByUserId(String userId);
}
