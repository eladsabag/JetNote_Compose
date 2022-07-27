package com.example.jetnote_compose.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote_compose.data.NotesDataSource
import com.example.jetnote_compose.model.Note
import com.example.jetnote_compose.repository.NoteRepiository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepiository): ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes ->
                if(listOfNotes.isEmpty()) {
                    Log.d("Empty",": Empty list")
                } else {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

}