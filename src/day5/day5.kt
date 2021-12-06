package day5

import FileReader.readFileToList
import java.io.File

class Vents(diagram: Array<IntArray>, includeDiagonal: Boolean){
    var diagram: Array<IntArray> = diagram;
    var includeDiagonal: Boolean = includeDiagonal;
    // top left corner is 0,0
    // bottom right corner is max

    fun addLine(x1: Int, y1: Int, x2: Int, y2: Int){
        if(x1 == x2){
            // it is a vertical line
            val range = if (y2 > y1) y1..y2 else y2..y1
            for(y in range){
                diagram[y][x1] += 1
            }
        }
        else if (y1 == y2){
            // it is a horizontal line
            val range = if(x2 > x1) x1..x2 else x2..x1
            for (x in range) {
                diagram[y1][x] += 1
            }
        }
        else if(includeDiagonal){
            if (y2 > y1){
                var startingX = x1
                var changeInX = if (x2 > x1) -1 else 1
                for (y in y1..y2){
                    diagram[y][startingX] += 1
                    startingX -= changeInX
                }
            } else if (y2 < y1){
                var startingX = x2
                var changeInX = if (x1 > x2) 1 else -1
                for (y in y2..y1){
                    diagram[y][startingX] += 1
                    startingX += changeInX
                }
            }
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

fun setUpDiagram(fileName: String, includeDiagonal: Boolean): Int {
    //get the largest number in the list
    val fileAsList = readFileToList(fileName)
    val allNumbers: List<Int> =
        fileAsList.flatMap { line -> line.split(" -> ").flatMap { pair -> pair.split(',') } }
            .map { stringNum -> stringNum.toInt() }
    val largestCoordinate: Int? = allNumbers.maxOrNull()

    if (largestCoordinate != null) {
        //initialize a grid 0s
        val initialGrid = Array(largestCoordinate + 1) { IntArray(largestCoordinate + 1) }

        var vents = Vents(initialGrid, includeDiagonal)

        // create the diagram
        File(fileName).forEachLine {
            var coordinates = it.split(" -> ").flatMap { pair -> pair.split(',') }
                .map { stringNum -> stringNum.toInt() }
            vents.addLine(coordinates[0], coordinates[1], coordinates[2], coordinates[3])
        }

        return vents.countInterSections()
    }
    return 0
}

fun main(){
    //var fileName = "src/day5/testInput.txt"
    var fileName = "src/day5/myInput.txt"

    println("Part 1: There are ${setUpDiagram(fileName, false)} sections with more than 2 overlapping lines excluding diagonals")
    println("Part 2: There are ${setUpDiagram(fileName, true)} sections with more than 2 overlapping lines")
}
