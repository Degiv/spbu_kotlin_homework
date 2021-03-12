package hw1.task2

fun String.countOccurrences(substring: String): Int {
    if (this == "" || substring == "") {
        return 0
    }

    var occurrencesCounter = 0
    var index = this.indexOf(substring)
    while (index != -1) {
        occurrencesCounter++
        index = this.indexOf(substring, index + 1)
    }
    return occurrencesCounter
}

fun main() {
    print("Enter the string to search in: ")
    val stringToSearchIn: String = readLine().toString()
    print("Enter the string to find: ")
    val stringToFind: String = readLine().toString()
    println("Number of occurrences is: ${stringToSearchIn.countOccurrences(stringToFind)}")
}