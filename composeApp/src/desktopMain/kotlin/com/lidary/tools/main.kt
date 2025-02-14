package com.lidary.tools

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CTools",
        undecorated = true,
        // macOS上运行报错 windows，Linux未测试
//        transparent = true,
        resizable = false,
        state = rememberWindowState(
            position = WindowPosition.Aligned(alignment = Alignment.Center)
        )
    ) {
//        Surface(shape = RoundedCornerShape(20.dp)) {
//            Column {
//                AppWindowTitleBar()
//                App()
//            }
//        }
        App()
    }
}

/**
 * 可拖拽区域
 */
//@Composable
//fun WindowScope.AppWindowTitleBar() = WindowDraggableArea(Modifier.height(60.dp).fillMaxWidth()) {
//    Search()
//}