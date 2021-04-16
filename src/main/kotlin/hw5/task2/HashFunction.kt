package hw5.task2

interface HashFunction<K> {
    val name: String
    fun getHash(key: K, mod: Int): Int
}

val hashFunctions = listOf(PolynomialHashFunction(), SumHashFunction(), XorHashFunction())

class PolynomialHashFunction : HashFunction<String> {

    override val name = "Polynomial"

    override fun getHash(key: String, mod: Int): Int {
        var result = 0
        key.toCharArray().forEach { result = (result * Companion.QUOTIENT + it.toInt()) % mod }
        return result
    }

    companion object {
        private const val QUOTIENT = 31
    }
}

class SumHashFunction : HashFunction<String> {
    override val name = "Sum"

    override fun getHash(key: String, mod: Int): Int {
        var result = 0
        key.toCharArray().forEach { result = (result + it.toInt()) % mod }
        return result
    }
}

class XorHashFunction : HashFunction<String> {
    override val name = "Xor"

    override fun getHash(key: String, mod: Int): Int {
        var result = 0
        key.toCharArray().forEach { result = result xor it.toInt() }
        return result % mod
    }
}
