package com.android.example.batikify.data.response

import com.google.gson.annotations.SerializedName

data class EncyclopediaResponse(

	@field:SerializedName("data")
	val data: List<DataItemEncyclopedia?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemEncyclopedia(

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
