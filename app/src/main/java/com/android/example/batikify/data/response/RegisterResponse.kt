package com.android.example.batikify.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
	@field:SerializedName("data")
	val data: DataRegister? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("errors")
	val errors: List<ErrorResponse>? = null
)

data class DataRegister(
	@field:SerializedName("userId")
	val userId: String? = null
)

data class ErrorResponse(
	@field:SerializedName("field")
	val field: String,

	@field:SerializedName("message")
	val message: String
)
