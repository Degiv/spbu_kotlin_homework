package hw5.task1

import java.io.File

class ArithmeticTree(path: String) {
    private val root: ArithmeticTreeNode
    private val expression: String
    private var index = 0

    init {
        expression = File(path).readText()
        root = parseTree()
    }

    private fun isNumber(char: Char) = char in '0'..'9'

    private fun parseTree(): ArithmeticTreeNode {
        if (expression[index] == '(') {
            index++
            val operation = expression[index++]
            index++
            val leftChild = parseTree()
            index++
            val rightChild = parseTree()
            index++
            return Operation(operation, leftChild, rightChild)
        } else {
            return Operand(Character.getNumericValue(expression[index++]))
        }
    }

    fun calculate(): Int {
        return root.calculate()
    }

    fun print() {
        print(root.toString())
    }
}
