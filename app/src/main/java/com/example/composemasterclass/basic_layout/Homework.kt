package com.example.composemasterclass.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemasterclass.R
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun Homework(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    date: String
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color(232, 116, 87)),
        horizontalAlignment = Alignment.End,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    description,
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.more_horizontal),
                contentDescription = null,
                tint = Color.White
            )

        }

        Text(
            date,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp),
        )

    }
}


@Preview(showBackground = true)
@Composable
private fun HomeworkPreview() {
    // Preview your homework implementation here
    ComposemasterclassTheme {
        Homework(
            title = "Project X",
            description = """Lorem ipsum dolor sit amet, consectetur adipiscing eli. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                | Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."""".trimMargin(),
            date = "Mar 5, 10:00",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeworkSmallPreview() {
    // Preview your homework implementation here
    ComposemasterclassTheme {
        Homework(
            title = "Project X",
            description = "This is a short description.",
            date = "Mar 5, 10:00",
        )
    }
}