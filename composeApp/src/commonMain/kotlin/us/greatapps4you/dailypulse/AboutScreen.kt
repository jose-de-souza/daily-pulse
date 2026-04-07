package us.greatapps4you.dailypulse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Toolbar()
    ContentView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(title = { Text(text = "About Device") },)
}

@Composable
fun ContentView() {
    val items = makeItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items){ row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }
}

fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", "${platform.density}")
    )
}

@Composable
fun RowView(title: String, subtitle: String) {
    Column(Modifier.padding(8.dp)){
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )

    }
    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
}



