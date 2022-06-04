package com.andi.surveykk

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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
    @POST("survey/update/" )
    fun edit(
        @Field("id")id:String,
        @Field("nokk")noKK:String,
        @Field("jumlahAnggota")jumlahAnggota:String,
        @Field("latitude")latitude:String,
        @Field("longitude")longitude:String,
    ): Call<SubmitModel>

    @FormUrlEncoded
    @POST("survey/destroy/")
    fun delete(
        @Field("id")id:String
    ): Call<SubmitModel>
}