package com.android.example.batikify.data.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("data")
	val data: DataDetail? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataDetail(

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)
