import java.util.*

fun main(args: Array<String> ) {
    var travel = Flights()
    println("File name : ")
    val fileName = readLine()!!
    travel.readFile(fileName)
    println("Origin : ")
    val origin = readLine()!!
    println("Destiny : ")
    val destiny = readLine()!!
    var r: MutableList<Pair<Pair<Country,Country>,Long>> = travel.shortestPath(origin,destiny)
    travel.showPath(origin,destiny,r)
}