import day.five.SupplyStacks
import day.four.CampCleanup
import day.one.CountElvesCalories
import day.seven.NoSpaceLeftOnDevice
import day.six.TuningTrouble
import day.three.RucksackReorganization
import day.two.RockPaperScissors

fun main() {
    // DAY 1
    println("******************** DAY 1 ********************")
    val topCalories =  CountElvesCalories().getMostCarriedCalories("src/main/kotlin/day/one/ElvesCalories")
    println("The strongest Elf is carrying $topCalories calories!")
    val top3Calories =  CountElvesCalories().getCaloriesOfNTopElves("src/main/kotlin/day/one/ElvesCalories" , 3)
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
    val countPartiallyContainingAssignments = CampCleanup().countPartiallyContainsOthers("src/main/kotlin/day/four/Input")
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
    val sizeOfDirToBeDeleted = NoSpaceLeftOnDevice().getDirSizeToFreeUpSpace("src/main/kotlin/day/seven/Input", 40000000)
    println("The size of the dir that has to be deleted to have enough space for an update is $sizeOfDirToBeDeleted.")
}