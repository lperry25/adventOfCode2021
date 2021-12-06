package FileReader

import java.io.File

fun readFileToList(fileName: String): List<String> {
    val myList = mutableListOf<String>()
    File(fileName).useLines { lines -> myList.addAll(lines) }
    return myList
}

fun readEachFileLine(fileName: String, action: () -> String) {
    File(fileName).forEachLine { println(it) }
}