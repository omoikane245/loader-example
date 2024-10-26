package com.example.loaderexample.util

import com.example.loaderexample.constant.GoogleCloudConstants
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.Storage
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Path
import kotlin.io.path.pathString
import kotlin.jvm.Throws

@Component
class GoogleCloudStorageUtil(
	private val storage: Storage,
	private val googleCloudConstants: GoogleCloudConstants
) {

	@Throws(IOException::class)
	fun download(fileName: String): Path {
		val path = Path.of("${googleCloudConstants.downloadPath}/$fileName")
		storage.reader(BlobId.of(googleCloudConstants.bucketName, fileName)).use { rc ->
			FileOutputStream(path.pathString).use { fos ->
				fos.channel.transferFrom(rc,0, Long.MAX_VALUE)
			}
		}
		return path
	}
}
