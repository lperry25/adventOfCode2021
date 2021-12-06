package day3

import java.io.File
import FileReader.readFileToList

fun binaryArrayToInteger(list: List<Int>): Int{
    return list.joinToString("").toInt(2)
}

class Diagnostic(gamma: List<Int>, epsilon: List<Int>){
    var gamma: List<Int> = gamma
    var epsilon: List<Int> = epsilon
    // calculate power consumption as the product of both integer values
    var powerConsumption: Int = binaryArrayToInteger(gamma) * binaryArrayToInteger(epsilon)
}

fun part1(input: List<String>): Diagnostic{
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

    return Diagnostic(gammaList, epsilonList)
}

fun part2DiagnsoticCalculation(input: List<String>, defaultToOneOnEquality: Boolean, currentCode: List<Int>, isGamma: Boolean): List<Int>{
    var codeLength = currentCode.size
    var numberOfLines = input.size

    // start by counting the 1 bit in each position
    var oneDigitCount: MutableList<Int> = currentCode.toMutableList();
    oneDigitCount.add(codeLength, 0)
    for (code in input){
        var digits = code.toCharArray();
        if (digits[codeLength].equals('1')){
            oneDigitCount[codeLength] = oneDigitCount[codeLength] + 1
        }
    }

    // calculate gamma from counts of 1 bit
    var halfOfItems: Float = numberOfLines.toFloat()/2
    var countOfOnes = oneDigitCount[codeLength]
    var newGammaCode = if (countOfOnes >= (halfOfItems))  1 else 0
    if (isGamma){
        var gammaList: MutableList<Int> = currentCode.toMutableList()
        gammaList.add(codeLength, newGammaCode)
        return gammaList
    }
    // calculate epsilon as opposite of gamma... this became a mess and is very specifc to the use case
    var epsilonList: MutableList<Int> = currentCode.toMutableList()
    var newEpsilonValue = if (newGammaCode.equals(1)) 0 else 1
    epsilonList.add(codeLength, newEpsilonValue)
    return epsilonList

}

fun part2(input: List<String>): Int {
    // find the oxygen generator rating (this uses the most common digits and defaults to 1 on a tie)
    var oxygenGeneratorRating: List<String> = input;
    var initialMatching: List<Int> = emptyList<Int>();
    do{
        var currentGamma = part2DiagnsoticCalculation(oxygenGeneratorRating, true, initialMatching, true)
        oxygenGeneratorRating = oxygenGeneratorRating.filter { code -> code.startsWith(currentGamma.joinToString("")) }
        initialMatching = currentGamma
    } while (oxygenGeneratorRating.size > 1)

    val oxygenGeneratorFinal = oxygenGeneratorRating[0].toInt(2)
    println("oxygen generator $oxygenGeneratorFinal")

    // find the CO2 scrubber rating (this usess the least common digits and defaults to 0 on a tie)
    var co2Scrubber: List<String> = input;
    var initialScrubbberMatching: List<Int> = emptyList<Int>();
    do{
        var currentEpsilon = part2DiagnsoticCalculation(co2Scrubber, false, initialScrubbberMatching, false)
        co2Scrubber = co2Scrubber.filter { code -> code.startsWith(currentEpsilon.joinToString("")) }
        initialScrubbberMatching = currentEpsilon
    } while (co2Scrubber.size > 1)

    val co2ScrubberFinal = co2Scrubber[0].toInt(2)
    println("CO2 scrubber $co2ScrubberFinal")

    return co2ScrubberFinal * oxygenGeneratorFinal
}

fun main(){
    //var input: List<String> = readFileToList("src/day3/testInput.txt")
    var input: List<String> = readFileToList("src/day3/myInput.txt")

    var part1Diagnostic = part1(input)
    println("Gamma: ${part1Diagnostic.gamma}")
    println("Epsilon: ${part1Diagnostic.epsilon}")
    println("Power consumption is: ${part1Diagnostic.powerConsumption}")

    var part2Diagnostic = part2(input)
    println("life support rating $part2Diagnostic")
}
