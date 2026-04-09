package us.greatapps4you.dailypulse

import androidx.compose.ui.window.ComposeUIViewController
import us.greatapps4you.dailypulse.di.initKoin // Import the bootstrapper

fun MainViewController() = ComposeUIViewController(
    configure = {
        // Start Koin before the Compose UI renders
        initKoin()
    }
) {
    App()
}