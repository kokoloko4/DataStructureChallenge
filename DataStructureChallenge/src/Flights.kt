import java.util.*

class Flights constructor(){
    var countries = mutableListOf<Country>()
                get() = field

    fun addCity(nameCountry: String) {
        this.countries.add(Country(nameCountry, false, 0))
    }

    fun getCity(country: String): Country {
        return this.countries.filter{it.name == country}.single()

    }

    fun addFlight(origin: String, destiny: String, time: Long){
        var startCity = getCity(origin)
        var endCity = getCity(destiny)
        startCity.addTravel(endCity, time)
    }

    fun resetAccumAllCountries() {
        this.countries.map { c -> c.accum = Long.MAX_VALUE}
    }

    fun visitAllCountries(origin: String): Stack<Pair<Country,Long>> {
        resetAccumAllCountries()
        var result = Stack<Pair<Country,Long>>()
        var auxQueue = PriorityQueue<Country>(Comparator { o1, o2 -> o1.accum.toInt() - o2.accum.toInt() })
        var originCountry = getCity(origin)
        originCountry.accum = 0
        originCountry.visited = true
        result.push(Pair(originCountry, 0L))
        auxQueue.add(originCountry)
        while (!auxQueue.isEmpty()){
            var c = auxQueue.poll()
            var edges = c.edges
            if (!c.visited){
                for ((destiny, edge) in edges){
                    if(!destiny.visited) {
                        if (c.accum + edge.weigth < destiny.accum){
                            destiny.accum = c.accum + edge.weigth
                            result.push(Pair(destiny,destiny.accum))
                            auxQueue.add(destiny)
                        }
                    }
                }
            }else{
                c.visited = true
            }
        }
        return result
    }

    fun getCities(){
        for (c in this.countries){
            println(c.name)
        }
    }
}