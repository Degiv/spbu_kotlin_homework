package hw1.task3

/**
 * Describes an action that can be performed on the list of integers.
 */
interface Action {
    fun perform()
    fun undo()
    fun print()
}

/**
 * Class for adding number to start of integer list.
 * @property {Int} [newValue] - the number to add.
 * @property {CommandStorage} [commandStorage] - storage to add.
 */
class InsertForward(private val newValue: Int, private val commandStorage: CommandStorage) : Action {
    /**
     * Performs adding to start.
     */
    override fun perform() {
        commandStorage.data.add(0, newValue)
        commandStorage.addAction(this)
    }

    /**
     * Undoes adding to start in fact removes the first number.
     */
    override fun undo() {
        commandStorage.data.removeFirst()
    }

    /**
     * Prints a description of this action.
     */
    override fun print() {
        println("add ${this.newValue} to start")
    }
}

/**
 * Class for adding number to end of integer list.
 * @property {Int} [newValue] - the number to add.
 * @property {CommandStorage} [commandStorage] - storage to add.
 */
class InsertBack(private val newValue: Int, private val commandStorage: CommandStorage) : Action {
    /**
     * Performs adding to end.
     */
    override fun perform() {
        commandStorage.data.add(newValue)
        commandStorage.addAction(this)
    }

    /**
     * Undoes adding to end in fact removes the last number.
     */
    override fun undo() {
        commandStorage.data.removeLast()
    }

    /**
     * Prints a description of this action.
     */
    override fun print() {
        println("add ${this.newValue} to end")
    }
}

/**
 * Class for moving numbers in integer list.
 * @property {Int} [indexFrom] - index of the number to move.
 * @property {int} [indexTo] - index which number moves to.
 * @property {CommandStorage} [commandStorage] - storage in which number moves.
 */
class Move(private val indexFrom: Int, private val indexTo: Int, private val commandStorage: CommandStorage) : Action {
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
     */
    override fun perform() {
        if (indexFrom !in commandStorage.data.indices || indexTo !in commandStorage.data.indices) {
            error("Invalid index")
        }

        moveElement(indexFrom, indexTo, commandStorage.data)
        commandStorage.addAction(this)
    }

    /**
     * Undoes this action
     */
    override fun undo() {
        moveElement(indexTo, indexFrom, commandStorage.data)
    }

    /**
     * Prints a description of this action.'
     */
    override fun print() {
        println("move element from ${this.indexFrom} to ${this.indexTo}")
    }
}
