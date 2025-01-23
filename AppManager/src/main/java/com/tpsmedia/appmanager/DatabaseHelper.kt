package com.tpsmedia.appmanager

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//import com.tpsmedia.appmanager.model.PROnlineModel

// Model untuk data PR Online
data class PROnlineModel(val UCode_PPB: String, val No_PPB: String, val Ket: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "SoejaschDB.db"
        private const val DATABASE_VERSION = 1
//        private const val TABLE_NAME = "posts"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE IF NOT EXISTS tb_mt_PPB (" +
                "UCode_PPB TEXT PRIMARY KEY, " +
                "No_PPB TEXT, " +
                "Ket TEXT, " +
                "Tgl_PPB TEXT, " +
                "status_approval TEXT, " +
                "approval_1 TEXT, " +
                "approval_2 TEXT, " +
                "approval_3 TEXT, " +
                "approval_4 TEXT, " +
                "Nama_Dept TEXT, " +
                "remarks TEXT )"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS tb_mt_PPB")
        onCreate(db)
    }

    // Mendapatkan semua posting
    @SuppressLint("Range")
    fun getAllPosts(): ArrayList<PROnlineModel> {
        val posts = ArrayList<PROnlineModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tb_mt_PPB", null)

        if (cursor.moveToFirst()) {
            do {
                val post = PROnlineModel(
                    UCode_PPB = cursor.getString(cursor.getColumnIndex("UCode_PPB")),
                    No_PPB = cursor.getString(cursor.getColumnIndex("No_PPB")),
                    Ket = cursor.getString(cursor.getColumnIndex("Ket"))
                )
                posts.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return posts
    }


}
