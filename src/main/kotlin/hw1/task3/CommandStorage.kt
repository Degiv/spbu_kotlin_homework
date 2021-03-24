package hw1.task3

import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Class for storing a list of numbers and performed actions on it
 */
class CommandStorage {
    val data = mutableListOf<Int>()
    val commandList = mutableListOf<Action>()

    /**
     * Adds the action to list of performed actions
     */
    fun addAction(newAction: Action) {
        commandList.add(newAction)
    }

    /**
     * Undoes last performed action
     */
    fun undoLastAction() {
        if (commandList.isEmpty()) {
            println("Nothing to undo")
            return
        }
        val lastIndex: Int = commandList.lastIndex
        commandList[lastIndex].undo(this)
        commandList.removeLast()
    }

    /**
     * Serialize [commandList] into file.
     * @param [fileName]
     */
    fun serialize(fileName: String) {
        File(fileName).writeText(Json.encodeToString(commandList))
    }

    /**
     * Deserialize list of [Action] from file.
     * @param [fileName]
     */
    fun deserialize(fileName: String) {
        val listOfActions: MutableList<Action> = Json.decodeFromString(File(fileName).readText())
        listOfActions.forEach { it.perform(this) }
    }
}
