package io.writeopia.ui.drawer.content

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.writeopia.sdk.model.action.Action
import io.writeopia.sdk.models.story.StoryStep
import io.writeopia.sdk.models.story.StoryTypes
import io.writeopia.ui.drawer.SimpleTextDrawer
import io.writeopia.ui.drawer.StoryStepDrawer
import io.writeopia.ui.manager.WriteopiaStateManager
import io.writeopia.ui.model.DrawConfig
import io.writeopia.ui.model.DrawInfo
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Draw a text that can be edited with a swipe effect to trigger edition.
 */
fun swipeTextDrawer(
    modifier: Modifier = Modifier,
    customBackgroundColor: Color = Color.Transparent,
    clickable: Boolean = true,
    dragIconWidth: Dp = 16.dp,
    config: DrawConfig,
    onSelected: (Boolean, Int) -> Unit = { _, _ -> },
    onDragHover: (Int) -> Unit,
    onDragStart: () -> Unit = {},
    onDragStop: () -> Unit = {},
    moveRequest: (Action.Move) -> Unit = {},
    messageDrawer: @Composable RowScope.() -> SimpleTextDrawer,
    isDesktop: Boolean,
    enabled: Boolean,
    endContent: @Composable ((StoryStep, DrawInfo, Boolean) -> Unit)? = null,
): StoryStepDrawer =
    DesktopTextItemDrawer(
        modifier,
        customBackgroundColor,
        clickable,
        onSelected,
        dragIconWidth,
        config,
        onDragHover,
        onDragStart,
        onDragStop,
        moveRequest,
        null,
        endContent,
        isDesktop,
        enabled,
        messageDrawer
    )

fun swipeTextDrawer(
    manager: WriteopiaStateManager,
    modifier: Modifier = Modifier,
    dragIconWidth: Dp = 16.dp,
    config: DrawConfig,
    isDesktop: Boolean,
    enabled: Boolean,
    endContent: @Composable ((StoryStep, DrawInfo, Boolean) -> Unit)? = null,
    messageDrawer: @Composable RowScope.() -> SimpleTextDrawer,
): StoryStepDrawer {
    return swipeTextDrawer(
        modifier = modifier,
        onSelected = manager::onSelected,
        dragIconWidth = dragIconWidth,
        config = config,
        onDragHover = manager::onDragHover,
        onDragStart = manager::onDragStart,
        onDragStop = manager::onDragStop,
        moveRequest = manager::moveRequest,
        customBackgroundColor = Color.Transparent,
        endContent = endContent,
        isDesktop = isDesktop,
        enabled = enabled,
        messageDrawer = {
            messageDrawer()
        }
    )
}

@Preview
@Composable
private fun SwipeMessageDrawerPreview() {
    swipeTextDrawer(
        onDragHover = {},
        isDesktop = true,
        config = DrawConfig(),
        enabled = true,
        messageDrawer = {
            TextDrawer(selectionState = MutableStateFlow(false), onSelectionLister = {})
        },
    ).Step(
        step = StoryStep(text = "Some text", type = StoryTypes.TEXT.type),
        drawInfo = DrawInfo(selectMode = true),
    )
}
