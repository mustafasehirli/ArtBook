package com.mustafasehirli.sanatbook

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafasehirli.sanatbook.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var artList:ArrayList<Art>
    private lateinit var artAdapter:ArtAdapter
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        artList= ArrayList<Art>()



        artAdapter= ArtAdapter(artList,this)
        binding.recylerView.layoutManager=LinearLayoutManager(this)
        binding.recylerView.adapter=artAdapter
         try {
                database=this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE,null )

                val cursor = database.rawQuery("SELECT * FROM arts",null )
                val artNameIx=cursor.getColumnIndex("artname")
                val idIx =cursor.getColumnIndex("id")

                while (cursor.moveToNext()){
                    val name = cursor.getString(artNameIx)
                    val id = cursor.getInt(idIx)
                    val art =Art(name, id)
                    artList.add(art)
                 }
                    artAdapter.notifyDataSetChanged()
                    cursor.close()

             }catch (e:Exception){
                e.printStackTrace()
             }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.art_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId==R.id.add_art_item){
                val intent=Intent(this@MainActivity,ArtActivity::class.java)
                intent.putExtra("info","new")
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                startActivity(intent)
            }
        return super.onOptionsItemSelected(item)
    }





}