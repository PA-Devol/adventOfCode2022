package day.six

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Paths

class TuningTrouble {

    fun getEndOfStartOfPacketMaker(fileName: String, windowSize: Int): Int {
        val communicationInput = Files.readAllLines(Paths.get(fileName))

        return getIndexOfEndingPacketMarker(communicationInput[0], windowSize)
    }

    private fun getIndexOfEndingPacketMarker(msg: String, markerSize: Int): Int {
        for (i in msg.indices)
            if (i + markerSize < msg.length) {
                val set = hashSetOf<Char>()

                for (j in 0 until markerSize) {
                    set.add(msg[i + j])
                }
                if (set.size == markerSize) {
                    return i + markerSize
                }
            }
        throw IllegalArgumentException("There are no $markerSize unique chars in in the messages $msg")
    }
}