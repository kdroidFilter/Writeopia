package br.com.leandroferreira.storyteller.normalization.addinbetween

import br.com.leandroferreira.storyteller.model.story.StoryStep
import br.com.leandroferreira.storyteller.model.story.StoryUnit
import br.com.leandroferreira.storyteller.utils.extensions.associateWithPosition
import java.util.Stack
import java.util.UUID

/**
 * This normalizer will guarantee that a new StoryUnit is added between all items of
 * a List<StoryUnit> this normalizer can be very useful to insert spaces between a list
 * of StoryUnits.
 */
class AddInBetween(private val unitToAdd: StoryUnit) {

    fun insert(unit: Map<Int, StoryUnit>): Map<Int, StoryUnit> =
        insert(unit.values).associateWithPosition()

    fun insert(units: Iterable<StoryUnit>): List<StoryUnit> {
        val stack: Stack<StoryUnit> = Stack()
        val typeToAdd = unitToAdd.type

        units.forEach { storyUnit ->
            when {
                stack.isEmpty() && storyUnit.type != typeToAdd -> {
                    stack.add(unitToAdd)
                    stack.add(storyUnit)
                }

                stack.isEmpty() && storyUnit.type == typeToAdd -> {
                    stack.add(storyUnit)
                }

                storyUnit.type != typeToAdd && stack.peek().type == typeToAdd -> {
                    stack.add(storyUnit)
                }

                storyUnit.type == typeToAdd && stack.peek()?.type != typeToAdd -> {
                    stack.add(storyUnit)
                }

                storyUnit.type != typeToAdd && stack.peek()?.type != typeToAdd -> {
                    stack.add(unitToAdd)
                    stack.add(storyUnit)
                }

                storyUnit.type == typeToAdd && stack.peek()?.type == typeToAdd -> {}
            }
        }

        if (stack.peek().type != typeToAdd) {
            stack.add(unitToAdd)
        }

        return stack.toList()
    }


    companion object {
        fun spaces(): AddInBetween =
            AddInBetween(
                unitToAdd = StoryStep(
                    id = UUID.randomUUID().toString(),
                    type = "space",
                )
            )
    }
}
