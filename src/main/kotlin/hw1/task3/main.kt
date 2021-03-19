package hw1.task3

fun showInputHint() {
    println("Enter the number of command you want to perform:")
}

fun showCommandsHint() {
    println("Commands list:")
    println("1. Print log")
    println("2. Add number to start")
    println("3. Add number to end")
    println("4. Move element")
    println("5. Undo last action")
    println("0. Exit")
}

fun inputNumberPrompt(prompt: String = ""): Int {
    println(prompt)
    var number = readLine()?.toIntOrNull()
    while (number == null) {
        println("Wrong input. Try again:")
        number = readLine()?.toIntOrNull()
    }
    return number
}

fun inputIndex(prompt: String = "", storage: CommandStorage): Int {
    var index = inputNumberPrompt(prompt)
    while (index !in storage.data.indices) {
        println("Index is out of range. Try again:")
        index = inputNumberPrompt(prompt)
    }
    return index
}

fun performCommand(command: Int, storage: CommandStorage) {
    when (command) {
        UserCommand.ADD_TO_START.ordinal -> {
            val newValue = inputNumberPrompt("Enter a number to add to start:")
            InsertForward(newValue, storage).perform()
        }

        UserCommand.ADD_TO_END.ordinal -> {
            val newValue = inputNumberPrompt("Enter a number to add to end:")
            InsertBack(newValue, storage).perform()
        }

        UserCommand.MOVE.ordinal -> {
            val indexFrom = inputIndex("Enter the index move from:", storage)
            val indexTo = inputIndex("Enter the index move to:", storage)

            Move(indexFrom, indexTo, storage).perform()
        }

        UserCommand.UNDO.ordinal -> {
            storage.undoLastAction()
        }

        UserCommand.PRINT.ordinal -> {
            println("List of numbers:")
            storage.data.forEach { print("$it ") }
            println("")

            println("List of actions:")
            storage.commandList.forEach { it.print() }
            println("")
        }
    }
}

fun main() {
    val storage = CommandStorage()
    showCommandsHint()
    var mustContinue = true
    do {
        showInputHint()
        var command = readLine()?.toIntOrNull()
        while (command == null || command < UserCommand.EXIT.ordinal || command > UserCommand.UNDO.ordinal) {
            println("Incorrect input. Try again:")
            command = readLine()?.toIntOrNull()
        }

        if (command == UserCommand.EXIT.ordinal) {
            mustContinue = false
        } else {
            performCommand(command, storage)
        }
    } while (mustContinue)
}
