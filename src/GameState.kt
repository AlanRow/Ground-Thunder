package pack

import java.util.*
import java.awt.*

//как писать тесты в Kotlin?
//как работать с дженериками
//как определяется equals у data-классов
//можно ли создать val name и var name

//как прицепить свойство к generic-типу (и можно ли вообще?)
val Array<Array<Terrain>>.width:Int
    get() = this.size

val Array<Array<Terrain>>.height:Int
    get() = this[0].size

fun Array<Array<Terrain>>.get(point: Point): Terrain{
    return this[point.Y][point.X]
}

fun Array<Array<Terrain>>.isInternal(point: Point): Boolean{
    return point.X >= 0 && point.Y >= 0 && point.X < this.width && point.Y < this.height
}


val Array<Array<Unit?>>.width:Int
    get() = this.size

val Array<Array<Unit?>>.height:Int
    get() = this[0].size

fun Array<Array<Unit?>>.get(point: Point): Unit?{
    return this[point.Y][point.X]
}


class GameState(val map : Array<Array<Terrain>>, val unitMap : Array<Array<Unit?>>, val win: Frame, val units : Queue<Unit?>){
    val current:Unit?
        get() = units.peek()


    fun tryMove(dir : Move) {

        if (!isMovableTo(dir))
            return

        when (current!!.Element) {
            Element.Ground -> moveGround(dir)
            Element.Thunder -> moveThunder(dir)
        }

        println("Then must be drawing...")
        win.revalidate()
        win.repaint()
    }

    private fun turn(){
        for (j in 0 until map.height) {
            for (i in 0 until map.width) {
                val cell = if (unitMap[j][i] == null) '0' else if (unitMap[j][i]!!.Element == Element.Thunder) 'T' else 'G'
                print(cell)
            }
            println()
        }
        for (i in 0 until map.width){
            print("-")
        }
        println()
        units.enqueue(units.dequeue())
    }

    private fun moveGround(dir: Move){

        val dist = current!!.Position.shift(dir)

        if (unitMap[dist.Y][dist.X] != null) {
            when (unitMap[dist.Y][dist.X]!!.Element) {
                    Element.Ground -> return
                    Element.Thunder -> units.delete(unitMap[dist.Y][dist.X])
                }
        }

        if (map[dist.Y][dist.X] == Terrain.Electro)
        {
            genElectro()
            map[dist.Y][dist.X] = Terrain.Grass
        }

        unitMap[dist.Y][dist.X] = unitMap[current!!.Y][current!!.X]
        unitMap[current!!.Y][current!!.X] = null
        current!!.moveTo(dist)

        turn()
    }

    private fun moveThunder(dir: Move) {
        val dist : Point? = findElectro(dir)
        if (dist == null)
            return

        unitMap[dist.Y][dist.X] = unitMap[current!!.Y][current!!.X]
        unitMap[current!!.Y][current!!.X] = null
        current!!.moveTo(dist)

        turn()
    }

    private fun genElectro() {
        val empty: Array<Point> = getEmptyForElectro()
        if (empty.size == 0)
            return

        val rand = Random().nextInt(empty.size)

        map[empty[rand].Y][empty[rand].X] = Terrain.Electro
    }

    private fun findElectro(dir: Move): Point?{
        var start = current!!.Position
        var n:Int
            when (dir){
                Move.Up -> n = start.Y
                Move.Down -> n = map.height - start.Y - 1
                Move.Left -> n = start.X
                Move.Right -> n = map.width - start.X - 1
            }

        for (i in 1..n){
            start = start.shift(dir).shift(dir.counterClock())
            var step = start
            for (j in 0..i*2){
                if (map.isInternal(step) && map.get(step) == Terrain.Electro && unitMap.get(step) == null) {
                    return step
                }
                    step = step.shift(dir.clockwise())
            }
        }

        return null
    }

    private fun isMovableTo(dir: Move): Boolean{
        when (dir){
            Move.Down -> return current!!.Y < map.height - 1
            Move.Up -> return current!!.Y > 0
            Move.Right -> return current!!.X < map.width - 1
            Move.Left -> return current!!.X > 0
        }
    }

    private fun getEmptyForElectro(): Array<Point>{
        val emptyCells = mutableListOf<Point>()
        //потестить
        for (i in 0 until map.width){
            for (j in 0 until map.height){
                if (map[j][i] == Terrain.Grass && unitMap[j][i] == null)
                    emptyCells.add(Point(i, j))
            }
        }

        return emptyCells.toTypedArray()
    }
}



