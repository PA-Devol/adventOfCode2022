package day.eight

import java.nio.file.Files
import java.nio.file.Paths

class TreetopTreeHouse {
    fun countVisibleTrees(fileName: String): Int {
        val treeLines = Files.readAllLines(Paths.get(fileName))
        return getTrees(treeLines).flatten().count { it!!.isVisible() }
    }

    fun getHighestScenicScore(fileName: String): Int {
        val treeLines = Files.readAllLines(Paths.get(fileName))
        return getTrees(treeLines).flatten().map { it!!.getScenicScore() }.max()
    }

    private fun getTrees(treeLines: List<String>): Array<Array<Tree?>> {
        val trees = Array<Array<Tree?>>(treeLines.size) { emptyArray() }
        for (i in treeLines.indices) {
            trees[i] = Array(treeLines[i].length) { null }
            for (j in treeLines[i].indices) {
                val tree = Tree(treeLines[i][j].digitToInt())

                // up
                if (treeLines.indices.contains(i - 1)) {
                    tree.up = trees[i - 1][j]
                    trees[i - 1][j]?.down = tree
                }

                // left
                if (treeLines.indices.contains(j - 1)) {
                    tree.left = trees[i][j - 1]
                    trees[i][j - 1]?.right = tree
                }
                trees[i][j] = tree
            }
        }
        return trees
    }

    class Tree(
        var height: Int,
        var up: Tree? = null,
        var down: Tree? = null,
        var right: Tree? = null,
        var left: Tree? = null
    ) {

        fun isVisible(): Boolean =
            left?.isVisibleLeft(height) ?: true || up?.isVisibleUp(height) ?: true || down?.isVisibleDown(height) ?: true || right?.isVisibleRight(
                height
            ) ?: true

        fun getScenicScore(): Int =
            (left?.getScenicScoreLeft(0, height) ?: 0) * (right?.getScenicScoreRight(0, height)
                ?: 0) * (up?.getScenicScoreUp(0, height)
                ?: 0) * (down?.getScenicScoreDown(0, height) ?: 0)


        private fun getScenicScoreLeft(score: Int, height: Int): Int =
            if (height > this.height) {
                left?.getScenicScoreLeft(score + 1, height) ?: (score + 1)
            } else {
                score + 1
            }

        private fun getScenicScoreRight(score: Int, height: Int): Int =
            if (height > this.height) {
                right?.getScenicScoreRight(score + 1, height) ?: (score + 1)
            } else {
                score + 1
            }

        private fun getScenicScoreUp(score: Int, height: Int): Int =
            if (height > this.height) {
                up?.getScenicScoreUp(score + 1, height) ?: (score + 1)
            } else {
                score + 1
            }

        private fun getScenicScoreDown(score: Int, height: Int): Int =
            if (height > this.height) {
                down?.getScenicScoreDown(score + 1, height) ?: (score + 1)
            } else {
                score + 1
            }


        private fun isVisibleRight(height: Int): Boolean {
            return if (height > this.height) {
                right?.isVisibleRight(height) ?: true
            } else {
                false
            }
        }

        private fun isVisibleLeft(height: Int): Boolean {
            return if (height > this.height) {
                left?.isVisibleLeft(height) ?: true
            } else {
                false
            }
        }

        private fun isVisibleUp(height: Int): Boolean {
            return if (height > this.height) {
                up?.isVisibleUp(height) ?: true
            } else {
                false
            }
        }

        private fun isVisibleDown(height: Int): Boolean {
            return if (height > this.height) {
                down?.isVisibleDown(height) ?: true
            } else {
                false
            }
        }
    }
}