package com.andi.surveykk
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SurveyAdapter(
    val survey:ArrayList<SurveyModel.Data>,
    val listener:OnAdapterListener
):RecyclerView.Adapter<SurveyAdapter.ViewHolder>() {


    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.et_id)
        val nokk = view.findViewById<TextView>(R.id.tv_noKK)
        val jmlAgt = view.findViewById<TextView>(R.id.tv_jml_agt)
        val latitude = view.findViewById<TextView>(R.id.tv_latitude)
        val longitude = view.findViewById<TextView>(R.id.tv_longitude)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=survey[position]
        holder.id?.text = " : "+data.id
        holder.nokk.text = " : "+data.nokk
        holder.jmlAgt.text = " : "+data.jumlahAnggota
        holder.latitude.text = " : "+data.latitude
        holder.longitude.text = " : "+data.longitude
        holder.itemView.setOnClickListener {
            listener.onEdit(data)
        }

    }

    override fun getItemCount(): Int {
        return survey.size
    }

    fun setData(data:List<SurveyModel.Data>){
        survey.clear()
        survey.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onEdit(surve:SurveyModel.Data)
    }
}