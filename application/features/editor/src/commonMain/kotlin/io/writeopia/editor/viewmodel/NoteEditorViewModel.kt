package io.writeopia.editor.viewmodel

import io.writeopia.editor.model.EditState
import io.writeopia.ui.backstack.BackstackHandler
import io.writeopia.ui.backstack.BackstackInform
import io.writeopia.ui.manager.WriteopiaStateManager
import io.writeopia.sdk.model.story.DrawState
import kotlinx.coroutines.flow.StateFlow

interface NoteEditorViewModel: BackstackInform, BackstackHandler {

    val writeopiaManager: WriteopiaStateManager

    val isEditable: StateFlow<Boolean>

    val showGlobalMenu: StateFlow<Boolean>

    val editHeader: StateFlow<Boolean>

    val currentTitle: StateFlow<String>

    val shouldGoToNextScreen: StateFlow<Boolean>

    val isEditState: StateFlow<EditState>

    val scrollToPosition: StateFlow<Int?>

    val toDraw: StateFlow<DrawState>

    val documentToShareInfo: StateFlow<ShareDocument?>

    val showTextOptionsMenu: StateFlow<Boolean>

    fun deleteSelection()

    fun handleBackAction(navigateBack: () -> Unit)

    fun onHeaderClick()

    fun createNewDocument(documentId: String, title: String)

    fun loadDocument(documentId: String)

    fun onHeaderColorSelection(color: Int?)

    fun onHeaderEditionCancel()

    fun onMoreOptionsClick()

    fun shareDocumentInJson()

    fun shareDocumentInMarkdown()

    fun onViewModelCleared()

    fun toggleOptionsMenu()

    fun hideOptionsMenu()

    fun onAddCheckListClick()

    fun onAddListItemClick()

    fun onAddCodeBlockClick()
}

data class ShareDocument(val content: String, val title: String, val type: String)
