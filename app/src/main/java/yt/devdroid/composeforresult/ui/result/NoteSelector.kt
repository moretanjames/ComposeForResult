@file:OptIn(ExperimentalMaterial3Api::class)

package yt.devdroid.composeforresult.ui.result

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import yt.devdroid.composeforresult.data.Note
import yt.devdroid.composeforresult.ui.home.NoteListContent
import yt.devdroid.composeforresult.ui.home.NoteListViewModel

@Composable
fun NoteSelectorBottomSheet(
  onResult: (Note) -> Unit,
  onDismissRequest: () -> Unit
) {
  val viewModel: NoteListViewModel = viewModel()

  val notes by viewModel.notes.collectAsState()

  ModalBottomSheet(onDismissRequest = onDismissRequest) {
    Surface {
      NoteListContent(notes = notes, onClickNote = onResult, modifier = Modifier.fillMaxWidth())
    }
  }
}

@Composable
fun NoteSelector(
  onResult: (Note) -> Unit,
  onBackPress: () -> Unit
) {
  val viewModel: NoteListViewModel = viewModel()

  val notes by viewModel.notes.collectAsState()

  Scaffold(
    topBar = {
      Row(modifier = Modifier.fillMaxWidth().statusBarsPadding()) {
        IconButton(onClick = onBackPress) {
          Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
      }
    }
  ) {
    NoteListContent(notes = notes, onClickNote = onResult, modifier = Modifier.padding(it))
  }
}