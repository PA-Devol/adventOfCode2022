import day.four.CampCleanup
import day.one.CountElvesCalories
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
}