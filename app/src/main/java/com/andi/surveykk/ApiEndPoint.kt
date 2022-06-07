package com.andi.surveykk

import retrofit2.Call
import retrofit2.http.*

interface  ApiEndPoint {

    @GET("survey")
    fun data():Call<SurveyModel>
    @FormUrlEncoded
    @POST("survey/store")
    fun create(
        @Field("nokk")noKK:String,
        @Field("jumlahAnggota")jumlahAnggota:String,
        @Field("latitude")latitude:String,
        @Field("longitude")longitude:String,
    ): Call<SubmitModel>

    @FormUrlEncoded
    @POST("survey/update/{id}" )
    fun edit(
        @Path("id")id:String,
        @Field("nokk")noKK:String,
        @Field("jumlahAnggota")jumlahAnggota:String,
        @Field("latitude")latitude:String,
        @Field("longitude")longitude:String,
    ): Call<SubmitModel>

    @FormUrlEncoded
    @POST("survey/destroy/{id}")
    fun delete(
        @Path("id") id: String
    ): Call<SubmitModel>
}