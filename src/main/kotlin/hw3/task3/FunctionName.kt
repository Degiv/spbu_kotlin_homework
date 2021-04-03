package hw3.task3

import kotlinx.serialization.Serializable

/**
 * Stores data about a function to test.
 * @param name Function name.
 */
@Serializable
data class FunctionName(val name: String)
