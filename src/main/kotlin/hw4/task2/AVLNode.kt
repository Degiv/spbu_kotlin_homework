package hw4.task2

class AVLNode<K : Comparable<K>, V>(private val privateKey: K, private var privateValue: V) : Map.Entry<K, V> {
    private var leftChild: AVLNode<K, V>? = null
    private var rightChild: AVLNode<K, V>? = null
    private val height: Int
        get() = kotlin.math.max((leftChild?.height ?: 0), (rightChild?.height ?: 0)) + 1
    private val balanceFactor: Int
        get() = (rightChild?.height ?: 0) - (leftChild?.height ?: 0)
    override val key: K
        get() = privateKey
    override val value: V
        get() = privateValue
    private val isLeaf: Boolean
        get() = leftChild == null && rightChild == null

    companion object {
        private const val balanceLimit = 1
    }

    private fun rotateLeft(): AVLNode<K, V>? {
        val updated = this.rightChild
        this.rightChild = updated?.leftChild
        updated?.leftChild = this
        return updated
    }

    private fun rotateRight(): AVLNode<K, V>? {
        val updated = this.leftChild
        this.leftChild = updated?.rightChild
        updated?.rightChild = this
        return updated
    }

    fun balance(): AVLNode<K, V>? {
        return when {
            this.balanceFactor > balanceLimit -> {
                if (rightChild?.balanceFactor ?: 0 < 0) {
                    rightChild = rightChild?.rotateRight()
                }
                this.rotateLeft()
            }
            this.balanceFactor < -balanceLimit -> {
                if (leftChild?.balanceFactor ?: 0 > 0) {
                    leftChild = leftChild?.rotateLeft()
                }
                this.rotateRight()
            }
            else -> this
        }
    }

    fun entries(entries: MutableSet<AVLNode<K, V>>) {
        entries.add(this)
        leftChild?.entries(entries)
        rightChild?.entries(entries)
    }

    fun add(key: K, value: V): V? {
        return when {
            key == privateKey -> {
                val oldValue = privateValue
                privateValue = value
                oldValue
            }
            key < privateKey -> {
                if (leftChild == null) {
                    leftChild = AVLNode(key, value)
                    null
                } else {
                    val oldValue = leftChild?.add(key, value)
                    leftChild = leftChild?.balance()
                    oldValue
                }
            }
            key > privateKey -> {
                if (rightChild == null) {
                    rightChild = AVLNode(key, value)
                    null
                } else {
                    val oldValue = rightChild?.add(key, value)
                    rightChild = rightChild?.balance()
                    oldValue
                }
            }
            else -> null
        }
    }

    private fun getMinChild(): AVLNode<K, V> = leftChild?.getMinChild() ?: this

    fun remove(key: K): AVLNode<K, V>? {
        return when {
            key < privateKey -> {
                leftChild = leftChild?.remove(key)
                leftChild = leftChild?.balance()
                this
            }
            key > privateKey -> {
                rightChild = rightChild?.remove(key)
                rightChild = rightChild?.balance()
                this
            }
            key == privateKey -> {
                when {
                    this.isLeaf -> {
                        null
                    }
                    leftChild == null -> {
                        rightChild
                    }
                    rightChild == null -> {
                        leftChild
                    }
                    else -> {
                        val minNode: AVLNode<K, V> = rightChild?.getMinChild() ?: this
                        this.remove(minNode.privateKey)
                        minNode.leftChild = this.leftChild
                        minNode.rightChild = this.rightChild
                        minNode.balance()
                    }
                }
            }
            else -> {
                this
            }
        }
    }

    fun get(key: K): AVLNode<K, V>? {
        return when {
            key > this.privateKey -> this.rightChild?.get(key)
            key < this.privateKey -> this.leftChild?.get(key)
            key == this.privateKey -> this
            else -> null
        }
    }

    fun containsValue(value: V): Boolean {
        return this.value == value ||
                leftChild?.containsValue(value) ?: false ||
                rightChild?.containsValue(value) ?: false
    }

    fun printPrefix() {
        print("${this.key} ${this.value} (")
        leftChild?.printPrefix() ?: print("null ")
        rightChild?.printPrefix() ?: print("null")
        print(") ")
    }
}
