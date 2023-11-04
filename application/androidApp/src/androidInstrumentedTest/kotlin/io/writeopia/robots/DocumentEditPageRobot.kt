package io.writeopia.robots

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import io.writeopia.editor.ui.NAVIGATE_BACK_TEST_TAG
import io.writeopia.editor.ui.NOTE_EDITION_SCREEN_TITLE_TEST_TAG

class DocumentEditPageRobot(private val composeTestRule: ComposeTestRule) {

    fun verifyItIsInEdition() {
        composeTestRule.onNodeWithTag(NOTE_EDITION_SCREEN_TITLE_TEST_TAG).assertIsDisplayed()
    }

    fun verifyToolbarTitle(title: String) {
        composeTestRule.onNodeWithTag(NOTE_EDITION_SCREEN_TITLE_TEST_TAG).assertTextContains(title)
    }

    fun goBack() {
        composeTestRule.onNodeWithTag(NAVIGATE_BACK_TEST_TAG).performClick()
    }
}