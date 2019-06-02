package com.example.numbersfacts


import com.example.numbersfacts.model.Request
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//private var posts = id.main_editText
//val poster
//    get() = ($main_editText)


//A retrofit Network Interface for the Api
interface RetrofitService {


    @GET(value = "{numbers}?json")
    fun getPosts(@Path("numbers") numbers: String): Deferred<Response<Request>>
}