package com.example.loaderexample.job

import com.example.loaderexample.constant.JobProperty
import com.example.loaderexample.constant.JobStatus
import com.example.loaderexample.service.SchoolLunchService
import com.example.loaderexample.util.ConfigUtil
import org.springframework.beans.factory.annotation.Autowired

class SchoolLunchDataLoader(
	jobProperty: JobProperty,
	configUtil: ConfigUtil,
): JobExecutor(jobProperty, configUtil) {

	@Autowired
	lateinit var schoolLunchService: SchoolLunchService

	override fun process(): JobStatus {
		return try {
			schoolLunchService.loadData()
			JobStatus.SUCCESS
		} catch (e: Exception) {
			JobStatus.FAILED
			throw e
		}
	}
}
