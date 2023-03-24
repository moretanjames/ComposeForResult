package yt.devdroid.composeforresult.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import yt.devdroid.composeforresult.ui.home.addHomeScreen
import yt.devdroid.composeforresult.ui.home.homeRoute
import yt.devdroid.composeforresult.ui.note.addNoteScreen
import yt.devdroid.composeforresult.ui.note.navigateToNoteScreen

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
      onBackPress = navController::navigateUp
    )
  }
}