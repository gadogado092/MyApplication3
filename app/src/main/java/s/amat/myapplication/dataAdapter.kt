package s.amat.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class dataAdapter (private val context: Context, private val dataModel : List <Model>, private val listener: (Model) -> Unit)
    : RecyclerView.Adapter<dataAdapter.NextViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = NextViewHolder(
        LayoutInflater.from(context).inflate(R.layout.adapter, parent, false
        )
    )

    override fun getItemCount(): Int = dataModel.size

    override fun onBindViewHolder(p0: dataAdapter.NextViewHolder, p1: Int) {
        p0.bindItem(dataModel[p1],listener)
    }

    class NextViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val id= view.findViewById<TextView>(R.id.id)
        private val tittle= view.findViewById<TextView>(R.id.tittle_id)


        fun bindItem(dataModel: Model, listener: (Model) -> Unit){

            id.text=dataModel.id.toString()
            tittle.text=dataModel.title.toString()




            itemView.setOnClickListener {
                listener(dataModel)
            }

        }
    }
}