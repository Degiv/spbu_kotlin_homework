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

    private fun parseTree(): ArithmeticTreeNode {
        val current = ArithmeticTreeNode()
        if (expression[index] == '(') {
            index++
            current.asChar = expression.get(index++)
            index++
            current.leftChild = parseTree()
            index++
            current.rightChild = parseTree()
            index++
        } else {
            current.asChar = expression.get(index++)
        }
        return current
    }

    fun calculate(): Int {
        return root.calculate()
    }

    private fun printRecursive(current: ArithmeticTreeNode) {
        if (current.leftChild != null) {
            print('(')
            current.print()
            print(' ')
            printRecursive(current.leftChild!!)
            print(' ')
            printRecursive(current.rightChild!!)
            print(')')
        } else {
            current.print()
        }
    }

    fun print() {
        printRecursive(root)
    }
}
