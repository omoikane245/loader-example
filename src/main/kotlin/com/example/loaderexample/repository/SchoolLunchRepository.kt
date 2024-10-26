package com.example.loaderexample.repository

import com.example.loaderexample.mapper.SchoolLunchMapper
import com.example.loaderexample.util.ConfigUtil
import com.example.loaderexample.util.GoogleCloudStorageUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.io.path.pathString

@Repository
class SchoolLunchRepository(
	private val schoolLunchMapper: SchoolLunchMapper,
	private val googleCloudStorageUtil: GoogleCloudStorageUtil,
	private val configUtil: ConfigUtil
) {

	companion object {
		private val logger = LoggerFactory.getLogger(this::class.java)
	}

	fun download(fileName: String): Path {
		logger.info(configUtil.getLogMessage("BE0003", fileName))
		return googleCloudStorageUtil.download(fileName)
	}

	fun loadData(filePath: Path, operatorId: String) {
		logger.info(configUtil.getLogMessage("BE0004", "TBL_SCHOOL_LUNCH", filePath.fileName.name))
		schoolLunchMapper.loadData(filePath.pathString, operatorId)
	}

	fun deleteAll() {
		logger.info(configUtil.getLogMessage("BE0005", "TBL_SCHOOL_LUNCH"))
		schoolLunchMapper.deleteAll()
	}
}
