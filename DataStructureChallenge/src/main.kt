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
    var r: MutableList<Pair<Country,Long>> = travel.shortestPath(origin,destiny)
    for(res in r){
        print(res.first.name+"->"+ res.second+"\n")
    }
}