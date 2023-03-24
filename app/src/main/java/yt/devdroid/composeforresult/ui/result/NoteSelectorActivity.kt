package yt.devdroid.composeforresult.ui.result

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract

class NoteSelectorActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setDecorFitsSystemWindows(false)

    setContent {
      NoteSelectorBottomSheet(
        onResult = {
          setResult(RESULT_OK, Intent().putExtra(NOTE_ID, it.id))
          finish()
        },
        onDismissRequest = { finish() }
      )
    }
  }
}

private const val NOTE_ID = "noteId"

class NoteSelectorContract : ActivityResultContract<Unit?, Long?>() {
  override fun createIntent(context: Context, input: Unit?): Intent {
    return Intent(context, NoteSelectorActivity::class.java)
  }

  override fun parseResult(resultCode: Int, intent: Intent?): Long? {
    if (resultCode != RESULT_OK || intent == null || intent.extras?.containsKey(NOTE_ID) == false) return null

    return intent.getLongExtra(NOTE_ID, -1L).takeIf { it != -1L }
  }

}