package com.app.meditation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.meditation.data.model.NoteModel
import com.app.meditation.database.dao.NoteDao

@Database(entities = [NoteModel::class], version = 1)
abstract class DatabaseHelper:RoomDatabase() {

    abstract val noteDao : NoteDao
}