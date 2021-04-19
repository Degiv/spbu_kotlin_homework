package hw5.task2

fun getKey(): String {
    println("Enter key:")
    return readLine().toString()
}

fun getValue(): Int {
    println("Enter value:")
    return readLine()?.toInt() ?: error("Wrong input.")
}
