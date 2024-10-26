package com.example.loaderexample.constant

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("job")
data class JobProperty @ConstructorBinding constructor(
	val duplexFlagPath: String,
	val debugMode: Boolean
)
