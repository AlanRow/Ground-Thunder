package pack


//как добавить к дженерику свойство
enum class Move {
    Up, Down, Right, Left;

    fun clockwise() : Move {
        when (this){
            Move.Up -> return Move.Right
            Move.Right -> return Move.Down
            Move.Down -> return Move.Left
            Move.Left -> return Move.Up
        }
    }

    fun counter() : Move{
        return this.clockwise().clockwise()
    }

    fun counterClock(): Move{
        return this.counter().clockwise()
    }
}