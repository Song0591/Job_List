//ListApapter สำหรับ MainActivity
package com.example.job_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter2 (val  context: Context, val list2:ArrayList<MyData2>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //TODO("Not yet implemented")
        val view: View = LayoutInflater.from(context).inflate(R.layout.row_layout2,parent,false)
        val jobNo = view.findViewById<TextView>(R.id.Text_jobNo)
        val truckLicense = view.findViewById<TextView>(R.id.Text_truckLicense)

        jobNo.text=list2[position].jobNO.toString()
        truckLicense.text=list2[position].truckLicense
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
        return list2.size
    }

}