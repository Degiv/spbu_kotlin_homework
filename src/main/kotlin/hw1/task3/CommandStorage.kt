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
        val lastIndex: Int = commandList.lastIndex
        commandList[lastIndex].undo()
        commandList.removeLast()
    }
}
