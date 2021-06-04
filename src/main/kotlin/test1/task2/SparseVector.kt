package test1.task2

class SparseVector {
    private val data = mutableMapOf<Int, Int>()

    operator fun get(i: Int): Int {
        return data[i] ?: 0
    }

    operator fun set(i: Int, value: Int) {
        data[i] = value
    }

    operator fun plus(operand: SparseVector): SparseVector {
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
    operator fun times(operand: SparseVector): Int{
        var scalar = 0
        for (current in data) {
            scalar += current.value * (operand.data[current.key] ?: 0)
        }

        return scalar
    }
}
