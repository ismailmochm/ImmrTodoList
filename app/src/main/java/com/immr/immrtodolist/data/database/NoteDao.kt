package com.immr.immrtodolist.data.database

import androidx.room.*
import com.immr.immrtodolist.ui.home.model.Note

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    fun getNote(): List<Note>

}