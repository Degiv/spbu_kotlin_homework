package hw5.task2

import java.io.File

interface Action {
    val name: String
    fun perform(hashTable: HashTable<String, Int>)
}

val actions = listOf(Add(), Remove(), Find(), SetHashFunction(), FillFromFile(), ShowStatistic())

class Add : Action {
    override val name = "add"
    override fun perform(hashTable: HashTable<String, Int>) {
        val key = getKey()
        val value = getValue()
        hashTable.add(key, value)
    }
}

class Remove : Action {
    override val name = "remove"
    override fun perform(hashTable: HashTable<String, Int>) {
        val key = getKey()
        hashTable.remove(key)
    }
}

class Find : Action {
    override val name = "find"
    override fun perform(hashTable: HashTable<String, Int>) {
        println(hashTable[getKey()] ?: "Not found")
    }
}

class SetHashFunction : Action {
    override val name = "set hash"
    override fun perform(hashTable: HashTable<String, Int>) {
        println("Enter the name of hash function you want to use(${hashFunctions.joinToString { it.name }})")
        val name = readLine().toString()
        val newHashFunction = hashFunctions.find { name == it.name } ?: error("Wrong input.")
        hashTable.setHashFunction(newHashFunction)
    }
}

class FillFromFile : Action {
    override val name = "fill"

    override fun perform(hashTable: HashTable<String, Int>) {
        println("Enter file name:")
        val fileName = readLine().toString()
        val input = File(javaClass.getResource(fileName).path).readText()
        val pairs = input.replace("(", "").replace(")", "").split("; ")
        pairs.forEach {
            val pair = it.split(", ")
            hashTable.add(pair.first(), pair.last().toInt())
        }
    }
}

class ShowStatistic : Action {
    override val name = "statistic"

    override fun perform(hashTable: HashTable<String, Int>) {
        hashTable.showStatistic()
    }
}
