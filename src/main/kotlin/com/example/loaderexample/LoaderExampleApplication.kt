package com.example.loaderexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class LoaderExampleApplication

fun main(args: Array<String>) {
	runApplication<LoaderExampleApplication>(*args).close()
}
