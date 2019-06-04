package com.example.numbersfacts

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.numbersfacts.model.Results
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlinx.coroutines.CoroutineScope as CoroutineScope1

class MainActivity : AppCompatActivity() {
    private val TAG = "$this"


    companion object {
    val endpoint = "http://numbersapi.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//        var textVisible = main_text_view.visibility

        //clear & hide textView and CardView and ProgressBar_Widget
        progressBar.visibility = View.GONE
        cardView.visibility = View.GONE
        main_text_view.visibility = View.GONE

        fab.setOnClickListener { view ->

            val factNum = main_editText.text.toString().trim()
            if (factNum.isNotEmpty()) {
                main_text_view.text = factNum

                //start - network request to API
                numbersAPI()

                //start progress dialog
                DisplayProgressDialog()
                //start progressBar_Widget
//                progressBar.visibility = View.VISIBLE
            } else {
                Toast.makeText(this@MainActivity, "Enter a number...", Toast.LENGTH_SHORT).show()
            }

//            doWork()

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Log.d(TAG, "onOptionsItemSelected called")
        return when (item.itemId) {
//            val nextActivity = R.id.action_settings
//            if (nextActivity != null) {
//                val intent = Intent(this, PhotoDetailsActivity::class.java)
//                intent.putExtra(PHOTO_TRANSFER, photo)
//                startActivity(intent)
//            }
            R.id.action_settings -> {
                startActivity(Intent(this, BottomActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope1(context = Dispatchers.Main + viewModelJob)

    fun doWork() {
        var result = 1.0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for (i in 1..1000) {
                    result += i * i
                }
            }
        }
        Log.d("coroutines example", " result = $result")
    }

    fun cancelJob() {
        viewModelJob.cancel()
    }

    fun numbersAPI(){

        println("!!!!!!!!!!!!_Start_Network_Request_!!!!!!!!!!!!!!!")
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope1(Dispatchers.IO).launch {
            //check user input's
            val types = "math"
            val factNum1 = main_editText.text.toString().trim()
            val request = service.getPosts(factNum1, types)
            try {
                val response = request.await()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                        //cancel progressBar_Widget
//                        progressBar.visibility = View.GONE
                        //cancel progress dialog
                        pDialog.dismiss()

//                        response.body()?.let { initRecyclerView(it) }
//                        println("!!!!!!!!!!!!!!!!!!!!!!!!!!! ${response.body()}")

                        //check network API response
                        var test1 = response.body()

                        println("+++++++++ ${test1}")
                        main_text_view.text = test1?.text.toString().trim()

                        //apply VISIBLE for answer text
                        cardView.visibility = View.VISIBLE
                        main_text_view.visibility = View.VISIBLE
                    } else {
//                        toast("Error network operation failed with ${response.code()}")
                        Toast.makeText(this@MainActivity, "Error network operation failed with ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: HttpException) {
                // Catch http errors
                Log.e("REQUEST", "Exception ${e.message}")
            } catch (e: Throwable) {
                // All other exceptions (non-http)
                Log.e("REQUEST", "Ooops: Something else went wrong ${e.message}")
            }
        }
    }


    private fun initRecyclerView(list: List<Results>) {
        main_text_view.text = list.toString()




    }

    //apply progress bar dialog
    lateinit var pDialog: ProgressDialog

    fun DisplayProgressDialog() {

        pDialog = ProgressDialog(this@MainActivity)
        pDialog.setMessage("Loading..")
        pDialog.setCancelable(false)
        pDialog.isIndeterminate = false
        pDialog.show()
    }


}
