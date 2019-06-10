package com.example.numbersfacts


import com.example.numbersfacts.model.Request
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//private var posts = id.main_editText
//val poster
//    get() = ($main_editText)

//http://numbersapi.com/number/type


//A retrofit Network Interface for the Api
interface DateRetrofitService {


    @GET(value = "{numbers}/{types}?json")
    fun getPosts(
        @Path("numbers") numbers: String,
        @Path("types") types: String


    ): Deferred<Response<Request>>

//    @GET(value = "{dateYear}?json")
//    fun getPosts(@Path("dateYear") dateYear: String): Deferred<Response<Request>>
}