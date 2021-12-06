package day5

import FileReader.readFileToList
import java.io.File

class Vents(diagram: Array<IntArray>){
    var diagram: Array<IntArray> = diagram;
    // top left corner is 0,0
    // bottom right corner is max

    fun addLine(x1: Int, y1: Int, x2: Int, y2: Int){
        if(x1 == x2){
            // it is a vertical line
            println("vertical: add from $y1 to $y2 at col $x2")
            val range = if (y2 > y1) y1..y2 else y2..y1
            for(y in range){
                diagram[y][x1] += 1
            }
        }
        else if (y1 == y2){
            // it is a horizontal line
            println("horizontal: add from $x1 to $x2 at row $y1")
            val range = if(x2 > x1) x1..x2 else x2..x1
            for (x in range) {
                diagram[y1][x] += 1
            }
        }
        else{
            println("diagonal line is ignored for now")
        }
    }

    fun countInterSections(): Int{
        var count = 0
        for (row in diagram) {
            for (entry in row){
                if(entry > 1) count++
            }
        }
        return count;
    }
}

fun setUpDiagram(fileName: String) {
    //get the largest number in the list
    val fileAsList = readFileToList(fileName)
    val allNumbers: List<Int> =
        fileAsList.flatMap { line -> line.split(" -> ").flatMap { pair -> pair.split(',') } }
            .map { stringNum -> stringNum.toInt() }
    val largestCoordinate: Int? = allNumbers.maxOrNull()
    println("The largest coordinate is $largestCoordinate")

    if (largestCoordinate != null) {
        //initialize a grid 0s
        val initialGrid = Array(largestCoordinate + 1) { IntArray(largestCoordinate + 1) }
        println("initial grid of zeros ${largestCoordinate + 1} x ${largestCoordinate + 1}")

        var vents = Vents(initialGrid)

        // create the diagram
        File(fileName).forEachLine {
            var coordinates = it.split(" -> ").flatMap { pair -> pair.split(',') }
                .map { stringNum -> stringNum.toInt() }
            vents.addLine(coordinates[0], coordinates[1], coordinates[2], coordinates[3])
        }

        println("final state ${vents.diagram.size}")
        println("There are ${vents.countInterSections()} sections with more than 2 overlapping lines")
    }
}

fun main(){
    //var fileName = "src/day5/testInput.txt"
    var fileName = "src/day5/myInput.txt"

    setUpDiagram(fileName)
}
