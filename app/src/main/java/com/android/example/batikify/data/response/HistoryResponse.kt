package com.android.example.batikify.data.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("data")
	val data: List<DataItemHistory?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemHistory(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
