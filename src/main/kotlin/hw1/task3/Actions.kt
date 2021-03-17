package hw1.task3

interface Action {
    fun perform()
    fun undo()
}

class InsertForward(private val newValue: Int, private val commandStorage: PerformedCommandStorage) : Action {
    override fun perform() {
        commandStorage.data.add(0, newValue)
        commandStorage.addAction(this)
    }

    override fun undo() {
        commandStorage.data.removeFirst()
    }
}

class InsertBack(private val newValue: Int, private val commandStorage: PerformedCommandStorage) : Action {
    override fun perform() {
        commandStorage.data.add(newValue)
        commandStorage.addAction(this)
    }

    override fun undo() {
        commandStorage.data.removeLast()
    }
}

class Move(
    private val indexFrom: Int,
    private val indexTo: Int,
    private val commandStorage: PerformedCommandStorage) : Action {
    private fun moveElement(indexFrom: Int, indexTo: Int, data: MutableList<Int>) {
        val elementToMove: Int = data[indexFrom]
        data.removeAt(indexFrom)
        data.add(indexTo, elementToMove)
    }

    override fun perform() {
        if (indexFrom !in commandStorage.data.indices || indexTo !in commandStorage.data.indices) {
            return
        }

        moveElement(indexFrom, indexTo, commandStorage.data)
        commandStorage.addAction(this)
    }

    override fun undo() {
        moveElement(indexTo, indexFrom, commandStorage.data)
    }
}
