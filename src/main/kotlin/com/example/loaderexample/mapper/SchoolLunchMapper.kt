package com.example.loaderexample.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface SchoolLunchMapper {

	fun loadData(@Param("filePath") filePath: String, @Param("operatorId") operatorId: String)

	fun deleteAll()
}
