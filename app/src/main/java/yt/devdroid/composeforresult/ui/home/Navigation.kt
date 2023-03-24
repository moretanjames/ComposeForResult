package yt.devdroid.composeforresult.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import yt.devdroid.composeforresult.data.Note

const val homeRoute = "home"

fun NavGraphBuilder.addHomeScreen(
    onClickNote: (Note) -> Unit,
    onClickCreateNote: () -> Unit
) {
    composable(route = homeRoute) {
        HomeScreen(
            onClickNote = onClickNote,
            onClickCreateNote = onClickCreateNote
        )
    }
}