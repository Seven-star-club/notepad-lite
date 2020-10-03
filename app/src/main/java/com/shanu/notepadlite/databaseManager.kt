package com.shanu.notepadlite

class databaseManager {
    val dbName:String = "MyNotes"
    val dbTable = "Notes"
    val colID = "ID"
    val colTitle = "Title"
    val colDes = "Description"
    val dbVersion = 1
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS" + dbTable + " (" + colID + " INTEGER PRIMARY KEY, "+
            colTitle + " TEXT, " + colDes + " TEXT);"

    constructor(){
    }
}