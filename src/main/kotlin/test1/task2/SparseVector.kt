package test1.task2

class SparseVector{
    private val data = mutableMapOf<Int, Int>()
    private var size = 0

    operator fun get(i: Int): Int {
        return data[i] ?: 0
    }

    operator fun set(i: Int, value: Int) {
        if (i >= size) {
            size = i + 1
        }
        data[i] = value
    }

    private fun sizeCheck(vectorFirst: SparseVector, vectorSecond: SparseVector) {
        if (vectorFirst.size != vectorSecond.size) {
            error("Different size of vectors.")
        }
    }

    operator fun plus(operand: SparseVector): SparseVector {
        sizeCheck(this, operand)

        val sum = SparseVector()
        for (current in data) {
            sum.data[current.key] = current.value + (operand.data[current.key] ?: 0)
        }

        for (current in operand.data) {
            if (!data.containsKey(current.key)) {
                sum.data[current.key] = current.value + (data[current.key] ?: 0)
            }
        }
        return sum
    }

    operator fun minus(operand: SparseVector): SparseVector {
        sizeCheck(this, operand)

        val subtract = SparseVector()
        for (current in data) {
            subtract.data[current.key] = current.value - (operand.data[current.key] ?: 0)
        }

        for (current in operand.data) {
            if (!data.containsKey(current.key)) {
                subtract.data[current.key] = (data[current.key] ?: 0) - current.value
            }
        }
        return subtract
    }

    /**
     * Scalar product
     */
    operator fun times(operand: SparseVector): Int {
        sizeCheck(this, operand)
        
        var scalar = 0
        for (current in data) {
            scalar += current.value * (operand.data[current.key] ?: 0)
        }

        return scalar
    }
}
