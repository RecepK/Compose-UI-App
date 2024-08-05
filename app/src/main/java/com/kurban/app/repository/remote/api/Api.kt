package com.kurban.app.repository.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("isOpen.json")
    suspend fun isOpen(): Response<ResponseModel>
}