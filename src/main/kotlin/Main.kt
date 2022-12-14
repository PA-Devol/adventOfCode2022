import day.eight.TreetopTreeHouse
import day.five.SupplyStacks
import day.four.CampCleanup
import day.nine.RopeBridge
import day.one.CountElvesCalories
import day.seven.NoSpaceLeftOnDevice
import day.six.TuningTrouble
import day.three.RucksackReorganization
import day.two.RockPaperScissors
import day.ten.CathodeRayTube

fun main() {
    // DAY 1
    println("******************** DAY 1 ********************")
    val topCalories = CountElvesCalories().getMostCarriedCalories("src/main/kotlin/day/one/ElvesCalories")
    println("The strongest Elf is carrying $topCalories calories!")
    val top3Calories = CountElvesCalories().getCaloriesOfNTopElves("src/main/kotlin/day/one/ElvesCalories", 3)
    println("The strongest 3 Elves are carrying $top3Calories calories together!")

    // Day 2
    println("******************** DAY 2 ********************")
    val myScore = RockPaperScissors().calculateScore("src/main/kotlin/day/two/Input")
    val myScoreWithNewStrategy = RockPaperScissors().calculateScoreWithNewStrategy("src/main/kotlin/day/two/Input")
    println("I will score $myScore points with the elf's strategy.")
    println("I will score $myScoreWithNewStrategy points with the elf's new strategy.")

    // Day 3
    println("******************** DAY 3 ********************")
    val rucksacksPriority = RucksackReorganization().getPriorityOfRucksacks("src/main/kotlin/day/three/Input")
    println("The combined priority of all rucksacks is $rucksacksPriority.")
    val rucksackGroupsPriority = RucksackReorganization().getPriorityOfRucksackGroups("src/main/kotlin/day/three/Input")
    println("The combined priority of all rucksack-groups is $rucksackGroupsPriority.")

    // Day 4
    println("******************** DAY 4 ********************")
    val countFullyContainingAssignments = CampCleanup().countFullyContainsOthers("src/main/kotlin/day/four/Input")
    println("There are  $countFullyContainingAssignments pairs that fully containing each other.")
    val countPartiallyContainingAssignments =
        CampCleanup().countPartiallyContainsOthers("src/main/kotlin/day/four/Input")
    println("There are  $countPartiallyContainingAssignments pairs that partially containing each other.")

    // Day 5
    println("******************** DAY 5 ********************")
    val upperCrates = SupplyStacks().getUpperCrates("src/main/kotlin/day/five/Input", false)
    println("This are the upper crates $upperCrates.")
    val upperCratesCrateMover9001 = SupplyStacks().getUpperCrates("src/main/kotlin/day/five/Input", true)
    println("This are the upper crates $upperCratesCrateMover9001 with Crate-Mover-9001.")

    // Day 6
    println("******************** DAY 6 ********************")
    val indexOfEndingMarker = TuningTrouble().getEndOfStartOfPacketMaker("src/main/kotlin/day/six/Input", 4)
    println("The Packet-Marker ends with an index of $indexOfEndingMarker.")
    val message = TuningTrouble().getEndOfStartOfPacketMaker("src/main/kotlin/day/six/Input", 14)
    println("The Message-Marker ends with an  index of $message.")

    // Day 7
    println("******************** DAY 7 ********************")
    val sumOfDirs = NoSpaceLeftOnDevice().getSumOfAllDirsSmallerThan("src/main/kotlin/day/seven/Input", 100000)
    println("The sum of all dirs <= 100000 is $sumOfDirs.")
    val sizeOfDirToBeDeleted =
        NoSpaceLeftOnDevice().getDirSizeToFreeUpSpace("src/main/kotlin/day/seven/Input", 40000000)
    println("The size of the dir that has to be deleted to have enough space for an update is $sizeOfDirToBeDeleted.")

    // Day 8
    println("******************** DAY 8 ********************")
    val countVisibleTrees = TreetopTreeHouse().countVisibleTrees("src/main/kotlin/day/eight/Input")
    println("There are $countVisibleTrees trees visible.")
    val highestScenicScore = TreetopTreeHouse().getHighestScenicScore("src/main/kotlin/day/eight/Input")
    println("The highest scenic score is $highestScenicScore.")

    // Day 9
    println("******************** DAY 9 ********************")
    val countUniqueSteps = RopeBridge().countUniqueFieldOfTail("src/main/kotlin/day/nine/Input")
    println("There are $countUniqueSteps positions that the tail of the rope visit at least once.")
    val countUniqueSteps10 = RopeBridge().countUniqueFieldOfTail("src/main/kotlin/day/nine/Input", 9)
    println("There are $countUniqueSteps10 positions that the nine tails of the rope visit at least once.")

    // Day 10
    println("******************** DAY 10 ********************")
    val countNCircles = CathodeRayTube().getSumOfNRuns("src/main/kotlin/day/ten/Input", 20, 60, 100, 140, 180, 220)
    println("There sum of the  20th, 60th, 100th, 140th, 180th, and 220th cycles is $countNCircles.")
}