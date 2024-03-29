package com.xzg.mall.quartz.util;

import cn.hutool.core.util.StrUtil;
import com.xzg.mall.common.util.SpringContextUtils;
import com.xzg.mall.quartz.model.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;


/**
 * 定时任务spring bean 执行定时任务
 * @author hutao
 */
@Slf4j
public class SpringBeanTaskUtil {
	
	public static void invokeMethod(ScheduleJob scheduleJob) {
		Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());
		try {
			if (StrUtil.isNotEmpty(scheduleJob.getParams())) {
				Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethodName(), String.class);
				ReflectionUtils.makeAccessible(method);
				method.invoke(target, scheduleJob.getParams());
			} else {
				Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethodName());
				ReflectionUtils.makeAccessible(method);
				method.invoke(target);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行定时任务失败", e);
		}
	}
}
