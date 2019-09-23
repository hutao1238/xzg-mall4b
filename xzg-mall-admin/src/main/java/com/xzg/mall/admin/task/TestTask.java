package com.xzg.mall.admin.task;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

    public void test1(){
        System.out.println(DateUtil.now() + ": test1 running.................");

    }

    public void test2(String param){
        System.out.println(DateUtil.now() + ": test2 running.................");
    }
}
