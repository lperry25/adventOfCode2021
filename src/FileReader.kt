package FileReader

import java.io.File

fun readFileToList(fileName: String): List<String> {
    val myList = mutableListOf<String>()
    File(fileName).useLines { lines -> myList.addAll(lines) }
    return myList
}

fun readEntireFileContents(fileName: String): String {
    var fileContents = ""
    File(fileName).forEachLine { fileContents += it }
    return fileContents
}

fun readFileAsIntList(fileName: String): List<Int>{
    return readEntireFileContents(fileName).split(',').map{it.toInt()}
}