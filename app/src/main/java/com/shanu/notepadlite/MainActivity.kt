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

        // Loading entries from database
        loadQuery("%")



    }

    override fun onResume() {
        super.onResume()
        loadQuery("%")
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun loadQuery(title:String){
        val dbConnect = databaseManager(this)
        val projections = arrayOf("ID","Title","Description")
        val selectionArgs = arrayOf(title)
        val cursor = dbConnect.Query(projections,"Title like ?",selectionArgs,"Title")
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val name = cursor.getString(cursor.getColumnIndex("Title"))
                val des = cursor.getString(cursor.getColumnIndex("Description"))
                listOfNotes.add(Notes(id,name,des))


            }while (cursor.moveToNext())
        }
        val myAdapter = MyNotesAdapter(this,listOfNotes)
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
                loadQuery("%" + query + "%")
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
        var context:Context?=null
        constructor(context: Context,listOfNotesAdapter:ArrayList<Notes>):super(){
            this.listOfNotesAdapter = listOfNotesAdapter
            this.context = context


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

            myView.deleteButton.setOnClickListener{
                var dbManager = databaseManager(this.context!!)
                val selectionArgs=arrayOf(myNote.noteId.toString())

                dbManager.Delete("ID=?",selectionArgs)
                loadQuery("%")
                Toast.makeText(this.context,"The note was deleted",Toast.LENGTH_SHORT).show()


            }
            myView.editButton.setOnClickListener (View.OnClickListener {
                GoToUpdate(myNote)


            })

            return myView
        }

    }
    fun GoToUpdate(note:Notes){
        val intent = Intent(this,AddNotesPage::class.java)
        intent.putExtra("ID",note.noteId)
        intent.putExtra("Name",note.noteName)
        intent.putExtra("Des",note.noteDes)
        startActivity(intent)

    }

}
