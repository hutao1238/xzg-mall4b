package com.xzg.mall.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzg.mall.sys.model.SysLog;
import com.xzg.mall.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.xzg.mall.common.util.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.hutool.core.util.StrUtil;




/**
 * 系统日志
 * @author hutao
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('sys:log:page')")
	public ResponseEntity<IPage<SysLog>> page(SysLog sysLog, PageParam<SysLog> page){
		IPage<SysLog> sysLogs = sysLogService.page(page,
				new LambdaQueryWrapper<SysLog>()
						.like(StrUtil.isNotBlank(sysLog.getUsername()),SysLog::getUsername, sysLog.getUsername())
						.like(StrUtil.isNotBlank(sysLog.getOperation()), SysLog::getOperation,sysLog.getOperation()));
		return ResponseEntity.ok(sysLogs);
	}
	
}
