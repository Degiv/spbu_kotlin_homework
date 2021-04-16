package hw5.task2

fun userInterface() {
    val hashTable = HashTable<String, Int>(PolynomialHashFunction())
    while (true) {
        println("Actions:")
        actions.forEach { print(it.name + "; ") }
        println()
        println("Enter action name to perform or 'exit' to finish:")
        val command = readLine().toString()

        if (command == "exit") {
            return
        }

        actions.find { it.name == command }?.perform(hashTable) ?: println("Wrong input.")
    }
}

fun main() {
    userInterface()
}
