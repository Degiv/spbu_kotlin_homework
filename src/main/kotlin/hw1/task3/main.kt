package hw1.task3

fun showInputHint() {
    println("Enter the number of command you want to perform:")
}

fun showCommandsHint(filename: String) {
    println("Commands list:")
    println("1. Print log")
    println("2. Add number to start")
    println("3. Add number to end")
    println("4. Move element")
    println("5. Undo last action")
    println("6. Serialize list of performed actions into file '$filename'")
    println("7. Deserialize list of actions from file '$filename'")
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

fun performCommand(command: Int, storage: CommandStorage, fileName: String) {
    when (command) {
        UserCommand.ADD_TO_START.ordinal -> {
            val newValue = inputNumberPrompt("Enter a number to add to start:")
            InsertForward(newValue).perform(storage)
        }

        UserCommand.ADD_TO_END.ordinal -> {
            val newValue = inputNumberPrompt("Enter a number to add to end:")
            InsertBack(newValue).perform(storage)
        }

        UserCommand.MOVE.ordinal -> {
            val indexFrom = inputIndex("Enter the index move from:", storage)
            val indexTo = inputIndex("Enter the index move to:", storage)

            Move(indexFrom, indexTo).perform(storage)
        }

        UserCommand.UNDO.ordinal -> {
            storage.undoLastAction()
        }

        UserCommand.PRINT.ordinal -> {
            println("List of numbers:")
            storage.data.forEach { print("$it ") }
            println("")

            println("List of actions:")
            storage.commandList.forEach { it.print(storage) }
            println("")
        }

        UserCommand.SERIALIZE.ordinal -> {
            storage.serialize(fileName)
        }

        UserCommand.DESERIALIZE.ordinal -> {
            storage.deserialize(fileName)
        }
    }
}

fun main() {
    val storage = CommandStorage()
    val fileName = "src/main/resources/ActionList.json"
    showCommandsHint(fileName)
    var mustContinue = true
    do {
        showInputHint()
        var command = readLine()?.toIntOrNull()
        while (command == null || command < UserCommand.EXIT.ordinal || command > UserCommand.DESERIALIZE.ordinal) {
            println("Incorrect input. Try again:")
            command = readLine()?.toIntOrNull()
        }

        if (command == UserCommand.EXIT.ordinal) {
            mustContinue = false
        } else {
            performCommand(command, storage, fileName)
        }
    } while (mustContinue)
}
