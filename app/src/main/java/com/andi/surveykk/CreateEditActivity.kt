package com.andi.surveykk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateEditActivity : AppCompatActivity() {
    private lateinit var etId:EditText
    private lateinit var etNoKK:EditText
    private lateinit var etJumlahAgt:EditText
    private lateinit var etLatitude:EditText
    private lateinit var etLongitude:EditText
    private lateinit var btnCreate:Button
    private lateinit var btnDelete:Button
    private val api by lazy{ApiRetrofit().endpoint}
    private val survey by lazy { intent.getSerializableExtra("survey") as SurveyModel.Data }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        setupView()
        val mode = intent.getStringExtra("mode")

        if (mode=="edit"){
            etId.setText(survey.id)
            etNoKK.setText(survey.nokk)
            etJumlahAgt.setText(survey.jumlahAnggota)
            etLatitude.setText(survey.latitude)
            etLongitude.setText(survey.longitude)
            btnDelete.visibility = View.VISIBLE

            btnDelete.setOnClickListener {
                api.delete(survey.id).enqueue(object:Callback<SubmitModel>{
                    override fun onResponse(
                        call: Call<SubmitModel>,
                        response: Response<SubmitModel>
                    ) {
                        if(response.isSuccessful){
                            val result = response.body()
                            Toast.makeText(
                                applicationContext,
                                result!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }

        btnCreate.setOnClickListener {
            if (etId.text.isNotEmpty()){
                edit()
                 }else{
                 create()
            }
        }
    }

    private fun setupView(){
        etId=findViewById(R.id.et_id)
        etNoKK=findViewById(R.id.et_noKK)
        etJumlahAgt=findViewById(R.id.et_jumlah_agt)
        etLatitude=findViewById(R.id.et_latitude)
        etLongitude=findViewById(R.id.et_longitude)
        btnCreate=findViewById(R.id.button_create)
        btnDelete=findViewById(R.id.button_delete)
    }

    private fun edit(){
        if(etNoKK.text.isNotEmpty() && etJumlahAgt.text.isNotEmpty() && etLatitude.text.isNotEmpty() && etLongitude.text.isNotEmpty()){
            api.edit(
                etId.text.toString(),
                etNoKK.text.toString(),
                etJumlahAgt.text.toString(),
                etLatitude.text.toString(),
                etLongitude.text.toString(),
            ).enqueue(object : Callback<SubmitModel>{
                override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                    val result = response.body()!!.message
                    Toast.makeText(applicationContext,result,Toast.LENGTH_SHORT).show()
                    finish()
                }
                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }else{
            Toast.makeText(applicationContext,"Masukan data dahulu",Toast.LENGTH_SHORT).show()
        }
    }

    private fun create(){
        if (etNoKK.text.isNotEmpty() && etJumlahAgt.text.isNotEmpty() && etLatitude.text.isNotEmpty() && etLongitude.text.isNotEmpty() ){
            Log.e("CreateActivity","mengisi data")
            api.create(
                etNoKK.text.toString(),
                etJumlahAgt.text.toString(),
                etLatitude.text.toString(),
                etLongitude.text.toString(),
            ).enqueue(object : Callback<SubmitModel>{
                override fun onResponse(
                    call: Call<SubmitModel>,
                    response: Response<SubmitModel>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()!!.message
                        Toast.makeText(applicationContext,result,Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext,"eror",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }else{
            Toast.makeText(applicationContext,"Masukan data dahulu",Toast.LENGTH_SHORT).show()
        }
    }
}