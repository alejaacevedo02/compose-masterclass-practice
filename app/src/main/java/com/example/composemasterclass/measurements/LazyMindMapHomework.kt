package com.example.composemasterclass.measurements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMapIndexedNotNull
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme
import kotlin.math.roundToInt


data class MindMapItemNew(
    val content: @Composable () -> Unit,
    val constraints: Constraints,
    val offset: Offset
)

data class ProcessedMindMapItemNew(
    val placeable: Placeable,
    val finalXPosition: Int,
    val finalYPosition: Int
)

@Composable
fun LazyMindMapFlexibleRoot() {
    var offset by remember {
        mutableStateOf(IntOffset.Zero)
    }
    var counter by remember {
        mutableIntStateOf(0)
    }
    val items = listOf(
        MindMapItemNew(
            offset = Offset.Zero,
            constraints = Constraints(
                maxWidth = 2000,
                maxHeight = 1500
            ),
            content = {
                Column(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.Gray
                        )
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = counter.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                    Row {
                        Button(
                            onClick = { counter-- }
                        ) {
                            Text("Dec")
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(
                            onClick = { counter++ }
                        ) {
                            Text("Inc")
                        }
                    }
                }
            }
        ),
        MindMapItemNew(
            offset = Offset(
                x = -700f,
                y = -700f
            ),
            constraints = Constraints(
                maxWidth = 2000,
                maxHeight = 1500
            ),
            content = {
                Text(
                    text = "Hello world!",
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.Gray
                        )
                        .padding(16.dp),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        )
    )

    LazyMindMapNew(
        itemsList = items,
        mindMapOffset = offset,
        onDrag = { delta -> offset += delta },
        modifier = Modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyMindMapNew(
    itemsList: List<MindMapItemNew> = emptyList(),
    onDrag: (delta: IntOffset) -> Unit,
    mindMapOffset: IntOffset = IntOffset.Zero,
    modifier: Modifier = Modifier,
) {

    LazyLayout(
        itemProvider = {
            object : LazyLayoutItemProvider {
                override val itemCount: Int
                    get() = itemsList.size

                @Composable
                override fun Item(index: Int, key: Any) {
                    val item = itemsList[index]
                    Layout(
                        content = item.content,
                        measurePolicy = { measurables, _ ->
                            val placeables = measurables.map {
                                it.measure(item.constraints)
                            }
                            val maxWidth = placeables.maxOfOrNull { it.width } ?: 0
                            val maxHeight = placeables.maxOfOrNull { it.height } ?: 0

                            layout(maxWidth, maxHeight) {
                                placeables.fastForEach {
                                    it.place(0, 0)
                                }
                            }
                        }
                    )
                }

            }
        },
        modifier = modifier.draggable2D(
            state = rememberDraggable2DState { delta -> onDrag(delta.round()) }
        ),
    ) { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight
        val centerX = layoutWidth / 2 + mindMapOffset.x
        val centerY = layoutHeight / 2 + mindMapOffset.y

        val visibleArea = IntRect(
            left = 0,
            top = 0,
            right = layoutWidth,
            bottom = layoutHeight
        )


        val visibleItems = itemsList.fastMapIndexedNotNull { index, item ->
            val finalXPosition = (centerX + item.offset.x).roundToInt()
            val finalYPosition = (centerY + item.offset.y).roundToInt()
            val maxItemWidth = item.constraints.maxWidth
            val maxItemHeight = item.constraints.maxHeight

            val extendedItemBounds = IntRect(
                left = finalXPosition - maxItemWidth / 2,
                top = finalYPosition - maxItemHeight / 2,
                right = finalXPosition + 3 * (maxItemWidth / 2),
                bottom = finalYPosition + 3 * (maxItemHeight / 2),
            )
            if (visibleArea.overlaps(extendedItemBounds)) {
                val placeables = measure(
                    index = index,
                    constraints = item.constraints
                )
                placeables.map {
                    ProcessedMindMapItemNew(
                        placeable = it,
                        finalXPosition = finalXPosition,
                        finalYPosition = finalYPosition
                    )
                }
            } else null
        }

        println("Visible items count: ${visibleItems.size}")

        layout(
            constraints.maxWidth,
            constraints.maxHeight
        ) {
            visibleItems.flatten().fastForEach { item ->
                item.placeable.place(
                    x = item.finalXPosition - item.placeable.width / 2,
                    y = item.finalYPosition - item.placeable.height / 2
                )
            }
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
private fun LazyMindMapFlexiblePreview() {
    ComposemasterclassTheme() {

        LazyMindMapFlexibleRoot()
    }
}
