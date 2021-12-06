package day4

import java.io.File

val foundMarker = -1;

class BingoCard(card: MutableList<MutableList<Int>>){
    var card: MutableList<MutableList<Int>> = card;

    fun addLine(newLine: String){
        var newLineInt = newLine.trim(' ').split("\\s+".toRegex()).map{ newNum -> newNum.toInt()}.toMutableList()
        card.add(newLineInt)
    }

    fun findMatch(match: Int){
        // loop through each row and column looking for a match
        for(i in 0..4){
            for (j in 0..4){
                if (card[j][i] == match){
                    card[j][i] = foundMarker
                    break
                }
            }
        }
    }

    fun hasCompleteRow(): Boolean{
        card.forEach{ row ->
            if(row.all { it == foundMarker }) return true
        }
        return false
    }

    fun hasCompleteCol(): Boolean{
        // need to loop through each column looking for foundMarkers in each column
        for(i in 0..4){
            var allMatch = true
            for (j in 0..4){
                if (card[j][i] != foundMarker){
                    allMatch = false
                }
            }
            if (allMatch) {
                return true
            };
        }
        return false;
    }

    fun hasWon(): Boolean{
        return hasCompleteRow() || hasCompleteCol()
    }

    fun calculateSum(): Int{
        // calculate the sum of all the number that have not been found
        var sum: Int = 0
        for(i in 0..4){
            for (j in 0..4){
                if (card[j][i] > foundMarker){
                    sum += card[j][i]
                }
            }
        }
        return sum
    }
}

fun playBingo(playedNumbers: List<Int>, boards: List<BingoCard>): Int{
    println("Let\'s play Bingo!!")
    for (number in playedNumbers){
        println("we played $number")
        boards.forEach{
            // mark the played number
            it.findMatch(number)
            // check for a win
            if (it.hasWon()){
                val sum = it.calculateSum()
                println("Bingo! The last number played was $number and the sum was $sum")
                return sum * number;
            }
        }
    }
    println("No one won")
    return 0
}

fun part1(fileName: String): Int{
    // generate the board
    var playedNumbers: List<Int> = emptyList()
    var boards: MutableList<BingoCard> = emptyList<BingoCard>().toMutableList()
    var boardIndex = -1
    File(fileName).forEachLine {
        if (it.contains(',')) {
            playedNumbers = it.split(",").map{ playedNum -> playedNum.toInt() }
        }
        else if (it.equals("")){
            boardIndex++
            var emptyInnerList = emptyList<Int>().toMutableList()
            // this is not working great cause i have an empty first list everytime
            boards.add(boardIndex, BingoCard(listOf(emptyInnerList).toMutableList()))
        }
        else {
            boards[boardIndex].addLine(it)
        }
    }
    // since i initialized each list with an empty list i have to remove it again... this sucks
    boards.forEach{ it.card.removeFirst() }

    // now I have my boards and played numbers so I can play!
    return playBingo(playedNumbers, boards)
}

fun part2(input: List<String>): Int {
    return 1
}

fun main(){
    //var fileName = "src/day4/testInput.txt"
    var fileName = "src/day4/myInput.txt"

    println("The final score is ${part1(fileName)}")

}
