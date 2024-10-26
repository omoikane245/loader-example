package com.example.loaderexample.config

import com.example.loaderexample.constant.GoogleCloudConstants
import com.google.cloud.NoCredentials
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleCloudStorageConfig(
	private val googleCloudConstants: GoogleCloudConstants
) {

	@Bean
	fun storage(): Storage {
		return StorageOptions.newBuilder()
			.setHost(googleCloudConstants.url)
			.setProjectId(googleCloudConstants.projectId)
			.setCredentials(NoCredentials.getInstance())
			.build()
			.service
			?: throw Exception("GoogleCloudStorageクライアント設定エラー")
	}
}
