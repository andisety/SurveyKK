package com.andi.surveykk

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val api by lazy{ApiRetrofit().endpoint}

    private lateinit var surveyAdapter:SurveyAdapter
    private lateinit var listSurvey: RecyclerView
    private lateinit var fabCreate: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listSurvey=findViewById(R.id.recycler)
        fabCreate=findViewById(R.id.fab_add)
        fabCreate.setOnClickListener {
            startActivity(
                Intent(this,CreateEditActivity::class.java)
                    .putExtra("mode","add")
            )
        }
    }

    override fun onStart() {
        super.onStart()
        getSurvey()
    }

    private fun getSurvey(){
        surveyAdapter= SurveyAdapter(arrayListOf(),object : SurveyAdapter.OnAdapterListener{
            override fun onEdit(surve: SurveyModel.Data) {
                startActivity(
                    Intent(this@MainActivity,CreateEditActivity::class.java)
                        .putExtra("survey",surve)
                        .putExtra("mode","edit")
                )
            }

            override fun onEditLong(surve: SurveyModel.Data) {
                dialogPopup(surve)
            }
        })
        api.data().enqueue(object: Callback<SurveyModel>{
            override fun onResponse(call: Call<SurveyModel>, response: Response<SurveyModel>) {
                if (response.isSuccessful){
                    surveyAdapter.setData(response.body()!!.survey)
                }else{
                    Log.e("MainActivity","eror set data")
                }
            }
            override fun onFailure(call: Call<SurveyModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
        listSurvey.adapter = surveyAdapter
    }
    private fun dialogPopup(surve: SurveyModel.Data){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Maps")
        val item = arrayOf("Maps","Edit")
        builder.setItems(item,DialogInterface.OnClickListener { dialog, which ->
            when(which){
                0-> startActivity(Intent(this,MapsActivity::class.java)
                    .putExtra("survey",surve)
                )
                1-> startActivity(
                    Intent(this@MainActivity,CreateEditActivity::class.java)
                        .putExtra("survey",surve)
                        .putExtra("mode","edit")
                )
            }
        })
        builder.show()
    }
}