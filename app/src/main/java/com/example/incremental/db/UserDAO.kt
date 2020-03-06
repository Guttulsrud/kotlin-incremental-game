package com.example.incremental.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.incremental.UserDTO
import com.example.incremental.db.UserTable.COLUMN_CLICK_STRENGTH
import com.example.incremental.db.UserTable.COLUMN_ID
import com.example.incremental.db.UserTable.COLUMN_NAME
import com.example.incremental.db.UserTable.COLUMN_POINTS
import com.example.incremental.db.UserTable.COLUMN_TOTAL_CLICKS
import com.example.incremental.db.UserTable.COLUMN_TOTAL_POINTS
import com.example.incremental.db.UserTable.TABLE_NAME


object UserTable : BaseColumns {
    const val TABLE_NAME = "USER_TABLE"
    const val COLUMN_NAME = "NAME"
    const val COLUMN_ID = "ID"
    const val COLUMN_POINTS = "POINTS"
    const val COLUMN_CLICK_STRENGTH = "CLICK_STRENGTH"
    const val COLUMN_TOTAL_POINTS = "TOTAL_POINTS"
    const val COLUMN_TOTAL_CLICKS = "TOTAL_CLICKS"
}

class UserDAO(context: Context) : BaseDataBase(context) {


    // Function to insert a new user
    fun insert(
        name: String,
        points: Int,
        clickStrength: Int,
        totalPoints: Int,
        totalClicks: Int
    ): Long? {
        val values = ContentValues().apply {
            put(COLUMN_POINTS, points)
            put(COLUMN_CLICK_STRENGTH, clickStrength)
            put(COLUMN_TOTAL_POINTS, totalPoints)
            put(COLUMN_TOTAL_CLICKS, totalClicks)
            put(COLUMN_NAME, name)

        }

        return writableDatabase.use {
            it.insert(TABLE_NAME, null, values)
        }
    }

    // Function to update a existing user
    fun update(
        user: UserDTO,
        points: Int,
        clickStrength: Int,
        totalPoints: Int,
        totalClicks: Int
    ): Int? {
        val values = ContentValues().apply {
            put(COLUMN_POINTS, points)
            put(COLUMN_CLICK_STRENGTH, clickStrength)
            put(COLUMN_TOTAL_POINTS, totalPoints)
            put(COLUMN_TOTAL_CLICKS, totalClicks)
        }

        val selectionArgs = arrayOf(user.id.toString())

        return writableDatabase.use {
            it.update(TABLE_NAME, values, "$COLUMN_ID = ?", selectionArgs)
        }
    }


    //Function to get all users
    fun getAllUsers(): List<UserDTO> {


        val cursor: Cursor = readableDatabase.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val userList = mutableListOf<UserDTO>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val points = getInt(getColumnIndexOrThrow(COLUMN_POINTS))
                val totalPoints = getInt(getColumnIndexOrThrow(COLUMN_TOTAL_POINTS))
                val totalClicks = getInt(getColumnIndexOrThrow(COLUMN_TOTAL_CLICKS))
                val clickStrength = getInt(getColumnIndexOrThrow(COLUMN_CLICK_STRENGTH))
                userList.add(UserDTO(id, points, clickStrength, totalPoints, totalClicks, name))
            }
        }
        return userList
    }

    fun delete(id: Long) {

    }

}