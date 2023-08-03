package com.github.leandroborgesferreira.storyteller.utils.extensions

import com.github.leandroborgesferreira.storyteller.model.action.Action
import com.github.leandroborgesferreira.storyteller.model.action.BackstackAction

//Todo: Delete this, some actions don't have a direct transform
internal fun Action.toBackStack(): BackstackAction =
    when (this) {
        is Action.AddStory -> BackstackAction.Add(position = position, storyStep = storyStep)
        is Action.BulkDelete -> BackstackAction.BulkDelete(deletedUnits)
        is Action.DeleteStory -> BackstackAction.Delete(storyStep, position)
        is Action.Merge -> BackstackAction.Merge(receiver, sender, positionFrom, positionTo)
        is Action.Move -> BackstackAction.Move(storyStep, positionFrom, positionTo)
        is Action.StoryStateChange -> BackstackAction.StoryStateChange(storyStep, position)
        is Action.TextEdit -> throw IllegalArgumentException(
            "This action can't be converted to BackstackAction"
        )
        is Action.LineBreak -> throw IllegalArgumentException(
            "This action can't be converted to BackstackAction"
        )

    }