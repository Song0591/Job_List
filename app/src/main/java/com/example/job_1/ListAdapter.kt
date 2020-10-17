//ListApapter สำหรับ InformationActivity
package com.example.job_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(val  context: Context,val list:ArrayList<MyData>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //TODO("Not yet implemented")
        val view:View=LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false)
        val jobNo = view.findViewById<TextView>(R.id.Text_jobNo)
        val truckLicense = view.findViewById<TextView>(R.id.Text_truckLicense)
        val province = view.findViewById<TextView>(R.id.Text_province)
        val truckType = view.findViewById<TextView>(R.id.Text_truckType)
        val routeDt = view.findViewById<TextView>(R.id.Text_routeDt)
        val routeCd = view.findViewById<TextView>(R.id.Text_routeCd)
        val logisticPointCd = view.findViewById<TextView>(R.id.Text_logisticPointCd)
        val arrivalDt = view.findViewById<TextView>(R.id.Text_arrivalDtd)
        val departureDt = view.findViewById<TextView>(R.id.Text_departureDt)

        jobNo.text=list[position].jobNO.toString()
        truckLicense.text=list[position].truckLicense
        province.text=list[position].province
        truckType.text=list[position].truckType
        routeDt.text=list[position].routeDt
        routeCd.text=list[position].routeCd
        logisticPointCd.text=list[position].logisticPointCd
        arrivalDt.text=list[position].arrivalDt
        departureDt.text=list[position].departureDt
        return  view
    }

    override fun getItem(position: Int): Any {
        //TODO("Not yet implemented")
        return position
    }

    override fun getItemId(position: Int): Long {
        //TODO("Not yet implemented")
        return position.toLong()
    }

    override fun getCount(): Int {
        //TODO("Not yet implemented")
        return list.size
    }

}