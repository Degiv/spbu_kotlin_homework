package hw1.task3

enum class Command {
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

fun main() {
    val storage = CommandStorage()
    showCommandsHint()
    var mustContinue: Boolean = true
    do {
        showInputHint()
        var command = readLine()?.toIntOrNull()
        while (command == null || command < Command.EXIT_COMMAND.ordinal || command > Command.UNDO_COMMAND.ordinal) {
            println("Incorrect input. Try again:")
            command = readLine()?.toIntOrNull()
        }

        when (command) {
            Command.EXIT_COMMAND.ordinal -> mustContinue = false

            Command.ADD_TO_START_COMMAND.ordinal -> {
                println("Enter a number to add to start:")
                var newValue = readLine()?.toIntOrNull()
                while (newValue == null) {
                    println("Cant be parsed as number. Try again:")
                    newValue = readLine()?.toIntOrNull()
                }
                InsertForward(newValue, storage).perform()
            }

            Command.ADD_TO_END_COMMAND.ordinal -> {
                println("Enter a number to add to end:")
                var newValue = readLine()?.toIntOrNull()
                while (newValue == null) {
                    println("Cant be parsed as number. Try again:")
                    newValue = readLine()?.toIntOrNull()
                }
                InsertBack(newValue, storage).perform()
            }

            Command.MOVE_COMMAND.ordinal -> {
                println("Enter the index move from:")
                var indexFrom = readLine()?.toIntOrNull()
                while (indexFrom == null || indexFrom !in storage.data.indices) {
                    println("Wrong input. Try again:")
                    indexFrom = readLine()?.toIntOrNull()
                }

                println("Enter the index move to")
                var indexTo = readLine()?.toIntOrNull()
                while (indexTo == null || indexTo !in storage.data.indices) {
                    println("Wrong input. Try again:")
                    indexTo = readLine()?.toIntOrNull()
                }

                Move(indexFrom, indexTo, storage).perform()
            }

            Command.UNDO_COMMAND.ordinal -> {
                storage.undoLastAction()
            }

            Command.PRINT_COMMAND.ordinal -> {
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
