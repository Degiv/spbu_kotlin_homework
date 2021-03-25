package hw1.task3

/**
 * Class for storing a list of numbers and performed actions on it
 */
class CommandStorage {
    val data = mutableListOf<Int>()
    val commandList = mutableListOf<Action>()

    /**
     * Adds the action to list of performed actions
     */
    fun addAction(newAction: Action) {
        commandList.add(newAction)
    }

    /**
     * Undoes last performed action
     */
    fun undoLastAction() {
        if (commandList.isEmpty()) {
            println("Nothing to undo")
            return
        }
        commandList.last().undo()
        commandList.removeLast()
    }
}
