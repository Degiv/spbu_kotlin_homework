package hw2.task2

/**
 * inputs list of integers.
 * @return list of integers.
 */
fun inputIntList(): List <Int> {
    println("Enter your array of numbers in one line: ")
    val numbersAsStrings = readLine().toString().split(" ")
    return numbersAsStrings.map { it.toIntOrNull() ?: error("Incorrect input.")}
}

fun <T> List<T>.withoutDuplicates(): List<T> {
    return this.asReversed().distinct().asReversed()
}

fun main() {
    val numbers = inputIntList()
    print("Your array without duplicates: ${numbers.withoutDuplicates()}")
}
