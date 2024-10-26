package com.example.loaderexample.constant

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleCloudConstants {

	@Value("\${gcp.url}")
	val url: String = ""

	@Value("\${gcp.project-id}")
	val projectId = ""

	@Value("\${gcp.cloud-storage.bucket-name}")
	val bucketName = ""

	@Value("\${gcp.cloud-storage.download-path}")
	val downloadPath = ""
}
