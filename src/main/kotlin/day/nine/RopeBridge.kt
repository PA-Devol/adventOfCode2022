package day.nine

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs

class RopeBridge {

    fun countUniqueFieldOfTail(fileName: String, tailSize: Int = 1): Int {
        // get structure
        val commands = Files.readAllLines(Paths.get(fileName)).map { it.split(" ") }
        val ropeParts = mutableListOf(RopePart(isHead = true))
        for (i in 1..tailSize) {
            val ropePart = RopePart(parent = ropeParts[i - 1])
            ropeParts.add(ropePart)
        }

        // exec command
        for (command in commands) {
            for (step in 1..command[1].toInt()) {
                ropeParts.forEach { it.move(command[0]) }
            }
        }
        return ropeParts.last().history.toSet().count()
    }


    class RopePart(
        val isHead: Boolean = false,
        val parent: RopePart? = null,
        var pos: MutableList<Int> = mutableListOf(0, 0),
        val history: MutableList<MutableList<Int>> = mutableListOf(mutableListOf(0, 0)),
    ) {

        fun move(dir: String) {
            if (isHead) {
                moveHead(dir)
            } else if (!isAdjacent()) {
                moveTail()
            }
        }

        private fun moveHead(dir: String) {
            pos = pos.toMutableList()
            when (dir) {
                "D" -> pos[1] = pos[1] - 1
                "L" -> pos[0] = pos[0] - 1
                "R" -> pos[0] = pos[0] + 1
                "U" -> pos[1] = pos[1] + 1
            }
            history.add(pos)
        }

        private fun moveTail() {
            pos = pos.toMutableList()
            val currentParentPos = parent!!.pos.toMutableList()
            val deltaX = currentParentPos[0] - pos[0]
            val deltaY = currentParentPos[1] - pos[1]
            if (abs(deltaX) > 1 && abs(deltaY) > 1) {
                pos[0] = pos[0] + deltaX / 2
                pos[1] = pos[1] + deltaY / 2
            } else if (abs(deltaX) > 1) {
                pos[0] = pos[0] + deltaX / 2
                pos[1] = pos[1] + deltaY
            } else {
                pos[0] = pos[0] + deltaX
                pos[1] = pos[1] + deltaY / 2
            }
            history.add(pos)
        }

        private fun isAdjacent(): Boolean {
            return abs(pos[0] - parent!!.pos[0]) <= 1 && abs(pos[1] - parent.pos[1]) <= 1
        }

    }
}