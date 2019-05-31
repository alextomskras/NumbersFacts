package com.example.numbersfacts


import com.example.numbersfacts.R.*
import com.example.numbersfacts.model.Responses
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

//private var posts = id.main_editText
//val poster
//    get() = ($main_editText)

interface RetrofitService {


    @GET("/25?json")
    fun getPosts(): Deferred<Response<List<Responses>>>
}