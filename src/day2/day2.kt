package day2

import java.util.*
import java.io.File
import java.io.InputStream

fun readFileToList(fileName: String): List<String> {
    val myList = mutableListOf<String>()
    File(fileName).useLines { lines -> myList.addAll(lines) }
    return myList
}

fun part1(input: List<String>): Array<Int> {
    var coordinates: Array<Int> = arrayOf(0, 0)
    for (directionLine in input){
        var commandSplit: List<String> = directionLine.split(" ")
        var direction: String = commandSplit[0]
        var value: Int = commandSplit[1].toInt()
        if (direction.equals("forward")){
            coordinates[0] = coordinates[0] + value
            println(coordinates[0])
        } else if (direction.equals("back")){
            coordinates[0] = coordinates[0] - value
        } else if (direction.equals("down")){
            coordinates[1] = coordinates[1] + value
        } else if (direction.equals("up")){
            coordinates[1] = coordinates[1] - value
        }
    }
    return coordinates
}

fun part2(input: List<String>) {

}

fun main(){
    var input: List<String> = readFileToList("myInput.txt")
    //var input: List<String> = readFileToList("testInput.txt")
    var part1Result = part1(input)
    println("horizontal  ${part1Result[0]} depth ${part1Result[1]}")
    println("product ${part1Result[0] * part1Result[1]}")
   // var part2Result = part2(input)
}
