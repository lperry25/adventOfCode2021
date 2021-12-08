package day7

import FileReader.readFileAsIntList
import kotlin.math.abs
import kotlin.math.roundToInt


fun findTargetPosition(positions: List<Int>){
    var totalTargetPosition = positions.reduce { acc, pos -> acc + pos }
    println("total of the positions $totalTargetPosition")
    var totalPositions = positions.size
    println("total positions $totalPositions")
    println(totalTargetPosition/totalPositions)
}

fun complexFuelConsumption(movement: Int): Int{
    var fuelConsumption = 0
    for (i in 1..movement){
        fuelConsumption += i
    }
    return fuelConsumption
}

fun part2(crabPositions: List<Int>): Int{
    //findTargetPosition(crabPositions)
    println(crabPositions.sorted())
    // i assume that finding the average will be the best position now
    var average = crabPositions.average().roundToInt()
    println("move all crabs to position $average")
    // now each move will use a complex calculation of fuel
    var fuelUsed = crabPositions.map{ complexFuelConsumption(abs(it - average)) }
    println(fuelUsed)
    return fuelUsed.reduce { acc, fuel -> acc + fuel }
}

fun part1(crabPositions: List<Int>): Int{
    //findTargetPosition(crabPositions)
    println(crabPositions.sorted())
    // i assume that finding the median will be the best position to move to
    var median = crabPositions.sorted()[crabPositions.size/2]
    println("move all crabs to position $median")
    // now i need to calculate the distance to the median for each entry
    var fuelUsed = crabPositions.map{ abs(it - median) }
    println(fuelUsed)
    return fuelUsed.reduce { acc, fuel -> acc + fuel }
}

fun main(){
    //var fileName = "src/day7/testInput.txt"
    var fileName = "src/day7/myInput.txt"

    val crabPositions = readFileAsIntList(fileName)
    println(crabPositions)

    println("Part 1: The total fuel consumed is ${part1(crabPositions)}")
    println("Part 2: The total fuel consumed is ${part2(crabPositions)}")
    //println("Part 1: There are ${countFishForDays(fileName, totalDays)} after $totalDays days")
    //println("Part 2: There are ${setUpDiagram(fileName, true)} sections with more than 2 overlapping lines")
}
