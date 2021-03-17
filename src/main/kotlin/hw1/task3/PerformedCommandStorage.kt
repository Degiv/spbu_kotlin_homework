package hw1.task3

class PerformedCommandStorage {
    var data = mutableListOf<Int>()
    var commandList = mutableListOf<Action>()

    fun addAction(newAction: Action) {
        commandList.add(newAction)
    }

    fun undoLastAction() {
        if (commandList.isEmpty()) {
            println("Nothing to undo")
            return
        }
        var lastIndex: Int = commandList.lastIndex
        commandList[lastIndex].undo()
        commandList.removeLast()
    }
}