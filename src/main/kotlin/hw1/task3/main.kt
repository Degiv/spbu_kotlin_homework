package hw1.task3

enum class UserCommand {
    EXIT_COMMAND,
    PRINT_COMMAND,
    ADD_TO_START_COMMAND,
    ADD_TO_END_COMMAND,
    MOVE_COMMAND,
    UNDO_COMMAND
}

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

fun inputNumberToAdd(): Int {
    println("Enter a number to add")
    var newValue = readLine()?.toIntOrNull()
    while (newValue == null) {
        println("Cant be parsed as number. Try again:")
        newValue = readLine()?.toIntOrNull()
    }
    return newValue
}

fun inputIndex(storage: CommandStorage): Int {
    var index = readLine()?.toIntOrNull()
    while (index == null || index !in storage.data.indices) {
        println("Wrong input. Try again:")
        index = readLine()?.toIntOrNull()
    }
    return index
}

fun main() {
    val storage = CommandStorage()
    showCommandsHint()
    var mustContinue: Boolean = true
    do {
        showInputHint()
        var command = readLine()?.toIntOrNull()
        while (command == null || command < UserCommand.EXIT_COMMAND.ordinal || command > UserCommand.UNDO_COMMAND.ordinal) {
            println("Incorrect input. Try again:")
            command = readLine()?.toIntOrNull()
        }

        when (command) {
            UserCommand.EXIT_COMMAND.ordinal -> mustContinue = false

            UserCommand.ADD_TO_START_COMMAND.ordinal -> {
                var newValue = inputNumberToAdd()
                InsertForward(newValue, storage).perform()
            }

            UserCommand.ADD_TO_END_COMMAND.ordinal -> {
                println("Enter a number to add to end:")
                var newValue = inputNumberToAdd()
                InsertBack(newValue, storage).perform()
            }

            UserCommand.MOVE_COMMAND.ordinal -> {
                println("Enter the index move from:")
                var indexFrom = inputIndex(storage)

                println("Enter the index move to")
                var indexTo = inputIndex(storage)

                Move(indexFrom, indexTo, storage).perform()
            }

            UserCommand.UNDO_COMMAND.ordinal -> {
                storage.undoLastAction()
            }

            UserCommand.PRINT_COMMAND.ordinal -> {
                println("List of numbers:")
                storage.data.forEach { print("$it ") }
                println("")

                println("List of actions:")
                storage.commandList.forEach { print("$it ") }
                println("")
            }
        }
    } while (mustContinue)
}
