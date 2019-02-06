package s.amat.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Services {

    @GET("todos")
    fun getData(): Call<List<Model>>

    @GET("todos/{id}")
    fun getItem(@Path("id") name: Int): Call<Model>
}