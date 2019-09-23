package com.xzg.mall.quartz.service.impl;

import com.xzg.mall.quartz.dao.ScheduleJobLogMapper;
import com.xzg.mall.quartz.model.ScheduleJobLog;
import com.xzg.mall.quartz.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author hutao
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogMapper scheduleJobLogMapper;

}
