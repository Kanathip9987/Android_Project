package com.example.ffffff

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray

class Recycle_adapter(
    context: Context,
    fm: FragmentManager,
    val dataSource: JSONArray
) : RecyclerView.Adapter<Recycle_adapter.Holder>()  {

    private val thiscontext : Context = context
    private val fragment: FragmentManager = fm

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;
        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var image: ImageView
        lateinit var dia : Button
        fun Holder(){
            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.title) as TextView
            image = View.findViewById<View>(R.id.image) as ImageView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycle, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)


        holder.layout.setOnClickListener(){
            Toast.makeText(thiscontext,holder.titleTextView.text.toString(), Toast.LENGTH_SHORT).show()

            val image:String = dataSource.getJSONObject(position).getString("image").toString()
            val title:String = dataSource.getJSONObject(position).getString("title").toString()
            val description:String = dataSource.getJSONObject(position).getString("description").toString()

            val detail = fragment_detail().newInstance(image,title,description)
            val transaction : FragmentTransaction = fragment!!.beginTransaction()
            transaction.replace(com.example.ffffff.R.id.layout,detail,"detail")
            transaction.addToBackStack("detail")
            transaction.commit()
        }
    }


}