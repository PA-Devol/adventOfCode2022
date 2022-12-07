package day.seven

import java.nio.file.Files
import java.nio.file.Paths

class NoSpaceLeftOnDevice {
    fun getSumOfAllDirsSmallerThan(fileName: String, size: Long): Long =
        getDir(Files.readAllLines(Paths.get(fileName))).getSumOfDirsSmallerThan(size)

    fun getSumOfDirToBeDeleted(fileName: String, maxUsed: Long): Long {
        val dir = getDir(Files.readAllLines(Paths.get(fileName)))
        return dir.getDirSizesBiggerThan(dir.getSize() - maxUsed, mutableListOf())
            .minOrNull()!!
    }


    private fun getDir(input: List<String>): Directory {
        var dir = Directory("root", null, mutableListOf())
        for (line in input) {
            if (isCdCmd(line)) {
                val cdDirName = line.split("cd")[1].trim()
                dir = if (cdDirName == "..") {
                    dir.upperDir!!
                } else {
                    val newDir = Directory(cdDirName, dir)
                    dir.subDirectories.add(newDir)
                    newDir
                }
            } else if (isNoCmd(line)) {
                if (line.isNotEmpty() && line[0].isDigit()) {
                    val splitFileLine = line.split(" ")
                    dir.files.add(File(splitFileLine[1], splitFileLine[0].toLong()))
                }
            }
        }
        return dir.getRoot()
    }

    private fun isNoCmd(line: String): Boolean =
        !line.startsWith("$")

    private fun isCdCmd(line: String): Boolean =
        line.startsWith("$ cd")


    class Directory(
        val name: String,
        val upperDir: Directory?,
        val files: MutableList<File> = ArrayList(),
        val subDirectories: MutableList<Directory> = ArrayList()
    ) {

        fun getSize(): Long =
            files.sumOf { it.size } + subDirectories.sumOf { it.getSize() }

        fun getRoot(): Directory =
            upperDir?.getRoot() ?: this

        fun getSumOfDirsSmallerThan(size: Long): Long =
            if (getSize() <= size) {
                getSize() + subDirectories.sumOf { it.getSumOfDirsSmallerThan(size) }
            } else {
                subDirectories.sumOf { it.getSumOfDirsSmallerThan(size) }
            }

        fun getDirSizesBiggerThan(size: Long, mutableList: MutableList<Long>): MutableList<Long> {
            if (getSize() >= size) {
                mutableList.add(getSize())
            }
            subDirectories.map { it.getDirSizesBiggerThan(size, mutableList) }
            return mutableList
        }

    }

    class File(
        val name: String,
        val size: Long
    )
}

