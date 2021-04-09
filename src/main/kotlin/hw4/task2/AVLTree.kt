package hw4.task2

class AVLTree<K : Comparable<K>, V> : Map<K, V> {
    private var root: AVLNode<K, V>? = null
    private var _size = 0

    override val entries: Set<Map.Entry<K, V>>
        get() {
            val entries = mutableSetOf<AVLNode<K, V>>()
            root?.entries(entries) ?: emptySet<AVLNode<K, V>>()
            return entries
        }

    override val keys: Set<K>
        get() {
            return entries.map { it.key }.toSet()
        }

    override val size: Int
        get() = _size

    override val values: Collection<V>
        get() {
            return entries.map { it.value }
        }

    override fun containsValue(value: V) = root?.containsValue(value) ?: false

    override fun get(key: K) = root?.get(key)?.value

    override fun containsKey(key: K): Boolean = get(key) != null

    override fun isEmpty(): Boolean = root == null

    fun add(key: K, value: V): V? {
        return if (this.isEmpty()) {
            root = AVLNode(key, value)
            ++_size
            null
        } else {
            val oldValue = root?.add(key, value)
            if (oldValue == null) {
                ++_size
            }
            root = root?.balance()
            oldValue
        }
    }

    fun remove(key: K): V? {
        val previousValue: V? = this[key]
        if (previousValue != null) {
            --_size
        }
        root = root?.remove(key)
        root = root?.balance()
        return previousValue
    }

    fun printPrefix() {
        root?.printPrefix() ?: print("null")
        println()
    }
}
