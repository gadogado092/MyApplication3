package s.amat.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvKata: RecyclerView
    private lateinit var adapter: dataAdapter
    private var listData : MutableList<Model> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter= dataAdapter(applicationContext,listData){
            val intent = Intent(applicationContext, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("selected", it)
            intent.putExtra("myBundle", bundle)
            startActivity(intent)


        }

        rvKata= findViewById(R.id.list)
        rvKata.layoutManager = LinearLayoutManager(applicationContext)
        rvKata.setHasFixedSize(true)
        rvKata.adapter=adapter



        showData()
    }

    private fun showData(){
        val categoryServices = DataRepository.create()
        categoryServices.getData().enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                if (response.isSuccessful) {

                    listData.clear()
                    val data = response.body()

                    data?.let { listData.addAll(it) }
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Log.d("tag","errornya = ${t.message}")
            }

        })
    }
}
