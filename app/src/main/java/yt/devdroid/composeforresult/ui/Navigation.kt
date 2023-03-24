package yt.devdroid.composeforresult.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import yt.devdroid.composeforresult.ui.home.addHomeScreen
import yt.devdroid.composeforresult.ui.home.homeRoute
import yt.devdroid.composeforresult.ui.note.addNoteScreen
import yt.devdroid.composeforresult.ui.note.navigateToNoteScreen
import yt.devdroid.composeforresult.ui.result.addNoteSelectorScreen
import yt.devdroid.composeforresult.ui.result.navigateToNoteSelector

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = homeRoute
  ) {
    addHomeScreen(
      onClickNote = { navController.navigateToNoteScreen(it.id) },
      onClickCreateNote = { navController.navigateToNoteScreen(null) }
    )

    addNoteScreen(
      onBackPress = navController::navigateUp,
      onClickCopyFromTemplate = navController::navigateToNoteSelector
    )

    addNoteSelectorScreen(
      onBackPressed = navController::navigateUp,
      onResult = { note ->
        navController.setResult(note.id)
        navController.navigateUp()
      }
    )
  }
}

const val RESULT = "result"

fun <T> NavController.setResult(result: T?) {
  previousBackStackEntry?.savedStateHandle?.set(RESULT, result)
}

fun <T> NavBackStackEntry.getResult(default: T?): Flow<T?> {
  return savedStateHandle.getStateFlow(RESULT, default)
    .filter { savedStateHandle.contains(RESULT) }
    .map { savedStateHandle.remove(RESULT) }
}