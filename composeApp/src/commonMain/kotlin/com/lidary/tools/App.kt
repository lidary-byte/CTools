package com.lidary.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lidary.tools.entity.ApplicationInfoEntity
import com.lidary.tools.utils.getInstallApps
import com.lidary.tools.widget.Search
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var installApps by remember { mutableStateOf(emptyList<ApplicationInfoEntity>()) }
    var searchList by remember { mutableStateOf(installApps) }
    // 用于控制协程的作用域
    val coroutineScope = rememberCoroutineScope()
    // 在首次加载时启动协程获取已安装应用列表
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // 假设 getInstallApps 是一个 suspend 函数，调用它获取数据
            val apps = getInstallApps()
            installApps = apps.appsName.toMutableStateList()
            // 初始时将所有应用赋值给 searchList
            searchList = installApps
        }
    }
    MaterialTheme {
        var searchText by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // 搜索框
            Search(searchText) {
                searchText = it
                searchList = installApps.filter { it.name?.contains(searchText, ignoreCase = true) == true }
            }
            Divider()
            Box() {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(searchList.size) {
                        if (it == 0) {
                            Spacer(modifier = Modifier.height(14.dp))
                        }
                        Text(searchList[it].name ?: "", modifier = Modifier.fillMaxWidth())
                        if (it == searchList.size - 1) {
                            Spacer(modifier = Modifier.height(14.dp))
                        }
                    }
                }
            }
        }
    }
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