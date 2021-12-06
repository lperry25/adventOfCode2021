package day4

import java.io.File

fun readFileToList(fileName: String): List<String> {
    val myList = mutableListOf<String>()
    File(fileName).useLines { lines -> myList.addAll(lines) }
    return myList
}

fun binaryArrayToInteger(list: List<Int>): Int{
    return list.joinToString("").toInt(2)
}

class GameBoard(gamma: List<Int>, epsilon: List<Int>){
    var gamma: List<Int> = gamma
    var epsilon: List<Int> = epsilon
    // calculate power consumption as the product of both integer values
    var powerConsumption: Int = binaryArrayToInteger(gamma) * binaryArrayToInteger(epsilon)
}

fun part1(input: List<String>){
    var codeLength = input[0].toCharArray().size
    var numberOfLines = input.size

    // start by counting the 1 bit in each position
    var oneDigitCount: MutableList<Int> = (Array(codeLength) { 0 }).toMutableList()
    for (code in input){
        var digits = code.toCharArray();
        for ((i, digit) in digits.withIndex()){
            if (digit.equals('1')){
                oneDigitCount[i] = oneDigitCount[i] + 1
            }
        }
    }

    // calculate gamma from counts of 1 bit
    var halfOfItems = numberOfLines/2
    var gammaList: List<Int> = oneDigitCount.map{count ->
        if (count >= (halfOfItems))  1 else 0
    }

    // calculate epsilon as opposite of gamma
    var epsilonList: List<Int> = gammaList.map{digit ->
        if (digit === 1) 0 else 1
    }
}

fun part2(input: List<String>) {

}

fun main(){
    //var input: List<String> = readFileToList("src/day3/testInput.txt")
    var input: List<String> = readFileToList("src/day3/myInput.txt")

    var part1Diagnostic = part1(input)
    println("Gamma: ${part1Diagnostic.gamma}")
    println("Epsilon: ${part1Diagnostic.epsilon}")
    println("Power consumption is: ${part1Diagnostic.powerConsumption}")

    //var part2Diagnostic = part2(input)
    //println("life support rating $part2Diagnostic")
}
