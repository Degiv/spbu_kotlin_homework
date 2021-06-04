package hw6

class   MergeSorterMT {
    data class Subarray(val leftBound: Int, val rightBound: Int)

    fun mergeSortMT(arrayToSort: IntArray, numberOfThreads: Int) {
        if (arrayToSort.isEmpty()) {
            return
        }
        val tempArray = IntArray(arrayToSort.size) { 0 }
        arrayToSort.mergeSortingMultiThread(
            Subarray(0, arrayToSort.lastIndex),
            sortedArray = tempArray,
            numberOfThreads = numberOfThreads
        )
        tempArray.copyInto(arrayToSort)
    }

    private fun IntArray.binarySearch(valueToFind: Int, left: Int, right: Int): Int {
        var low = left
        var high = kotlin.math.max(left, right + 1)
        var mid: Int
        while (low < high) {
            mid = (low + high) / 2
            if (valueToFind <= this[mid]) {
                high = mid
            } else {
                low = mid + 1
            }
        }
        return high
    }

    private fun IntArray.mergeMT(
        firstPart: Subarray,
        secondPart: Subarray,
        arrayMergeTo: IntArray,
        leftBoundOfRemainder: Int,
        numberOfThreads: Int = 1
    ) {
        val firstPartSize = firstPart.rightBound - firstPart.leftBound + 1
        val secondPartSize = secondPart.rightBound - secondPart.leftBound + 1
        if (firstPartSize < secondPartSize) {
            this.mergeMT(
                secondPart,
                firstPart,
                arrayMergeTo,
                leftBoundOfRemainder
            )
            return
        }
        if (firstPartSize == 0) {
            return
        }
        val midOfFirstPart =
            (firstPart.leftBound + firstPart.rightBound) / 2
        val midOfSecondPart =
            this.binarySearch(this[midOfFirstPart], secondPart.leftBound, secondPart.rightBound)
        val halfOfFirstPartSize = midOfFirstPart - firstPart.leftBound
        val halfOfSecondPartSize = midOfSecondPart - secondPart.leftBound
        val midOfRemainder = leftBoundOfRemainder + halfOfFirstPartSize + halfOfSecondPartSize

        arrayMergeTo[midOfRemainder] = this[midOfFirstPart]
        val numberOfLeftThreads = numberOfThreads / 2
        val numberOfRightThreads = numberOfThreads - numberOfLeftThreads
        val leftThread =
            Thread {
                this.mergeMT(
                    Subarray(firstPart.leftBound, midOfFirstPart - 1),
                    Subarray(secondPart.leftBound, midOfSecondPart - 1),
                    arrayMergeTo,
                    leftBoundOfRemainder,
                    numberOfLeftThreads
                )
            }
        val rightThread =
            Thread {
                this.mergeMT(
                    Subarray(midOfFirstPart + 1, firstPart.rightBound),
                    Subarray(midOfSecondPart, secondPart.rightBound),
                    arrayMergeTo,
                    midOfRemainder + 1,
                    numberOfRightThreads
                )
            }
        leftThread.start()
        rightThread.start()
        leftThread.join()
        rightThread.join()
    }

    private fun IntArray.mergeSortingMultiThread(
        sortingPart: Subarray,
        sortedArray: IntArray,
        leftBoundOfRemainder: Int = 0,
        numberOfThreads: Int = 1
    ) {
        val sortingPartSize = sortingPart.rightBound - sortingPart.leftBound + 1
        if (sortingPartSize == 1) {
            sortedArray[leftBoundOfRemainder] = this[sortingPart.leftBound]
        } else {
            val tempArray = IntArray(sortingPartSize) { 0 }
            val mid = (sortingPart.leftBound + sortingPart.rightBound) / 2
            val newMiddle = mid - sortingPart.leftBound
            if (numberOfThreads == 1) {
                this.mergeSortingMultiThread(
                    Subarray(sortingPart.leftBound, mid),
                    tempArray, 0
                )
                this.mergeSortingMultiThread(
                    Subarray(mid + 1, sortingPart.rightBound),
                    tempArray,
                    newMiddle + 1
                )
            } else {
                val numberOfLeftThreads = numberOfThreads / 2
                val numberOfRightThreads = numberOfThreads - numberOfLeftThreads
                val leftThread =
                    Thread {
                        this.mergeSortingMultiThread(
                            Subarray(sortingPart.leftBound, mid),
                            tempArray, 0,
                            numberOfLeftThreads
                        )
                    }
                val rightThread =
                    Thread {
                        this.mergeSortingMultiThread(
                            Subarray(mid + 1, sortingPart.rightBound),
                            tempArray, newMiddle + 1,
                            numberOfRightThreads
                        )
                    }
                leftThread.start()
                rightThread.start()
                leftThread.join()
                rightThread.join()
            }
            tempArray.mergeMT(
                Subarray(0, newMiddle),
                Subarray(newMiddle + 1, sortingPartSize - 1),
                sortedArray,
                leftBoundOfRemainder,
                numberOfThreads
            )
        }
    }
}
