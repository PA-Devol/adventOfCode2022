package day.nine

import java.util.HexFormat
import kotlin.math.abs

class RopeBridge {

    fun countUniqueFieldOfTail(fileName: String) {

    }


    class Head(
        val pos: Array<Int> = Array(2) { 0 },
        val history: MutableList<Array<Int>> = mutableListOf()
    ) {

        fun move(dir: String) {
            history.add(pos)
            when (dir) {
                "D" -> pos[0] = pos[0] - 1
                "L" -> pos[1] = pos[1] - 1
                "R" -> pos[1] = pos[1] + 1
                "U" -> pos[0] = pos[0] + 1
            }
        }
    }

    class Tail(
        val head: Head = Head(),
        val pos: Array<Int> = Array(2) { 0 },
        val history: List<Array<Int>> = mutableListOf()
    ) {

        fun move(dir: String, steps: Int) {
            for (i in 1..steps) {
                head.move(dir)
                if (!isAdjacent()) {
                    moveTail()
                }
            }
        }

        private fun moveTail() {

        }

        private fun isAdjacent(): Boolean {
            return abs(pos[0] - head.pos[0]) <= 1 || abs(pos[1] - head.pos[1]) <= 1
        }

    }
}