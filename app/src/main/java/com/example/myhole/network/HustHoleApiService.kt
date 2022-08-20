package com.example.myhole.network


import com.example.myhole.model.Hole
import com.example.myhole.model.Interact
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 *@classname HustHoleApiService
 * @description:
 * @date :2022/8/17 10:33
 * @version :1.0
 * @author
 */
private const val BASE_URL = "https://hustholev2.pivotstudio.cn/api/"

private val okHttpClient = OkHttpClient.Builder()
    .readTimeout(10, TimeUnit.SECONDS)
    .connectTimeout(10, TimeUnit.SECONDS)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface HustHoleApiService {
    @Headers("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoiOTA3NyIsImV4cCI6MTY2MzM3Njk1MSwidGltZXN0YW1wIjoiMTY0OTU3NTIyOCJ9.YZYiOXZL57-mlC81kgbnZdDAr513cCJa2ccg9TJB-Y4")
    @GET("hole/list")
    suspend fun getHoleList(
        @Query("timestamp") timestamp: String,
        @Query("offset") offset: Int = 0,
        @Query("mode") mode: String = "LATEST_REPLY",
        @Query("limit") getHoleNum: Int = 20
    ): List<Hole>

    @Headers("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoiOTA3NyIsImV4cCI6MTY2MzM3Njk1MSwidGltZXN0YW1wIjoiMTY0OTU3NTIyOCJ9.YZYiOXZL57-mlC81kgbnZdDAr513cCJa2ccg9TJB-Y4")
    @GET("hole/one")
    fun getOneHole(@Body data: Interact): Hole

    @Headers("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoiOTA3NyIsImV4cCI6MTY2MzM3Njk1MSwidGltZXN0YW1wIjoiMTY0OTU3NTIyOCJ9.YZYiOXZL57-mlC81kgbnZdDAr513cCJa2ccg9TJB-Y4")
    @POST("interact/like")
    fun postInteractLike(@Body data: Interact): Call<ResponseBody>

    @Headers("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoiOTA3NyIsImV4cCI6MTY2MzM3Njk1MSwidGltZXN0YW1wIjoiMTY0OTU3NTIyOCJ9.YZYiOXZL57-mlC81kgbnZdDAr513cCJa2ccg9TJB-Y4")
    @POST("interact/unlike")
    fun postInteractUnLike(@Body data: Interact): Call<ResponseBody>

    @Headers("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoiOTA3NyIsImV4cCI6MTY2MzM3Njk1MSwidGltZXN0YW1wIjoiMTY0OTU3NTIyOCJ9.YZYiOXZL57-mlC81kgbnZdDAr513cCJa2ccg9TJB-Y4")
    @POST("interact/follow")
    fun postInteractFollow(@Body data: Interact): Call<ResponseBody>

    @Headers("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoiOTA3NyIsImV4cCI6MTY2MzM3Njk1MSwidGltZXN0YW1wIjoiMTY0OTU3NTIyOCJ9.YZYiOXZL57-mlC81kgbnZdDAr513cCJa2ccg9TJB-Y4")
    @POST("interact/unfollow")
    fun postInteractUnFollow(@Body data: Interact): Call<ResponseBody>
}

object HustHoleApi {
    val retrofitService: HustHoleApiService by lazy {
        retrofit.create(HustHoleApiService::class.java)
    }
}