import java.util.*

fun main(args: Array<String> ) {
    var travel = Flights()
    travel.readFile("C:\\Users\\aconcunubo\\OneDrive - ENDAVA\\Documents\\TallerGrafo\\flights.txt")
    var r: Stack<Pair<Country,Long>> = travel.visitAllCountries("a")
    for(res in r){
        print(res.first.name+"\n")
    }
}