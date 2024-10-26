package com.example.loaderexample.service

import com.example.loaderexample.repository.SchoolLunchRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchoolLunchService(
	private val schoolLunchRepository: SchoolLunchRepository
) {

	@Transactional(rollbackFor = [Exception::class])
	fun loadData() {
		schoolLunchRepository.deleteAll()
		// NOTE 実際には日付部分は動的にする必要がありそうだが、今回はやらない
		val path = schoolLunchRepository.download("202102kondate.csv")
		schoolLunchRepository.loadData(path, "BE-BATCH001")
	}
}
