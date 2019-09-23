package com.xzg.mall.quartz.controller;

import javax.validation.Valid;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzg.mall.common.exception.XzgShopBindException;
import com.xzg.mall.quartz.model.ScheduleJob;
import com.xzg.mall.quartz.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.xzg.mall.common.util.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzg.mall.common.annotation.SysLog;

import cn.hutool.core.util.StrUtil;



/**
 * 定时任务
 * @author hutao
 */
@Slf4j
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('sys:schedule:page')")
	public ResponseEntity<IPage<ScheduleJob>> page(String beanName, PageParam<ScheduleJob> page){
		IPage<ScheduleJob> scheduleJobs = scheduleJobService.page(page,new LambdaQueryWrapper<ScheduleJob>().like(StrUtil.isNotBlank(beanName),ScheduleJob::getBeanName,beanName));
		return ResponseEntity.ok(scheduleJobs);
	}
	
	/**
	 * 定时任务信息
	 */
	@GetMapping("/info/{jobId}")
	@PreAuthorize("@pms.hasPermission('sys:schedule:info')")
	public ResponseEntity<ScheduleJob> info(@PathVariable("jobId") Long jobId){
		ScheduleJob schedule = scheduleJobService.getById(jobId);
		return ResponseEntity.ok(schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys:schedule:save')")
	public ResponseEntity<Void> save(@RequestBody @Valid ScheduleJob scheduleJob){

		int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()));
		if (dbAlikeCount > 0) {
			throw new XzgShopBindException("定时任务已存在");
		}

		scheduleJobService.saveAndStart(scheduleJob);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys:schedule:update')")
	public ResponseEntity<Void> update(@RequestBody @Valid ScheduleJob scheduleJob){

		int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()).ne(ScheduleJob::getJobId,scheduleJob.getJobId()));
		if (dbAlikeCount > 0) {
			throw new XzgShopBindException("定时任务已存在");
		}

		scheduleJobService.updateScheduleJob(scheduleJob);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@DeleteMapping
	@PreAuthorize("@pms.hasPermission('sys:schedule:delete')")
	public ResponseEntity<Void> delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@PostMapping("/run")
	@PreAuthorize("@pms.hasPermission('sys:schedule:run')")
	public ResponseEntity<Void> run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@PostMapping("/pause")
	@PreAuthorize("@pms.hasPermission('sys:schedule:pause')")
	public ResponseEntity<Void> pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@PostMapping("/resume")
	@PreAuthorize("@pms.hasPermission('sys:schedule:resume')")
	public ResponseEntity<Void> resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		return ResponseEntity.ok().build();
	}

}
