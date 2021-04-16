package hw5.task1

interface ArithmeticTreeNode {
    fun calculate(): Int
    override fun toString(): String
}

class Operand(val value: Int) : ArithmeticTreeNode {
    override fun calculate(): Int {
        return value
    }

    override fun toString(): String {
        return value.toString()
    }
}

class Operation(
    var operation: Char,
    var leftChild: ArithmeticTreeNode,
    var rightChild: ArithmeticTreeNode
) : ArithmeticTreeNode {

    override fun toString(): String {
        return "($operation $leftChild $rightChild)"
    }

    override fun calculate(): Int {
        val leftResult = leftChild.calculate()
        val rightResult = rightChild.calculate()
        return when (operation) {
            '+' -> leftResult + rightResult
            '-' -> leftResult - rightResult
            '*' -> leftResult * rightResult
            '/' -> leftResult / rightResult
            else -> 0
        }
    }
}
