fun main(args: Array<String> ) {
    var f1 = Flights()
    var bog = City("Bogotá", false)
    var cal = City("Cali", false)
    bog.addTravel(cal, 1)
    println(bog.edges.getValue(cal).weigth)
}