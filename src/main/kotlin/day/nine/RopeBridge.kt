package day.nine

import java.nio.file.Files
import java.nio.file.Paths
import java.util.HexFormat
import kotlin.math.abs

class RopeBridge {

    fun countUniqueFieldOfTail(fileName: String): Int {
        val commands = Files.readAllLines(Paths.get(fileName)).map { it.split(" ") }
        val tail = Tail()
        execCommands(tail, commands)
        return tail.history.distinct().count()
    }


    private fun execCommands(tail: Tail, commands: List<List<String>>) {
        for (cmd in commands) {
            tail.move(cmd[0], cmd[1].toInt())
        }
        tail.history.add(tail.pos)
    }

    class Head(
        var pos: MutableList<Int> = mutableListOf(0, 0),
        val history: MutableList<MutableList<Int>> = mutableListOf()
    ) {

        fun move(dir: String) {
            history.add(pos)
            pos = pos.toMutableList()
            when (dir) {
                "D" -> pos[1] = pos[1] - 1
                "L" -> pos[0] = pos[0] - 1
                "R" -> pos[0] = pos[0] + 1
                "U" -> pos[1] = pos[1] + 1
            }
        }
    }

    class Tail(
        val head: Head = Head(),
        var pos: MutableList<Int> = mutableListOf(0, 0),
        val history: MutableList<MutableList<Int>> = mutableListOf()
    ) {

        fun move(dir: String, steps: Int) {
            for (i in 1..steps) {
                head.move(dir)
                if (!isAdjacent()) {
                    moveTail(dir)
                }
            }
        }

        private fun moveTail(dir: String) {
            history.add(pos)
            pos = pos.toMutableList()
            if (needJump()) {
                val headPos = head.pos.toMutableList()
                when (dir) {
                    "D" -> {
                        pos[0] = headPos[0]
                        pos[1] = headPos[1] + 1
                    }
                    "L" -> {
                        pos[0] = headPos[0] + 1
                        pos[1] = headPos[1]
                    }
                    "R" -> {
                        pos[0] = headPos[0] - 1
                        pos[1] = headPos[1]
                    }
                    "U" -> {
                        pos[0] = headPos[0]
                        pos[1] = headPos[1] - 1
                    }
                }
            } else {
                when (dir) {
                    "D" -> pos[1] = pos[1] - 1
                    "L" -> pos[0] = pos[0] - 1
                    "R" -> pos[0] = pos[0] + 1
                    "U" -> pos[1] = pos[1] + 1
                }
            }
        }

        private fun needJump(): Boolean {
            return (abs(pos[0] - head.pos[0]) >= 2 && abs(pos[1] - head.pos[1]) > 0)
                    || (abs(pos[0] - head.pos[0]) > 0 && abs(pos[1] - head.pos[1]) >= 2)
        }

        private fun isAdjacent(): Boolean {
            return abs(pos[0] - head.pos[0]) <= 1 && abs(pos[1] - head.pos[1]) <= 1
        }

    }
}