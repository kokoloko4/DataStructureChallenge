class Country constructor(name: String, visited: Boolean, accum: Long = 9999) {
    var name: String = name
                get() = field
                set(value) {
                    field = value
                }
    var visited: Boolean = visited
                get() = field
                set(value) {
                    field = value
                }
    var edges = mutableMapOf<Country, Edge>()
                get() = field
    var accum: Long = accum
                get() = field
                set(value) {
                    field = value
                }

    fun addTravel(destiny: Country, weigth: Long) {
        var edge = Edge(weigth, false)
        this.edges.put(destiny, edge)
    }
}