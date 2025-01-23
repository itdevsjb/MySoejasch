package com.tpsmedia.mysoejasch.api
import com.tpsmedia.mysoejasch.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SlugInterface {
    @GET("mslug")
    suspend fun getSlug(
        @Header("Authorization") authorization: String,
        @Query("jenis") jenis: String,
        @Query("parameter") parameter: String
    ): ApiResponse
}
