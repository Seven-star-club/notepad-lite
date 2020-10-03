package com.shanu.notepadlite

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.design_ticket.view.*

class MainActivity : AppCompatActivity() {
    var listOfNotes = ArrayList<Notes>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Dummy Data.
        listOfNotes.add(Notes(1,"Python",
            "Python 3 is very good language as lorem ipsum sit dolor amet."))
        listOfNotes.add(Notes(2,"Javascript",
            "Javascript is very good language as lorem ipsum sit dolor amet."))
        listOfNotes.add(Notes(3,"R",
            "R is very good language as lorem ipsum sit dolor amet."))

        val myAdapter = MyNotesAdapter(listOfNotes)
        lvNotes.adapter = myAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        var searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        var searchmanager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchmanager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_SHORT).show()
                //TODO: Search Database
                return false

            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item!=null) {
            when (item.itemId) {
                R.id.addNote -> {
                    //Go to Add page
                    val intent = Intent(this,AddNotesPage::class.java)
                    startActivity(intent)

                }
            }
        }
        return super.onOptionsItemSelected(item)
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

        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.design_ticket,null)
            var myNote = listOfNotesAdapter[position]
            myView.titleBox.text = myNote.noteName
            myView.contentBox.text = myNote.noteDes

            return myView
        }

    }

}