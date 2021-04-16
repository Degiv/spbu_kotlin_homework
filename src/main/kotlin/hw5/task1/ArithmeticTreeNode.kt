package hw5.task1

class ArithmeticTreeNode {
    var asChar: Char = '\n'
    var leftChild: ArithmeticTreeNode? = null
    var rightChild: ArithmeticTreeNode? = null

    private val isNumber: Boolean
        get() = asChar in '0'..'9'

    fun print() {
        print(asChar)
    }

    fun calculate(): Int {
        return if (isNumber) {
            Character.getNumericValue(asChar)
        } else {
            val leftResult = leftChild?.calculate() ?: throw NullPointerException("leftChild is null.")
            val rightResult = rightChild?.calculate() ?: throw NullPointerException("rightChild is null.")
            when (asChar) {
                '+' -> leftResult + rightResult
                '-' -> leftResult - rightResult
                '*' -> leftResult * rightResult
                '/' -> leftResult / rightResult
                else -> 0
            }
        }
    }
}
