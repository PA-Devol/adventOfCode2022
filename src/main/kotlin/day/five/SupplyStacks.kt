package day.five

import java.nio.file.Files
import java.nio.file.Paths

class SupplyStacks {


    /**
     *
     * [N]             [R]             [C]
     * [T] [J]         [S] [J]         [N]
     * [B] [Z]     [H] [M] [Z]         [D]
     * [S] [P]     [G] [L] [H] [Z]     [T]
     * [Q] [D]     [F] [D] [V] [L] [S] [M]
     * [H] [F] [V] [J] [C] [W] [P] [W] [L]
     * [G] [S] [H] [Z] [Z] [T] [F] [V] [H]
     * [R] [H] [Z] [M] [T] [M] [T] [Q] [W]
     *  1   2   3   4   5   6   7   8   9
     */
    fun getUpperCrates(fileName: String, isCrateMover9001: Boolean): String {
        val supplyShipFile = Files.readAllLines(Paths.get(fileName))
        val supplyShip = SupplyShip(
            mutableListOf(
                mutableListOf("R", "G", "H", "Q", "S", "B", "T", "N"),
                mutableListOf("H", "S", "F", "D", "P", "Z", "J"),
                mutableListOf("Z", "H", "V"),
                mutableListOf("M", "Z", "J", "F", "G", "H"),
                mutableListOf("T", "Z", "C", "D", "L", "M", "S", "R"),
                mutableListOf("M", "T", "W", "V", "H", "Z", "J"),
                mutableListOf("T", "F", "P", "L", "Z"),
                mutableListOf("Q", "V", "W", "S"),
                mutableListOf("W", "H", "L", "M", "T", "D", "N", "C"),
            )
        )

        val moveCmds = supplyShipFile
            .filter { it.startsWith("move") }

            .map {
                Regex("[0-9]+").findAll(it)
                    .map(MatchResult::value)
                    .toList()
            }

        for (cmd in moveCmds) {
            supplyShip.move(cmd[0].toInt(), cmd[1].toInt(), cmd[2].toInt(), isCrateMover9001)
        }
        return supplyShip.getUpperCrates()

    }

    //WZMFVGGZP
    class SupplyShip(
        private var stacks: MutableList<MutableList<String>>
    ) {

        fun move(count: Int, from: Int, to: Int, isCrateMover9001: Boolean) {
            if (!isCrateMover9001) {
                stacks[to - 1].addAll(stacks[from - 1].takeLast(count).reversed())
            } else {
                stacks[to - 1].addAll(stacks[from - 1].takeLast(count))
            }
            stacks[from - 1] = stacks[from - 1].subList(0, stacks[from - 1].size - count)
        }

        fun getUpperCrates(): String {
            var s = ""
            for (stack in stacks) {
                s += stack.last()
            }
            return s
        }
    }
}