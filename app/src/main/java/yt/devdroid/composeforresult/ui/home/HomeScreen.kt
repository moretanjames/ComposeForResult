@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package yt.devdroid.composeforresult.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*
import yt.devdroid.composeforresult.data.Note

@Composable
fun HomeScreen(
  onClickNote: (Note) -> Unit,
  onClickCreateNote: () -> Unit,
  modifier: Modifier = Modifier
) {

  val viewModel: NoteListViewModel = viewModel()

  val notes by viewModel.notes.collectAsState()

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(onClick = onClickCreateNote) {
        Icon(imageVector = Icons.Default.Create, contentDescription = null)
      }
    },
    modifier = modifier
  ) {
    NoteListContent(
      notes = notes,
      onClickNote = onClickNote,
      modifier = Modifier.padding(it)
    )
  }
}

@Composable
fun NoteListContent(
  notes: List<Note>,
  onClickNote: (Note) -> Unit,
  modifier: Modifier = Modifier
) {
  if (notes.isNotEmpty()) {
    LazyColumn(
      modifier = modifier,
      contentPadding = PaddingValues(
        top = 16.dp,
        bottom = 80.dp,
        start = 16.dp,
        end = 16.dp
      ),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(notes) { note ->
        NoteItem(
          note = note,
          onClickNote = onClickNote,
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  } else {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      Text(text = "No Notes Yet")
    }
  }
}

@Composable
fun NoteItem(
  note: Note,
  onClickNote: (Note) -> Unit,
  modifier: Modifier = Modifier
) {

  val formatter = remember { SimpleDateFormat("MM/dd/yy", Locale.getDefault()) }

  Card(
    onClick = { onClickNote(note) },
    modifier = modifier,
    shape = MaterialTheme.shapes.medium,
    elevation = CardDefaults.elevatedCardElevation()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(imageVector = Icons.Outlined.StickyNote2, contentDescription = null, modifier = Modifier.size(36.dp))
      Spacer(modifier = Modifier.width(8.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(text = note.title, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.headlineSmall)
        Text(text = note.body, maxLines = 2, overflow = TextOverflow.Ellipsis)
      }
      Spacer(modifier = Modifier.width(8.dp))
      Text(text = formatter.format(note.date))
    }
  }
}