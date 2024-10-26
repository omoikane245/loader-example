package com.example.loaderexample.job

import com.example.loaderexample.constant.JobProperty
import com.example.loaderexample.constant.JobStatus
import com.example.loaderexample.exception.JobException
import com.example.loaderexample.util.ConfigUtil
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import kotlin.jvm.Throws

abstract class JobExecutor(
	private val jobProperty: JobProperty,
	private val configUtil: ConfigUtil
) {

	companion object {
		private val logger = LoggerFactory.getLogger(this::class.java)
	}

	internal lateinit var jobId: String

	internal lateinit var jobName: String

	fun execute(): JobStatus {
		var result: JobStatus

		try {
			// 多重起動チェック
			if (isDuplex()) {
				logger.error(configUtil.getLogMessage("BE9998"))
				throw JobException(jobId)
			}

			// ジョブ起動
			writeDuplexFlag()
			result = process()
		} catch (e: JobException) {
			logger.error(configUtil.getLogMessage("BE9997"))
			result = e.result
		} finally {
			clearDuplexFlag()
		}

		return result;
	}

	@Throws(JobException::class)
	protected abstract fun process(): JobStatus

	private fun isDuplex(): Boolean {
		return try {
			Files.exists(Path.of(jobProperty.duplexFlagPath))
		} catch (e: Exception) {
			false
		}
	}

	private fun writeDuplexFlag() {
		Files.newBufferedWriter(Path.of(jobProperty.duplexFlagPath), Charsets.UTF_8).use {
			it.write("$jobId processing")
		}
	}

	private fun clearDuplexFlag() {
		Files.deleteIfExists(Path.of(jobProperty.duplexFlagPath))
	}
}
