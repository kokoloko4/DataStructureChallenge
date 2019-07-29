import java.io.File
import java.util.*

class Flights constructor(){
    var countries = mutableListOf<Country>()
                get() = field

    fun addCity(nameCountry: String) {
        this.countries.add(Country(nameCountry, false, 0))
    }

    fun getCity(country: String): Country {
        if(!this.countries.filter{it.name.equals(country)}.isEmpty()){

            return this.countries.filter{it.name.equals(country)}.single()
        }
        return Country("",false)

    }

    fun addFlight(origin: String, destiny: String, time: Long){
        var startCity = getCity(origin)
        var endCity = getCity(destiny)
        startCity.addTravel(endCity, time)
    }

    fun resetAccumAllCountries() {
        this.countries.map { c -> c.accum = Long.MAX_VALUE}
    }

    fun isInResult(r : MutableList<Pair<Country,Long>>, c : Country): Int {
        if (r.isEmpty()){
            return -1
        }
        var index = 0
        var auxCity = r[index].first
        while (index < r.size && !auxCity.name.equals(c.name)) {
            auxCity = r[index].first
            index++
        }
        if(!auxCity.name.equals(c.name)) return -1
        return index
    }

    fun shortestPath (origin: String, destiny: String): MutableList<Pair<Country,Long>> {
        resetAccumAllCountries()
        var result = mutableListOf<Pair<Country, Long>>()
        var auxQueue = PriorityQueue<Country>(Comparator { o1, o2 -> o1.accum.toInt() - o2.accum.toInt() })
        var originCountry = getCity(origin)
        originCountry.accum = 0
        result.add(Pair(originCountry, 0L))
        auxQueue.add(originCountry)
        while (!auxQueue.isEmpty()) {
            var c = auxQueue.poll()
            var edges = c.edges
            if (!c.visited && !c.name.equals(destiny)) {
                for ((destiny, edge) in edges) {
                    if (!destiny.visited) {
                        if (c.accum + edge.weigth < destiny.accum) {
                            destiny.accum = c.accum + edge.weigth
                            var existInResult = isInResult(result, destiny)
                            if (existInResult != -1) {
                                var auxCity = result.get(existInResult - 1)
                                if (auxCity.second > destiny.accum) result[existInResult - 1] =
                                    Pair(destiny, destiny.accum)
                            } else {
                                result.add(Pair(destiny, destiny.accum))
                            }
                            auxQueue.add(destiny)
                        }
                    }
                }
            }
        }
        result.sortBy { pair -> pair.second }
        return result
    }

    fun getCities(){
        for (c in this.countries){
            println(c.name)
        }
    }

    fun readFile(file:String){
        var path = "src/"+file
        var endCountries = false
        File(path).forEachLine {
            if(it.equals("finPaises")){
                endCountries = true
            }
            if(endCountries && !it.equals("finPaises")) {
                var data = it.split(',')
                var origin = data[0]
                var destiny = data[1]
                var cost = data[2]
                this.addFlight(origin,destiny,cost.toLong())
            }else if (!endCountries){
                this.addCity(it)
            }
        }
    }
}