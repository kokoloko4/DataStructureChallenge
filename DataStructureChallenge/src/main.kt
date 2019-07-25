import java.util.*

fun main(args: Array<String> ) {
    var travel = Flights()
    travel.addCity("Bogotá")
    travel.addCity("Medellín")
    travel.addCity("Cali")
    travel.addFlight("Bogotá","Cali", 4L)
    travel.addFlight("Bogotá","Medellín", 2L)
    travel.addFlight("Medellín","Cali", 1L)
    var r: Stack<Pair<Country,Long>> = travel.visitAllCountries("Bogotá")
    println(r.peek().second)
}