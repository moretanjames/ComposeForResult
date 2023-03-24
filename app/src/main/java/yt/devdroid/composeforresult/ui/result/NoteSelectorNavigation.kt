package yt.devdroid.composeforresult.ui.result

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import yt.devdroid.composeforresult.data.Note

fun NavGraphBuilder.addNoteSelectorScreen(
  onBackPressed: () -> Unit,
  onResult: (Note) -> Unit
) {
  composable(
    route = "note/selector"
  ) {
    NoteSelector(onResult = onResult, onBackPressed)
  }
}

fun NavController.navigateToNoteSelector() {
  navigate("note/selector")
}