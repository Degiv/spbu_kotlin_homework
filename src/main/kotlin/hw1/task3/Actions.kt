package hw1.task3

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes an action that can be performed on the list of integers.
 */
@Serializable
sealed class Action {
    abstract fun perform(commandStorage: CommandStorage)
    abstract fun undo(commandStorage: CommandStorage)
    abstract fun print(commandStorage: CommandStorage)
}

/**
 * Class for adding number to start of integer list.
 * @property {Int} [newValue] - the number to add.
 */
@Serializable
@SerialName("InsertForward")
class InsertForward(private val newValue: Int) : Action() {
    /**
     * Performs adding to start.
     * @param {CommandStorage} [commandStorage] - storage to add.
     */
    override fun perform(commandStorage: CommandStorage) {
        commandStorage.data.add(0, newValue)
        commandStorage.addAction(this)
    }

    /**
     * Undoes adding to start in fact removes the first number.
     * @param {CommandStorage} [commandStorage] - storage to undoes.
     */
    override fun undo(commandStorage: CommandStorage) {
        commandStorage.data.removeFirst()
    }

    /**
     * Prints a description of this action.
     * @param {CommandStorage} [commandStorage] - storage to print.
     */
    override fun print(commandStorage: CommandStorage) {
        println("add ${this.newValue} to start")
    }
}

/**
 * Class for adding number to end of integer list.
 * @property {Int} [newValue] - the number to add.
 */
@Serializable
@SerialName("InsertBack")
class InsertBack(private val newValue: Int) : Action() {
    /**
     * Performs adding to end.
     * @param {CommandStorage} [commandStorage] - storage to add.
     */
    override fun perform(commandStorage: CommandStorage) {
        commandStorage.data.add(newValue)
        commandStorage.addAction(this)
    }

    /**
     * Undoes adding to end in fact removes the last number.
     * @param {CommandStorage} [commandStorage] - storage to add.
     */
    override fun undo(commandStorage: CommandStorage) {
        commandStorage.data.removeLast()
    }

    /**
     * Prints a description of this action.
     * @param {CommandStorage} [commandStorage] - storage print.
     */
    override fun print(commandStorage: CommandStorage) {
        println("add ${this.newValue} to end")
    }
}

/**
 * Class for moving numbers in integer list.
 * @property {Int} [indexFrom] - index of the number to move.
 * @property {int} [indexTo] - index which number moves to.
 */
@Serializable
@SerialName("Move")
class Move(private val indexFrom: Int, private val indexTo: Int) : Action() {
    /**
     * Moves number in integer list.
     * @param {Int} [indexFrom] - index of the number to move.
     * @param {int} [indexTo] - index which number moves to.
     * @param {MutableList<Int>} [commandStorage] - list in which number moves.
     */
    private fun moveElement(indexFrom: Int, indexTo: Int, data: MutableList<Int>) {
        val elementToMove: Int = data[indexFrom]
        data.removeAt(indexFrom)
        data.add(indexTo, elementToMove)
    }

    /**
     * Performs this action.
     * @param {CommandStorage} [commandStorage] - storage in which number moves.
     */
    override fun perform(commandStorage: CommandStorage) {
        if (indexFrom !in commandStorage.data.indices || indexTo !in commandStorage.data.indices) {
            error("Invalid index")
        }

        moveElement(indexFrom, indexTo, commandStorage.data)
        commandStorage.addAction(this)
    }

    /**
     * Undoes this action
     * @param {CommandStorage} [commandStorage] - storage in which number moves.
     */
    override fun undo(commandStorage: CommandStorage) {
        moveElement(indexTo, indexFrom, commandStorage.data)
    }

    /**
     * Prints a description of this action.
     * @param {CommandStorage} [commandStorage] - storage to print.
     */
    override fun print(commandStorage: CommandStorage) {
        println("move element from ${this.indexFrom} to ${this.indexTo}")
    }
}
