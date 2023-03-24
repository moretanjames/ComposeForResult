package yt.devdroid.composeforresult.ui.result

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class NoteSelectorActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setDecorFitsSystemWindows(false)

    setContent {
      NoteSelectorBottomSheet(
        onResult = { TODO() },
        onDismissRequest = { finish() }
      )
    }
  }
}