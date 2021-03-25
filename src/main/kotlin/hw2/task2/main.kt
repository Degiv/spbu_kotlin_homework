package hw2.task2

/**
 * inputs list of integers.
 * @return list of integers.
 */
fun inputIntList(): MutableList <Int> {
    println("Enter your array of numbers in one line: ")
    val numbersAsStrings = readLine().toString().split(" ")
    val numbers = mutableListOf<Int>()
    numbersAsStrings.forEach { numbers.add(it.toIntOrNull() ?: error("Incorrect input.")) }
    return numbers
}

fun <T> List<T>.withoutDuplicates(): List<T> {
    return this.asReversed().distinct().asReversed()
}

fun main() {
    val numbers = inputIntList()
    print("Your array without duplicates: ${numbers.withoutDuplicates()}")
}
