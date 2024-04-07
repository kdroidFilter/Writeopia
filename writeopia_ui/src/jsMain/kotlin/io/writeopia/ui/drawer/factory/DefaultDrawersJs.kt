package io.writeopia.ui.drawer.factory

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import io.writeopia.ui.drawer.StoryStepDrawer
import io.writeopia.ui.edition.TextCommandHandler
import io.writeopia.ui.manager.WriteopiaStateManager
import org.jetbrains.skiko.SkikoKey

private const val LARGE_START_PADDING = 26
private const val MEDIUM_START_PADDING = 12
private const val SMALL_START_PADDING = 4

object DefaultDrawersJs : DrawersFactory {

    @Composable
    override fun create(
        manager: WriteopiaStateManager,
        defaultBorder: Shape,
        editable: Boolean,
        groupsBackgroundColor: Color,
        onHeaderClick: () -> Unit,
        textCommandHandler: TextCommandHandler
    ): Map<Int, StoryStepDrawer> =
        CommonDrawers.create(
            manager,
            30.dp,
            defaultBorder,
            editable,
            groupsBackgroundColor,
            onHeaderClick,
            dragIconWidth = 16.dp,
            eventListener = KeyEventListenerFactory.js(
                manager = manager,
                isEmptyErase = ::emptyErase,
            )
        )

    private fun emptyErase(keyEvent: KeyEvent, input: TextFieldValue): Boolean =
        keyEvent.nativeKeyEvent.key == SkikoKey.KEY_BACKSPACE && input.selection.start == 0
}
