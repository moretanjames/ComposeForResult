package yt.devdroid.composeforresult.ui.note

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.collectLatest
import yt.devdroid.composeforresult.ui.getResult
import yt.devdroid.composeforresult.ui.getRouteWithArgs

const val NOTE_ID_ARG = "noteId"
private const val noteRoute: String = "note?$NOTE_ID_ARG={$NOTE_ID_ARG}"
private val navArguments: List<NamedNavArgument> = listOf(
  navArgument(NOTE_ID_ARG) { type = NavType.StringType; nullable = true }
)

private fun getRouteWithNoteId(noteId: Long?) = getRouteWithArgs(noteRoute, mapOf(NOTE_ID_ARG to noteId))

fun NavGraphBuilder.addNoteScreen(
  onBackPress: () -> Unit,
  onClickCopyFromTemplate: () -> Unit
) {
  composable(
    route = noteRoute,
    arguments = navArguments
  ) {

    val viewModel: NoteViewModel = viewModel()

    LaunchedEffect(it.savedStateHandle) {
      it.getResult<Long?>(null).collectLatest { noteId ->
        noteId?.also { id -> viewModel.setNoteTemplate(id) }
      }
    }

    val note by viewModel.note.collectAsState()

    NoteScreen(
      note = note,
      updateNote = viewModel::updateNote,
      onBackPressed = onBackPress,
      onSaveNote = { viewModel.saveNote(); onBackPress() },
      onClickCopyFromTemplate = onClickCopyFromTemplate
    )
  }
}

fun NavController.navigateToNoteScreen(noteId: Long?) {
  navigate(route = getRouteWithNoteId(noteId = noteId))
}