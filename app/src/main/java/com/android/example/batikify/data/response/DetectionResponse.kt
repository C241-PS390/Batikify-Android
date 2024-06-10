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

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)
