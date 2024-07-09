package io.writeopia.note_menu.data.repository

import io.writeopia.note_menu.data.model.Folder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class InMemoryFolderRepository : FolderRepository {

    private val mutableMap = mutableMapOf<String, Folder>()
    private val _foldersStateFlow = MutableStateFlow<Map<String, Folder>>(mutableMap)

    override suspend fun createFolder(folder: Folder) {
        mutableMap[folder.id] = folder
        refreshState()
    }

    override suspend fun updateFolder(folder: Folder) {
        mutableMap[folder.id] = folder
        refreshState()
    }

    override fun listenForAllFoldersByParentId(
        parentId: String,
        coroutineScope: CoroutineScope
    ): Flow<Map<String, List<Folder>>> {
        return MutableStateFlow(emptyMap())
    }

    override suspend fun getChildrenFolders(userId: String, parentId: String): List<Folder> =
        emptyList()

    override suspend fun deleteFolderById(folderId: String) {
        mutableMap.remove(folderId)
        refreshState()
    }

    private fun refreshState() {
        _foldersStateFlow.value = mutableMap
    }

    companion object {
        private var instance: InMemoryFolderRepository? = null

        fun singleton(): InMemoryFolderRepository {
            return instance ?: run {
                instance = InMemoryFolderRepository()
                instance!!
            }
        }
    }

}
