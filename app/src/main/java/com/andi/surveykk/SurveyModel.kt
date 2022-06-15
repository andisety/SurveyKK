package com.andi.surveykk

import java.io.Serializable

data class SurveyModel (val survey:List<Data>){
    data class Data(
        val id:String?,
        val nokk:String?,
        val jumlahAnggota:String?,
        val latitude:String?,
        val longitude:String?):Serializable

    data class SubmitResponse(val message:String)
}