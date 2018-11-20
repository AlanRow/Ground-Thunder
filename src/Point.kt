package pack


data class Point(val X: Int, val Y: Int){

    fun shift(dir: Move): Point{
        when (dir){
            Move.Down -> return Point(X, Y + 1)
            Move.Up -> return Point(X, Y - 1)
            Move.Left -> return Point(X - 1, Y)
            Move.Right -> return Point(X + 1, Y)
        }
    }
}