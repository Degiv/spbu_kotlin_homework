package hw5.task2

interface HashFunction<K> {
    fun getHash(key: K): Int
}