package test1.task2

interface ArithmeticAvailable<T> {
    abstract operator fun plus(operand: T): T
    abstract operator fun minus(operand: T): T
    abstract operator fun times(operand: T): Int
}

class SparseVector<T: ArithmeticAvailable<T>> {
    private val data = mutableMapOf<Int, T>()
    private var size = 0

    operator fun get(i: Int): T {
        if (i >= size) {
            error("Index is out of bounds.")
        }
        return data[i] ?: error("Element doesn't exist.")
    }

    operator fun set(i: Int, value: T) {
        if (i >= size) {
            size = i + 1
        }
        data[i] = value
    }

    fun addAll(elements: List<Pair<Int, T>>) {
        elements.forEach {
            data[it.first] = it.second
        }
    }

    private fun sizeCheck(vectorFirst: SparseVector<T>, vectorSecond: SparseVector<T>) {
        if (vectorFirst.size != vectorSecond.size) {
            error("Different size of vectors.")
        }
    }

    operator fun plus(operand: SparseVector<T>): SparseVector<T> {
        sizeCheck(this, operand)

        val sum = SparseVector<T>()
        for (current in data) {
            sum.data[current.key] = current.value + (operand.data[current.key] ?: 0 as T)
            if (operand.data[current.key] != null) {
                sum.data[current.key] = sum.data[current.key] + (operand.data[current.key] ?: 0 as T)
            }
        }

        for (current in operand.data) {
            if (!data.containsKey(current.key)) {
                sum.data[current.key] = current.value
                if (data[current.key] != null) {
                    sum.data[current.key] = sum.data[current.key] + data[current.key]
                }
            }
        }
        return sum
    }

    operator fun minus(operand: SparseVector<T>): SparseVector<T> {
        sizeCheck(this, operand)

        val subtract = SparseVector<T>()
        for (current in data) {
            subtract.data[current.key] = current.value - (operand.data[current.key] ?: 0 as T)
        }

        for (current in operand.data) {
            if (!data.containsKey(current.key)) {
                subtract.data[current.key] = (data[current.key] ?: 0 as T) - current.value
            }
        }
        return subtract
    }

    /**
     * Scalar product
     */
    operator fun times(operand: SparseVector<T>): Int {
        sizeCheck(this, operand)

        var scalar = 0
        for (current in data) {
            scalar += (current.value * (operand.data[current.key] ?: 0 as T))
        }

        return scalar
    }
}
