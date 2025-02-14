package com.lidary.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.lidary.tools.utils.getInstallApps
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val installApps = getInstallApps()

    MaterialTheme {
        var searchText by remember { mutableStateOf(TextFieldValue("")) }
        val commands = listOf(
            "Open App",
            "Search Files",
            "View System Status",
            "Run Command",
            "Settings"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // 设置深色背景
        ) {
            // 搜索框
            SearchBox(searchText) { searchText = it }

//            Spacer(modifier = Modifier.height(20.dp))
//
//            // 命令列表
//            CommandList(commands.filter { it.contains(searchText.text, ignoreCase = true) })
            List(installApps.appsName.size) {
                Text(installApps.appsName[it])
            }

        }
    }
}


@Composable
fun SearchBox(searchText: TextFieldValue, onSearchTextChanged: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2C))
            .padding(16.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.h6.copy(color = Color.White),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFF2C2C2C),
            focusedBorderColor = Color(0xFF4CAF50),
            unfocusedBorderColor = Color.Gray
        )
    )
}

@Composable
fun CommandList(commands: List<String>) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        commands.forEach { command ->
            CommandItem(command)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun CommandItem(command: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFF333333)),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(command, style = MaterialTheme.typography.body1, color = Color.White)
        }
    }
}