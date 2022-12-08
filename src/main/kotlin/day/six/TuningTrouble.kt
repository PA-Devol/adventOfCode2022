package day.six

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Paths

class TuningTrouble {

    fun getEndOfStartOfPacketMaker(fileName: String, windowSize: Int): Int =
        getIndexOfEndingPacketMarker(fileName.let { Files.readAllLines(Paths.get(it)) }[0], windowSize)


    private fun getIndexOfEndingPacketMarker(msg: String, markerSize: Int): Int {
        for (i in msg.indices)
            if (i + markerSize < msg.length) {
                val set = msg.substring(i, i + markerSize).toCharArray().toSet()
                if (set.size == markerSize) {
                    return i + markerSize
                }
            }
        throw IllegalArgumentException("There are no $markerSize unique chars in in the messages $msg")
    }
}