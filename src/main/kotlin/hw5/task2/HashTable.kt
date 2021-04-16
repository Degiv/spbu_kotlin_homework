package hw5.task2

class HashTable<K, V>(private var hashFunction: HashFunction<K>) {

    private data class Element<K, V>(val key: K, var value: V)

    private var size = 1
    private var dataArray: Array<MutableList<Element<K, V>>> = Array(size) { mutableListOf() }
    private var elementCounter = 0
    private var conflictCounter = 0
    private val loadFactor: Double
        get() = elementCounter / size.toDouble()

    companion object {
        const val FACTOR_LIMIT = 0.7
    }

    fun add(key: K, value: V) {
        val hash = hashFunction.getHash(key) % size
        val element = dataArray[hash].find { key == it.key }
        if (element != null) {
            if (value != element.value) {
                element.value = value
            }
        } else {
            dataArray[hash].add(Element(key, value))
            ++elementCounter
        }
        if (loadFactor >= FACTOR_LIMIT) {
            this.expand()
        }
    }

    private fun expand() {
        size *= 2
        updateHashTable()
    }

    private fun updateHashTable() {
        val newArray = Array(size) { mutableListOf<Element<K, V>>() }
        dataArray.forEach { list ->
            list.forEach { element ->
                val hash = hashFunction.getHash(element.key) % (size)
                newArray[hash].add(element)
            }
        }
        dataArray = newArray
    }

    fun remove(key: K): Boolean {
        val hash = hashFunction.getHash(key) % (size)
        val element = dataArray[hash].find { key == it.key }
        if (element != null) {
            dataArray[hash].remove(element)
            --elementCounter
        }
        return element != null
    }

    fun get(key: K): V? {
        val hash = hashFunction.getHash(key) % size
        return dataArray[hash].find { key == it.key }?.value
    }

    fun contains(key: K): Boolean {
        val hash = hashFunction.getHash(key) % size
        return dataArray[hash].find { key == it.key } != null
    }

    fun setHashFunction(newHashFunction: HashFunction<K>) {
        hashFunction = newHashFunction
        updateHashTable()
    }

    fun showStatistic(): String {
        TODO()
    }
}