package pack

data class Unit(var X: Int, var Y: Int, val Element: Element){

    val Position: Point
        get() = Point(X, Y)

    fun moveTo(point: Point){
        X = point.X
        Y = point.Y
    }
}