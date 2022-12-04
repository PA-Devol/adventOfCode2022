package day.three

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Paths

class RucksackReorganization {

    private val lowerAsciiA = "a".single().code
    private val upperAsciiA = "A".single().code


    fun getPriorityOfRucksacks(fileName: String): Int {
        val rucksacks = Files.readAllLines(Paths.get(fileName))
        var priority = 0
        for (rucksack in rucksacks) {
            priority += getPriorityOfRucksack(rucksack)
        }
        return priority
    }

    fun getPriorityOfRucksackGroups(fileName: String): Int {
        val rucksackGroups = Files.readAllLines(Paths.get(fileName))
            .withIndex()
            .groupBy { it.index / 3 }
            .map { it.value.map { v -> v.value } }
        var priority = 0
        for (rucksackGroup in rucksackGroups) {
            priority += getPriorityOfRucksackGroup(rucksackGroup)
        }
        return priority
    }

    private fun getPriorityOfRucksackGroup(rucksackGroup: List<String>): Int {
        val duplicatedChar = getDuplicatedCharStrings(rucksackGroup[0], rucksackGroup[1], rucksackGroup[2])
        return getPriorityValue(duplicatedChar)
    }


    private fun getPriorityOfRucksack(rucksack: String): Int {
        val duplicatedChar = getDuplicatedCharStrings(
            rucksack.substring(0, (rucksack.length / 2)),
            rucksack.substring((rucksack.length / 2), rucksack.length)
        )
        return getPriorityValue(duplicatedChar)
    }

    private fun getDuplicatedCharStrings(s1: String, s2: String): Char {
        for (char1 in s1) {
            for (char2 in s2) {
                if (char1 == char2) {
                    return char1
                }
            }
        }
        throw IllegalArgumentException("There is no duplicated char in the given compartments $s1 - $s2")
    }

    private fun getDuplicatedCharStrings(s1: String, s2: String, s3: String): Char {
        for (char1 in s1) {
            for (char2 in s2) {
                if (char1 == char2) {
                    for (char3 in s3) {
                        if (char1 == char3) {
                            return char1
                        }
                    }
                }
            }
        }
        throw IllegalArgumentException("There is no duplicated char in the given compartments $s1 - $s2 - $s3")
    }

    private fun getPriorityValue(char: Char): Int {
        return if (char.isLowerCase()) {
            char.code - lowerAsciiA + 1
        } else {
            char.code + 26 - upperAsciiA + 1
        }
    }


}