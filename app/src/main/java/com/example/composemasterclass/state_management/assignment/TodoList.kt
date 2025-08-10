package com.example.composemasterclass.state_management.assignment

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun TodoItemHoistingDemo() {
    val todo = remember {
        mutableStateOf(
            Todo(
                title = "Bring out the trash",
                description = "Better do this before wife comes home",
                isChecked = false
            )
        )
    }

    TodoItem(
        todo = todo.value,
        onCheckedChange = { newChange ->
            todo.value = todo.value.copy(
                isChecked = newChange
            )
        })

}

@Composable
fun TodoItem(
    todo: Todo,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                todo.title,
                fontWeight = FontWeight.Bold,
                textDecoration = if (todo.isChecked) TextDecoration.LineThrough
                else TextDecoration.None
            )
            Text(
                todo.description,
                textDecoration = if (todo.isChecked) TextDecoration.LineThrough
                else TextDecoration.None
            )
        }
        Checkbox(
            checked = todo.isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}


@Preview(showBackground = true)
@Composable
internal fun TodoItemPreview() {

    ComposemasterclassTheme {
        TodoItemHoistingDemo(
        )
    }
}