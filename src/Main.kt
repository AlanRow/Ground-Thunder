package pack

fun main(args: Array<String>){

    //queueTest()

    //GameStateTests()

    val win = GameWindow()

    win.start();
}

/*fun mapFromStrings (width: Int, height:Int, strMap: Array<String>): Array<Array<Terrain>>{
    val map = mutableListOf<MutableList<Terrain>>()

    for (j in 0 until height){
        map.add(mutableListOf<Terrain>())
        for (i in 0 until width){
            when (strMap[j][i]) {
                'G' -> map[j].add(Terrain.Grass)
                'E' -> map[j].add(Terrain.Electro)
                else -> throw Exception("This is incorrect map!")
            }
        }
    }
}

fun unitsFromStrings (width: Int, height:Int, strMap: Array<String>): Array<Array<Unit?>{
    val map = mutableListOf<MutableList<Unit?>>()
    var number = 0

    for (j in 0 until height){
        map.add(mutableListOf<Unit?>())
        for (i in 0 until width){
            when (strMap[j][i]) {
                'G' -> map[j].add(Unit(i, j, Element.Ground))
                'T' -> map[j].add(Unit(i, j, Element.Thunder))
                '0' -> map[j].add(null)
                else -> throw Exception("This is incorrect map!")
            }
        }
    }
}*/