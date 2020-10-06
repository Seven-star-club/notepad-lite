package com.shanu.notepadlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes_page.*
import java.lang.Exception

class AddNotesPage : AppCompatActivity() {
    val dbNotes = "Notes"
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes_page)


        try {
            var bundle: Bundle? = intent.extras
            id = bundle!!.getInt("ID", 0)
            if (id != 0) {
                etTitle.setText(bundle.getString("Name").toString())
                etDes.setText(bundle.getString("Des").toString())
            }
        }catch (ex:Exception){}

    }

    fun buAdd(view:View){
        var dbManager = databaseManager(this)
        var values = ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())

        if(id==0) {
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, "Note is added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Note adding failed", Toast.LENGTH_SHORT).show()
            }
        }else{
            var selectionArgs = arrayOf(id.toString())
            val ID = dbManager.Update(values,"ID=?",selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, "Note is added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Note adding failed", Toast.LENGTH_SHORT).show()
            }

        }
        finish()
    }
}