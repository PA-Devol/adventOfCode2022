package day.nine

import java.nio.file.Files
import java.nio.file.Paths
import java.util.HexFormat
import kotlin.math.abs

class RopeBridge {

    fun countUniqueFieldOfTail(fileName: String, countKnots: Int = 1): Int {
        // get structure
        val commands = Files.readAllLines(Paths.get(fileName)).map { it.split(" ") }
        val ropeParts = mutableListOf<RopePart>(Head())
        for (i in 1..countKnots) {
            val tail = Tail(parent = ropeParts[ i -1])
            ropeParts.add(tail)
        }

        // exec command
        for (command in commands) {
            for (step in 1..command[1].toInt()) {
                ropeParts.forEach {  it.move(command[0]) }
                for (ropePart in ropeParts){
                    println(ropePart.getHistory().toString())
                }
            }
        }
        return ropeParts[ropeParts.size -1 ].getHistory().distinct().count()
    }


    interface RopePart {
        fun move(dir: String)
        fun getPos(): MutableList<Int>
        fun getHistory(): MutableList<MutableList<Int>>
    }

    class Head(
        private var pos: MutableList<Int> = mutableListOf(0, 0),
        private val history: MutableList<MutableList<Int>> = mutableListOf(mutableListOf(0, 0)),
    ) : RopePart {

        override fun move(dir: String) {
            pos = pos.toMutableList()
            when (dir) {
                "D" -> pos[1] = pos[1] - 1
                "L" -> pos[0] = pos[0] - 1
                "R" -> pos[0] = pos[0] + 1
                "U" -> pos[1] = pos[1] + 1
            }
            history.add(pos)
        }

        override fun getPos(): MutableList<Int> =
            this.pos

        override fun getHistory(): MutableList<MutableList<Int>> =
            mutableListOf()

    }

    class Tail(
        private val parent: RopePart,
        private var pos: MutableList<Int> = mutableListOf(0, 0),
        private val history: MutableList<MutableList<Int>> = mutableListOf(mutableListOf(0, 0)),
    ) : RopePart {

        override fun move(dir: String) {
            if (!isAdjacent()) {
                moveTail(dir)
            }
        }

        override fun getPos(): MutableList<Int> =
            this.pos

        override fun getHistory(): MutableList<MutableList<Int>> =
            this.history

        private fun moveTail(dir: String) {
            pos = pos.toMutableList()
            if (needJump()) {
                val parentPos = parent.getPos().toMutableList()
                when (dir) {
                    "D" -> {
                        pos[0] = parentPos[0]
                        pos[1] = parentPos[1] + 1
                    }
                    "L" -> {
                        pos[0] = parentPos[0] + 1
                        pos[1] = parentPos[1]
                    }
                    "R" -> {
                        pos[0] = parentPos[0] - 1
                        pos[1] = parentPos[1]
                    }
                    "U" -> {
                        pos[0] = parentPos[0]
                        pos[1] = parentPos[1] - 1
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
            history.add(pos)
        }

        private fun needJump(): Boolean {
            return (abs(pos[0] - parent.getPos()[0]) >= 2 && abs(pos[1] - parent.getPos()[1]) > 0)
                    || (abs(pos[0] - parent.getPos()[0]) > 0 && abs(pos[1] - parent.getPos()[1]) >= 2)
        }

        private fun isAdjacent(): Boolean {
            return abs(pos[0] - parent.getPos()[0]) <= 1 && abs(pos[1] - parent.getPos()[1]) <= 1
        }

    }
}