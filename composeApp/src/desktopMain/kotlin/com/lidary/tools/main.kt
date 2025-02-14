package com.lidary.tools

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CTools",
        undecorated = true
    ) {
        App()
    }
}