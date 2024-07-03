package com.jarica.chronogol.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PuntuationDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("goals") val goals: Int= 0
)
