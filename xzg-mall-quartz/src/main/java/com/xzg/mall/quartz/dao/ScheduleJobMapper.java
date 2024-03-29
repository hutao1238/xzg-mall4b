package com.xzg.mall.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzg.mall.quartz.model.ScheduleJob;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务，任务调度mapper
 * @author hutao
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

	/**
	 *  批量修改任务状态
	 * @param jobIds 调度任务id
	 * @param status 任务状态
	 * @return 修改成功条数
	 */
	int updateBatch(@Param("jobIds") Long[] jobIds, @Param("status") int status);
}