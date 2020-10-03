package com.shanu.notepadlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class databaseManager {
    val dbName:String = "MyNotes"
    val dbTable = "Notes"
    val colID = "ID"
    val colTitle = "Title"
    val colDes = "Description"
    val dbVersion = 1
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + colID + " INTEGER PRIMARY KEY, "+
            colTitle + " TEXT, " + colDes + " TEXT);"
    var sqlDB:SQLiteDatabase?=null

    constructor(context: Context){
        val db = DatabaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }
    // Created inner class
    inner class DatabaseHelperNotes: SQLiteOpenHelper {
        var context:Context?=null
        constructor(context:Context):super(context,dbName,null,dbVersion){
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context," Database is created ",Toast.LENGTH_SHORT).show()



        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS $dbTable")
        }

    }
    fun Insert(value:ContentValues):Long{
        val ID = sqlDB!!.insert(dbTable,"",value)
        return ID
    }
}