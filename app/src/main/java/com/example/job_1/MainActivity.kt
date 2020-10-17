package com.example.job_1

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    lateinit var pDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url="https://dev.priorsolution.co.th/test/job_list.json"
        AsyncTaskHandler().execute(url)

    }

    //ทำการปิดโปรแกรมด้วยการกดกลับ
    private var backPressTime=0L;
    override fun onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            Toast.makeText(applicationContext,"Press back again to exit application",Toast.LENGTH_SHORT)
        }
        backPressTime = System.currentTimeMillis()
    }


    inner class AsyncTaskHandler:AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog=ProgressDialog(this@MainActivity)
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
            val list2=ArrayList<MyData2>()
            var  i=0
            while (i<jsonArray.length())
            {
                val jsonObject = jsonArray.getJSONObject(i)
                list2.add(
                        MyData2(
                                jsonObject.getInt("jobNo"),
                                jsonObject.getString("truckLicense"),
                        )
                )
                i++
            }

            //Adapter
            val adapter=ListAdapter2(this@MainActivity, list2)
            //List
            val joblist = findViewById<ListView>(R.id.job_list_main)
            //set adapter
            joblist.adapter=adapter

            //List item click
            joblist.setOnItemClickListener{ parent, view, position, id->
                var  j=0
                while (j<jsonArray.length())
                {
                    if (position==j){
                        //ทดสอบ
                        Log.d("Num", "j_main = " + j)

                        val intent = Intent(this@MainActivity, InformationActivity::class.java)
                        intent.putExtra("Num", j)
                        startActivity(intent)
                        finish ()
                    }
                    j++
                }
            }
        }
    }
}
