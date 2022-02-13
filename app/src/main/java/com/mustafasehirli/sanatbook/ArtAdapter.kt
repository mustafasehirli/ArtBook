package com.mustafasehirli.sanatbook

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.database.sqlite.SQLiteDatabase
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mustafasehirli.sanatbook.databinding.RecyclerviewRowBinding
import java.lang.Exception

class ArtAdapter(val artList : ArrayList<Art>,var mContext:Context) : RecyclerView.Adapter<ArtAdapter.ArtHolder>() {
        private var database: SQLiteDatabase
    init {
        database=mContext.openOrCreateDatabase("Arts", Context.MODE_PRIVATE,null)
        database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY, artname VARCHAR, artistname VARCHAR, year VARCHAR, image BLOB)")

    }





     class ArtHolder(val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.recylerViewTextView.text = artList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,ArtActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("id",artList[position].id)
            holder.itemView.context.startActivity(intent)
        }
        holder.binding.sil.setOnClickListener {
            val veri = artList.get(position).name
            database.execSQL("DELETE FROM arts WHERE artname='$veri'")

           val intent=Intent(mContext,MainActivity::class.java)
            intent.flags=FLAG_ACTIVITY_NO_ANIMATION
            mContext.startActivity(intent)


        }


    }

    override fun getItemCount(): Int {
        return artList.size
    }

}


