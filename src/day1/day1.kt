import java.util.*

fun part1() {
    val reader = Scanner(System.`in`)
    println("input:")
    var increasedCount = 0
    var decreasedCount = 0
    var previousVal = reader.nextInt()
    do{
        val currentVal = reader.nextInt()
        if (currentVal > previousVal) {
            increasedCount += 1
        } else{
            decreasedCount += 1
        }
        previousVal = currentVal
        var hasNext = reader.hasNextInt()
        println("$previousVal, $currentVal $hasNext")
    }while(hasNext)
    println("There were $increasedCount increases and $decreasedCount decreases")
}

fun part2() {
    val reader = Scanner(System.`in`)
    println("input:")
    var increasedCount = 0
    var decreasedCount = 0
    var window1 = reader.nextInt()
    var window2 = reader.nextInt()
    var window3 = reader.nextInt()
    var previousWindow = window1 + window2 + window3
    do{
        window1 = window2
        window2 = window3
        window3 = reader.nextInt()
        var currentWindow = window1 + window2 + window3
        println("$previousWindow $currentWindow ")
        if (currentWindow > previousWindow) {
            increasedCount += 1
        } else{
            decreasedCount += 1
        }
        previousWindow = currentWindow
        var hasNext = reader.hasNextInt()
    }while(hasNext)
    println("There were $increasedCount sums that are larger and $decreasedCount sums that are smaller")
}

fun main(){
    part2()
}