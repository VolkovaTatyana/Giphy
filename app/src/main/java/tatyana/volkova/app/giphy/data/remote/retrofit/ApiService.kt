package tatyana.volkova.app.giphy.data.remote.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import tatyana.volkova.app.giphy.BuildConfig
import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse

interface ApiService {

    @GET("search")
    fun searchGifs(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String,
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): Single<BaseResponse>

    @GET("trending")
    fun getGifs(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): Single<BaseResponse>

    @GET
    fun getOrSearchGifs(
        @Url endPoint: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String? = null,
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0,

    ): Single<BaseResponse>
}