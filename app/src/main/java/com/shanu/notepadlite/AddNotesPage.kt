package com.shanu.notepadlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes_page.*

class AddNotesPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes_page)
    }

    fun buAdd(view:View){
        var dbManager = databaseManager(this)
        var values = ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())
        val ID = dbManager.Insert(values)
        if(ID>0){
            Toast.makeText(this,"Note is added successfully",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Note adding failed",Toast.LENGTH_SHORT).show()


        }
        finish()
    }
}