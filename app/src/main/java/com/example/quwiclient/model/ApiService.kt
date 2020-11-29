package com.example.quwiclient.model

import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @POST("auth/login")
    fun signin(@Body info: LoginRequest): Call<LoginResponse>

    /*@GET("projects")
    fun getProjectsList(): Call<ProjectList>*/

    @GET("projects-manage/index")
    fun getProjectsList(): Call<ProjectList>

    @GET("projects-manage/{id}")
    fun getProjectInfo(@Path("id") id : Long?) : Call<Info>

    @POST("projects-manage/update")
    fun changeProjName(@Query("id") id : Long?, @Body name: NewNameRequest): Call<Info>
}