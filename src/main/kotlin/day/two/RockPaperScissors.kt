package day.two

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Paths

class RockPaperScissors {
    private val matrix = arrayOf(arrayOf(3, 0, 6), arrayOf(6, 3, 0), arrayOf(0, 6, 3))

    //          Rock     Paper   Scissor
    // Rock     3          0        6
    // Paper    6          3        0
    // Scissor  0          6        3
    // ^ me


    fun calculateScore(fileName: String): Int {
        val gameList = Files.readAllLines(Paths.get(fileName))
        var myFinalScore = 0
        for (game in gameList) {
            val splitGame = game.trim().split(" ")
            isValidGame(splitGame)
            val myInput = Input.valueOf(splitGame[1]).index
            val opponentInput = Input.valueOf(splitGame[0]).index

            myFinalScore += myInput + 1 + matrix[myInput][opponentInput] // add + 1 because to the array offset
        }
        return myFinalScore
    }


    fun calculateScoreWithNewStrategy(fileName: String): Int {
        val gameList = Files.readAllLines(Paths.get(fileName))
        var myFinalScore = 0
        for (game in gameList) {
            val splitGame = game.trim().split(" ")
            isValidGame(splitGame)
            val myResult = Result.valueOf(splitGame[1])
            val opponentInput = Input.valueOf(splitGame[0])

            myFinalScore += matrix.filter { it[opponentInput.index] == myResult.score }
                .map { matrix.indexOf(it) }.first() + myResult.score + 1
        }
        return myFinalScore
    }


    private fun isValidGame(splitGame: List<String>) {
        if (splitGame.size != 2) {
            throw IllegalArgumentException("There must be exactly 2 chars for a game input, but there are ${splitGame.size}")
        }
    }


    enum class Result(val score: Int) {
        X(0),
        Y(3),
        Z(6)
    }

    enum class Input(val index: Int) {
        A(0),
        B(1),
        C(2),
        X(0),
        Y(1),
        Z(2)
    }
}