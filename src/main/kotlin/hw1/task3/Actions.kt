package hw1.task3

interface Action {
    fun perform()
    fun undo()
    fun print()
}

class InsertForward(private val newValue: Int, private val commandStorage: CommandStorage) : Action {
    override fun perform() {
        commandStorage.data.add(0, newValue)
        commandStorage.addAction(this)
    }

    override fun undo() {
        commandStorage.data.removeFirst()
    }

    override fun print() {
        println("add ${this.newValue} to start")
    }
}

class InsertBack(private val newValue: Int, private val commandStorage: CommandStorage) : Action {
    override fun perform() {
        commandStorage.data.add(newValue)
        commandStorage.addAction(this)
    }

    override fun undo() {
        commandStorage.data.removeLast()
    }

    override fun print() {
        println("add ${this.newValue} to end")
    }
}

class Move(private val indexFrom: Int, private val indexTo: Int, private val commandStorage: CommandStorage) : Action {
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

    override fun print() {
        println("move element from ${this.indexFrom} to ${this.indexTo}")
    }
}
