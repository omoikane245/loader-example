package com.example.loaderexample.job

import com.example.loaderexample.constant.JobProperty
import com.example.loaderexample.constant.JobStatus
import com.example.loaderexample.util.ConfigUtil
import org.slf4j.LoggerFactory

class SampleJob(
	jobProperty: JobProperty,
	configUtil: ConfigUtil
): JobExecutor(jobProperty, configUtil) {

	companion object {
		private val logger = LoggerFactory.getLogger(this::class.java)
	}

	override fun process(): JobStatus {
		logger.info("サンプル開始")
		val result = JobStatus.SUCCESS
		logger.info("サンプル終了")
		return result
	}
}
