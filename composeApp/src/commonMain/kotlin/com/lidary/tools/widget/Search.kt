package com.lidary.tools.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 主页搜索框
 */
@Composable
fun Search(searchText: String, onSearchTextChange: (String) -> Unit) {
//    var searchText by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        value = searchText,
        onValueChange = onSearchTextChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 18.sp),
        singleLine = true,
        placeholder = {
            Text(
                text = "Search App...",
            )
        }
    )
}