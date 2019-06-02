package com.example.numbersfacts

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.numbersfacts.model.Request
import com.example.numbersfacts.model.TmdbMovieResponse

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {


      companion object{
    val endpoint = "http://numbersapi.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->

            val factNum = main_editText.text.toString().trim()
            if (factNum.isNotEmpty()) {
                numbersAPI()
                main_text_view.text = factNum
            } else {
                Toast.makeText(this@MainActivity, "Enter a number...", Toast.LENGTH_SHORT).show()
            }

            doWork()

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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(context = Dispatchers.Default + viewModelJob)

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

        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val factNum1 = main_editText.text.toString().trim()
            val request = service.getPosts(factNum1)
            try {
                val response = request.await()
                withContext(Dispatchers.Default) {
                    if (response.isSuccessful) {
//                        response.body()?.let { initRecyclerView(it) }
//                        println("!!!!!!!!!!!!!!!!!!!!!!!!!!! ${response.body()}")
                        var test1 = response.body()?: listOf()
                        println("!!!!!!!!!!!!!!!!!!!!!!!!!!! ${test1}")
                    } else {
//                        toast("Error network operation failed with ${response.code()}")
                        Toast.makeText(this@MainActivity, "Error network operation failed with ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: HttpException) {
                Log.e("REQUEST", "Exception ${e.message}")
            } catch (e: Throwable) {
                Log.e("REQUEST", "Ooops11: Something else went wrong")
            }
        }
    }


    private fun initRecyclerView(list: List<Request>) {
        main_text_view.text = list.toString()




    }


}
