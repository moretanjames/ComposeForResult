package yt.devdroid.composeforresult.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import yt.devdroid.composeforresult.data.NoteRepo

class NoteListViewModel(application: Application): AndroidViewModel(application) {

    private val repo = NoteRepo(application)

    val notes = repo.getNotes().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}