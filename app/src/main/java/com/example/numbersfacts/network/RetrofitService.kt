package com.example.numbersfacts


import com.example.numbersfacts.R.*
import com.example.numbersfacts.model.Request
import com.example.numbersfacts.model.Responses
import com.example.numbersfacts.model.Results
import com.example.numbersfacts.model.TmdbMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//private var posts = id.main_editText
//val poster
//    get() = ($main_editText)


//A retrofit Network Interface for the Api
interface RetrofitService {


    @GET(value = "{numbers}")
    fun getPosts(@Path("numbers") numbers:String) : Deferred<Response<List<Results>>>
}