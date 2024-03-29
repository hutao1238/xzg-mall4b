package com.xzg.mall.quartz.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzg.mall.quartz.model.ScheduleJobLog;
import com.xzg.mall.quartz.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.xzg.mall.common.util.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 定时任务日志
 * @author hutao
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('sys:schedule:log')")
	public ResponseEntity<IPage<ScheduleJobLog>> page(Long jobId, PageParam<ScheduleJobLog> page){
		IPage<ScheduleJobLog> list = scheduleJobLogService.page(page,new LambdaQueryWrapper<ScheduleJobLog>().eq(jobId != null,ScheduleJobLog::getJobId,jobId));
		return ResponseEntity.ok(list);
	}
}
