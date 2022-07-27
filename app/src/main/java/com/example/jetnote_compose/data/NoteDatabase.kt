package com.example.jetnote_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetnote_compose.model.Note
import com.example.jetnote_compose.util.DateConverter
import com.example.jetnote_compose.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}