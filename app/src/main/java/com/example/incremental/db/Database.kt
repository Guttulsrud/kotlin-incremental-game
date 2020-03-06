package com.example.incremental.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.incremental.db.UserTable.COLUMN_ID
import com.example.incremental.db.UserTable.COLUMN_NAME
import com.example.incremental.db.UserTable.COLUMN_TOTAL_POINTS
import com.example.incremental.db.UserTable.COLUMN_CLICK_STRENGTH
import com.example.incremental.db.UserTable.COLUMN_POINTS
import com.example.incremental.db.UserTable.COLUMN_TOTAL_CLICKS

const val DATABASE_VERSION: Int = 1
const val DATABASE_NAME = "user_db"

open class BaseDataBase(val context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ) {


    override fun onCreate(db: SQLiteDatabase?) {
        val queryCreateUserTable = "CREATE TABLE ${UserTable.TABLE_NAME} " +
                "($COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_POINTS INTEGER DEFAULT 0, " +
                "$COLUMN_CLICK_STRENGTH INTEGER DEFAULT 1, " +
                "$COLUMN_TOTAL_POINTS INTEGER DEFAULT 0, " +
                "$COLUMN_TOTAL_CLICKS INTEGER DEFAULT 0, " +
                "$COLUMN_NAME TEXT)"


        db?.execSQL(queryCreateUserTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}