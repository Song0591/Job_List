package com.example.job_1

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.exitProcess

class InformationActivity : AppCompatActivity() {

    lateinit var pDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        val url="https://dev.priorsolution.co.th/test/job_list.json"
        AsyncTaskHandler().execute(url)

        //Button สำหรับกดกลับไป MainActivity
        val mButton = findViewById<Button>(R.id.btn_back)
        mButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java)) }

    }

    override fun onBackPressed() {
    }

    inner class AsyncTaskHandler:AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog=ProgressDialog(this@InformationActivity)
            pDialog.setMessage("Plese Wait")
            pDialog.setCancelable(false)
            pDialog.show()
        }

        override fun doInBackground(vararg url: String?): String {
            //TODO("Not yet implemented")

            val res:String
            val connection=URL(url[0]).openConnection()as HttpURLConnection
            try {
                connection.connect()
                res=connection.inputStream.use { it.reader().use { reader->reader.readText() } }
            }
            finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if(pDialog.isShowing())
                pDialog.dismiss()
            jsonResult(result)
        }


        private  fun jsonResult(jsonString: String?)
        {
            val jsonArray = JSONArray(jsonString)
            val list=ArrayList<MyData>()

            //ดึงข้อมูลจาก Main Activity
            val j = intent.extras!!.getInt("Num")
            Log.d("Num", "j_information = " + j)

            val jsonObject = jsonArray.getJSONObject(j)
            list.add(
                MyData(
                    jsonObject.getInt("jobNo"),
                    jsonObject.getString("truckLicense"),
                    jsonObject.getString("province"),
                    jsonObject.getString("truckType"),
                    jsonObject.getString("routeDt"),
                    jsonObject.getString("routeCd"),
                    jsonObject.getString("logisticPointCd"),
                    jsonObject.getString("arrivalDt"),
                    jsonObject.getString("departureDt")
                )
            )

            //Adapter
            val adapter=ListAdapter(this@InformationActivity, list)
            //List
            val joblist = findViewById<ListView>(R.id.job_list_information)
            //set adapter
            joblist.adapter=adapter
        }
    }
}

