package day.ten

import java.nio.file.Files
import java.nio.file.Paths

class CathodeRayTube {


    fun getSumOfNRuns(fileName: String, vararg circles: Int): Int {
        // get structure
        val commands = Files.readAllLines(Paths.get(fileName))
        val execMap = mutableMapOf<Int, Int>()
        val resultMap = mutableMapOf<Int, Int>()
        var stackValue = 1
        var circle = 1
        var commandIndex = 0
        var run = true
        var blocked = false

        while (run) {
            if (execMap.contains(circle)) {
                stackValue += execMap.getValue(circle)
                blocked = false
            }
            if (circles.contains(circle)) {
                resultMap[circle] = stackValue * (circle)
            }
            if (!blocked) {
                if (commands.size > commandIndex) {
                    val cmd = commands[commandIndex]
                    if (cmd.startsWith("addx")) {
                        val addx = cmd.split(" ")
                        execMap[circle + 2] = addx[1].toInt()
                    } else {
                        execMap[circle + 1] = 0
                    }
                    commandIndex++
                    blocked = true
                } else {
                    run = false
                }
            }
            circle++
        }
        return resultMap.values.sum()
    }


    fun getRenderedImage(fileName: String, vararg circles: Int): List<String> {
        // get structure
        val commands = Files.readAllLines(Paths.get(fileName))
        val execMap = mutableMapOf<Int, Int>()
        var stackValue = 1
        var circle = 1
        var commandIndex = 0
        var run = true
        var blocked = false
        var crt = ""
        var crtCount = 0
        val image = mutableListOf<String>()

        while (run) {
            if (execMap.contains(circle)) {
                stackValue += execMap.getValue(circle)
                blocked = false
            }
            crt += if (listOf(stackValue - 1, stackValue, stackValue + 1).contains(crtCount)) {
                "#"
            } else {
                "."
            }
            if (!blocked) {
                if (commands.size > commandIndex) {
                    val cmd = commands[commandIndex]
                    if (cmd.startsWith("addx")) {
                        val addx = cmd.split(" ")
                        execMap[circle + 2] = addx[1].toInt()
                    } else {
                        execMap[circle + 1] = 0
                    }
                    commandIndex++
                    blocked = true
                } else {
                    run = false
                }
            }
            if (circles.contains(circle)) {
                image.add(crt)
                crt = ""
                crtCount = 0
            }else{
                crtCount++
            }
            circle++
        }
        return image
    }
}