package com.android.example.batikify.data.response

import com.google.gson.annotations.SerializedName

data class DetectionResponse(

	@field:SerializedName("data")
	val data: DataDetection? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataDetection(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("historyId")
	val historyId: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null
)
