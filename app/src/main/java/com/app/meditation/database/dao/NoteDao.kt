package com.app.meditation.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.meditation.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    fun insert(noteModel: NoteModel)

    @Delete
    fun delete(noteModel: NoteModel)

    @Update
    fun update(noteModel: NoteModel)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteModel>>
}