package day.four

import java.nio.file.Files
import java.nio.file.Paths

class CampCleanup {


    fun countFullyContainsOthers(filename: String): Int =
        getAssignmentsFromStrings(filename.let { Files.readAllLines(Paths.get(it)) })
            .let { it.count { assignmentPair -> assignmentPair.isFullyContainingOther() } }

    fun countPartiallyContainsOthers(filename: String): Int =
        getAssignmentsFromStrings(filename.let { Files.readAllLines(Paths.get(it)) })
            .let { it.count { assignmentPair -> assignmentPair.isPartiallyContainingOther() } }


    private fun getAssignmentsFromStrings(pairLines: List<String>): List<AssignmentPair> {
        val assignmentList = ArrayList<AssignmentPair>()
        for (pair in pairLines) {
            val splitPair = pair.split(",")
            val firstAssignment = splitPair[0].split("-")
            val secondAssignment = splitPair[1].split("-")
            assignmentList.add(
                AssignmentPair(
                    firstAssignment[0].toInt(),
                    firstAssignment[1].toInt(),
                    secondAssignment[0].toInt(),
                    secondAssignment[1].toInt()
                )
            )

        }
        return assignmentList
    }


    class AssignmentPair(
        private val startFirst: Int,
        private val endFirst: Int,
        private val startSecond: Int,
        private val endSecond: Int
    ) {

        fun isFullyContainingOther(): Boolean {
            if (startFirst >= startSecond && endFirst <= endSecond)
                return true
            else if (startSecond >= startFirst && endSecond <= endFirst)
                return true
            return false
        }

        fun isPartiallyContainingOther(): Boolean {
            if (startSecond in startFirst..endFirst)
                return true
            else if(startFirst in startSecond..endSecond)
                return true
            return false
        }
    }

}