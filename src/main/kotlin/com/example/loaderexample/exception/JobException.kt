package com.example.loaderexample.exception

import com.example.loaderexample.constant.JobStatus

class JobException(
	val jobId: String,
	val result: JobStatus = JobStatus.FAILED,
): Exception()
