package com.kurban.app.repository.remote.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseModel(
    @SerializedName("open")
    var isOpen: Boolean? = null
) : Serializable