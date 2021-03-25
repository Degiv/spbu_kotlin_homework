package hw1.task1

fun getFactorialIterative(x: Int): Int {
    var result = 1
    for (i in 2..x) {
        result *= i
    }
    return result
}

fun getFactorialRecursive(x: Int): Int {
    if (x < 2) {
        return 1
    }
    return getFactorialRecursive(x - 1) * x
}

fun main() {
    print("Enter a positive integer number: ")
    var x = readLine()?.toIntOrNull()
    while (x == null || x < 0) {
        print("Incorrect input. Try again:")
        x = readLine()?.toIntOrNull()
    }
    println("Factorial of your number by iterative method: ${getFactorialIterative(x)}")
    println("Factorial of your number by recursive method: ${getFactorialRecursive(x)}")
}
