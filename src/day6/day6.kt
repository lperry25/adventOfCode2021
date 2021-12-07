package day6

import FileReader.readEntireFileContents

val newFishLife = 8
val oldFishReset = 6

fun newlySpawnFishChildren(days:Int): Int{
    println("new fish days ${days - newFishLife}")
    return 1 + ((days - newFishLife)/ oldFishReset)
}

fun existingFishChildren(initialLife: Int, days: Int): Int{
    println("days - initialLife ${days-initialLife} oldfish reset $oldFishReset ${(days-initialLife)/ oldFishReset}")
    return 1 + ((days - initialLife) / oldFishReset)
}

fun remainingDays(daysCounter: Int, roundCounter: Int): Int{
    return daysCounter - (newFishLife * roundCounter)
}

fun spawnEntireFishFamily(initialLife: Int, days: Int): Int{
    val parentFish = 1
    val initialFishChildren = existingFishChildren(initialLife, days)
    println("initial fish children $initialFishChildren for initial life of $initialLife and days $days")
    var dayCount = days
    var roundCount = 1
    var fishFamily = 0
    while (remainingDays(dayCount, roundCount) >= newFishLife){
        fishFamily += newlySpawnFishChildren(remainingDays(dayCount, roundCount))
        roundCount++
    }
    println("fish family $fishFamily")
    return parentFish + initialFishChildren + fishFamily
}

fun countFishForDays(fileName: String, days: Int): Int {
    //get the largest number in the list
    val initialFish: List<Int> = readEntireFileContents(fileName).split(',').map{it.toInt()}
    var numOfInitialFish = initialFish.size

    println("we start with $numOfInitialFish fish $initialFish")

    var totalFish = 0
    for (fishLife in initialFish){
        totalFish += spawnEntireFishFamily(fishLife, days)
    }
    return totalFish
}

fun main(){
    var fileName = "src/day6/testInput.txt"
    //var fileName = "src/day6/myInput.txt"

    val totalDays = 18
    println("Part 1: There are ${countFishForDays(fileName, totalDays)} after $totalDays days")
    //println("Part 2: There are ${setUpDiagram(fileName, true)} sections with more than 2 overlapping lines")
}
