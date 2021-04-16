package hw5.task2

interface HashFunction<K> {
    fun getHash(key: K, mod: Int): Int
}

class PolynomialHashFunction : HashFunction<String> {
    companion object {
        const val QUOTIENT = 31
    }
    override fun getHash(key: String, mod: Int): Int {
        val QUOTIENT = 31
        var result = 0
        key.toCharArray().forEach { result = (result * QUOTIENT + it.toInt()) % mod }
        return result
    }
}

class SumHashFunction : HashFunction<String> {
    override fun getHash(key: String, mod: Int): Int {
        var result = 0
        key.toCharArray().forEach { result = (result + it.toInt()) % mod }
        return result
    }
}

class XorHashFunction : HashFunction<String> {
    override fun getHash(key: String, mod: Int): Int {
        var result = 0
        key.toCharArray().forEach { result = result xor it.toInt() }
        return result % mod
    }
}
