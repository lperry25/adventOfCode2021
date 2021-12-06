package day6

import FileReader.readEntireFileContents

val newFishLife = 8
val oldFishReset = 6

class FishLife(timer: Int){
    var timer = timer

    fun spanNewFish(): Boolean{
        if (timer > 0){
            // countdown the timer
            timer -= 1
            return false
        }
        // span a new fish and reset timer to initial count
        timer = oldFishReset
        return true
    }
}

fun countFishForDays(fileName: String, days: Int): Int {
    //get the largest number in the list
    val initialFish: List<Int> = readEntireFileContents(fileName).split(',').map{it.toInt()}

    var totalFish: MutableList<FishLife> = initialFish.map{life -> FishLife(life)}.toMutableList()
    var day = 1
    while (day <= days){
        println("day $day")
        var newTotalFish: MutableList<FishLife> = emptyList<FishLife>().toMutableList()
        totalFish.map{ fish ->
            if (fish.spanNewFish()) {
                newTotalFish.add(fish)
                newTotalFish.add(FishLife(newFishLife))
            } else{
                newTotalFish.add(fish)
            }
        }
        totalFish = newTotalFish
        day++
    }
    return totalFish.size
}

fun main(){
    var fileName = "src/day6/testInput.txt"
    //var fileName = "src/day6/myInput.txt"

    println("Part 1: There are ${countFishForDays(fileName, 80)} after 80 days")
    //println("Part 2: There are ${setUpDiagram(fileName, true)} sections with more than 2 overlapping lines")
}
