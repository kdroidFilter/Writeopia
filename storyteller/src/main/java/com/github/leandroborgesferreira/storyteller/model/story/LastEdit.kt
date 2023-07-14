package com.github.leandroborgesferreira.storyteller.model.story

import com.github.leandroborgesferreira.storyteller.model.document.Document

/**
 * Last edition. This signs how the last edition in the [Document] was made and it allows the SDK
 * to react properly, for example saving just one line of change instead saving the whole document.
 */
sealed class LastEdit {
    /**
     * No edition was make
     */
    object Nothing

    /**
     * A whole edition was made like a line break (adding a new line) , a deletion
     * o a image upload between content.
     * In this case the whole document should be saved.
     * It is important to notice that when new content is added between content this will change all
     * the values of the positions, so it is necessary to save the whole document again.
     */
    object WholeEdition

    /**
     * A edition in the line was made, but the positions were not affected. In this case it is
     * possible to update just one line. 
     */
    data class LineEdition(val position: Int, val text: String)
}