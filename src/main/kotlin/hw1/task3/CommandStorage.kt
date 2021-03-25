package hw1.task3

class CommandStorage {
    val data = mutableListOf<Int>()
    val commandList = mutableListOf<Action>()

    fun addAction(newAction: Action) {
        commandList.add(newAction)
    }

    fun undoLastAction() {
        if (commandList.isEmpty()) {
            println("Nothing to undo")
            return
        }
        commandList.last().undo()
        commandList.removeLast()
    }
}
