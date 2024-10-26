package com.example.loaderexample

import com.example.loaderexample.constant.JobProperty
import com.example.loaderexample.constant.JobStatus
import com.example.loaderexample.job.JobExecutor
import com.example.loaderexample.util.ConfigUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.system.exitProcess

@Component
class JobRunner(
    private val autowiredCapableBeanFactory: AutowireCapableBeanFactory,
	private val jobProperty: JobProperty,
	private val configUtil: ConfigUtil
): CommandLineRunner {

	companion object {
		private val logger = LoggerFactory.getLogger(this::class.java)
		private const val DEFAULT_JOB_ID = "BE-BATCH000"
	}

	override fun run(vararg args: String?) {
		var jobId = ""
		var jobName = ""
		var status = JobStatus.FAILED

		try {
			// パラメータ設定
			jobId = args[0]?.trim() ?: DEFAULT_JOB_ID
			jobName = configUtil.getProperty("$jobId.job-name")

			// 開始ログ
			logger.info(configUtil.getLogMessage("BE0001", jobId, jobName))

			// 対象ジョブ実行
			val executor = Class.forName(configUtil.getProperty("$jobId.class-name"))
				.getConstructor(JobProperty::class.java, ConfigUtil::class.java)
				.newInstance(jobProperty, configUtil) as JobExecutor
			executor.jobId = jobId
			executor.jobName = jobName
			autowiredCapableBeanFactory.autowireBean(executor)
			status = executor.execute()
		} catch (e: IOException) {
			logger.error(configUtil.getLogMessage("BE9996"), e)
		} catch (e: Exception) {
			logger.error(configUtil.getLogMessage("BE9999", e.message), e)
		} finally {
			// 終了ログ
			logger.info(configUtil.getLogMessage("BE0002", jobId, jobName, status.code.toString()))

			// 終了
			exitProcess(status.code)
		}
    }
}
