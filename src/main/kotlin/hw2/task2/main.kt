package hw2.task2

/**
 * inputs list of integers.
 * @param {String} [prompt] - user input prompt.
 * @return list of integers.
 */
fun inputIntList(prompt: String = ""): MutableList <Int> {
    println(prompt)
    val numbersAsStrings: List <String> = readLine().toString().split(" ")
    val numbers = mutableListOf<Int>()
    for (numberAsString in numbersAsStrings) {
        val number = numberAsString.toIntOrNull() ?: error("Incorrect input.")
        numbers.add(number)
    }
    return numbers
}

fun main() {
    val numbers = inputIntList("Enter your array of numbers in one line: ")
    print("Your array without duplicates: ${numbers.asReversed().distinct().asReversed()}")
}
