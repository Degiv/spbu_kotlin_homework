package test1.task1

private class ListElement<T> (val data: T) {
    var next: ListElement<T>? = null
    var previous: ListElement<T>? = null
}

class DoublyLinkedList<T> {
    private var size = 0
    private var head: ListElement<T>? = null
    private var tail: ListElement<T>? = null
    val isEmpty: Boolean
        get() = size == 0

    fun isCorrectPosition(position: Int): Boolean {
        return position in 0 until size
    }

    fun add(data: T, position: Int = size) {
        if (!isCorrectPosition(position) && position != size) {
            error("Wrong position to add.")
        }

        val newElement = ListElement<T>(data)
        when (size) {
            0 -> {
                head = newElement
                tail = newElement
            }
            1 -> {
                if (position == 0) {
                    newElement.next = tail
                    tail?.previous = newElement
                    head = newElement
                } else {
                    newElement.previous = head
                    head?.next = newElement
                    tail = newElement
                }
            }
            else -> {
                when (position) {
                    0 -> {
                        newElement.next = head
                        head?.previous = newElement
                        head = newElement
                    }
                    size -> {
                        newElement.previous = tail
                        tail?.next = newElement
                        tail = newElement
                    }
                    else -> {
                        var current = head
                        repeat(position) {
                            current = current?.next
                        }
                        newElement.next = current
                        newElement.previous = current?.previous
                        newElement.previous?.next = newElement
                        current?.previous = newElement
                    }
                }
            }
        }
        ++size
    }

    fun remove(position: Int) {
        if (!isCorrectPosition(position)) {
            error("Wrong position to remove")
        }

        when (size) {
            1 -> {
                head = null
                tail = null
            }
            2 -> {
                if (position == 0) {
                    head = tail
                    head?.previous = null
                } else {
                    tail = head
                    tail?.next = null
                }
            }
            else -> {
                when (position) {
                    0 -> {
                        head = head?.next
                        head?.previous = null
                    }
                    size - 1 -> {
                        tail = tail?.previous
                        tail?.next = null
                    }
                    else -> {
                        var current = head
                        repeat(position) {
                            current = current?.next
                        }
                        current?.previous?.next = current?.next
                        current?.next?.previous = current?.previous
                    }
                }
            }
        }
        --size
    }

    fun get(position: Int = 0): T? {
        if (!isCorrectPosition(position)) {
            error("Wrong position to get")
        }
        var current = head
        repeat(position) {
            current = current?.next
        }
        return current?.data
    }
}
