package s.amat.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : Activity() {

    private lateinit var dataModel: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.getBundleExtra("myBundle")


        if (bundle!=null){
            dataModel = bundle.getParcelable("selected") as Model

            val Services = DataRepository.create()
            dataModel.id?.let {
                Services.getItem(it).enqueue(object : Callback<Model> {
                    override fun onResponse(call: Call<Model>, response: Response<Model>) {
                        if (response.isSuccessful) {
                            textView.text=response.body()?.title
                        }
                    }

                    override fun onFailure(call: Call<Model>, t: Throwable) {
                        Log.d("Kampret","errornya = ${t.message}")
                    }

                })
            }

        }
    }
}
