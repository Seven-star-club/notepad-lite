package com.shanu.notepadlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class MainActivity : AppCompatActivity() {
    var listOfNotes = ArrayList<Notes>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Dummy Data.
        listOfNotes.add(Notes(1,"Python","Python 3 is very good language as lorem ipsum sit dolor amet."))
        listOfNotes.add(Notes(2,"Javascript","Javascript is very good language as lorem ipsum sit dolor amet."))
        listOfNotes.add(Notes(3,"R","R is very good language as lorem ipsum sit dolor amet."))


    }
    inner class MyNotesAdapter: BaseAdapter {
        var listOfNotesAdapter=ArrayList<Notes>()
        constructor(listOfNotesAdapter:ArrayList<Notes>):super(){
            this.listOfNotesAdapter = listOfNotesAdapter


        }

        override fun getCount(): Int {
            return listOfNotesAdapter.size
        }

        override fun getItem(position: Int): Any {
            return listOfNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            TODO("Not yet implemented")
        }

    }

}