package pack

import java.util.*
import java.awt.*


class GameState(val map : Array2d<Terrain>,  val unitMap : MutableMap<Point, Unit>, val units : Queue<Unit>, window : Frame){
    val fieldCanvas : FieldCanvas
    var groundsCount = 0
    var thundersCount = 0
    val tops: MutableList<Point> = arrayListOf()
    var winner: Element = Element.None

    init {
        fieldCanvas = FieldCanvas(this, window)

        for (unitPoint in unitMap.entries){
            when (unitPoint.value.Element){
                Element.Thunder -> thundersCount++
                Element.Ground -> groundsCount ++
            }
        }

        for (j in 0 until map.Height)
            for (i in 0 until map.Width)
                if (map.get(i, j) == Terrain.Top)
                    tops.add(Point(i, j))

        fieldCanvas.revalidate()
        fieldCanvas.repaint()
    }

    val current:Unit
        get() = units.peek()


    fun tryMove(dir : Move) {

        if (!isMovableTo(dir))
            return

        when (current.Element) {
            Element.Ground -> moveGround(dir)
            Element.Thunder -> moveThunder(dir)
        }

        fieldCanvas.revalidate()
        fieldCanvas.repaint()
    }

    private fun turn(){

        if (thundersCount == 0)
            groundsWin()

        var thunderWon = true
        for (p in tops) {
            val topUnit = unitMap[p]
            if (topUnit == null || topUnit.Element != Element.Thunder)
                thunderWon = false
        }

        if (thunderWon)
            thundersWin()

        units.enqueue(units.dequeue())
    }

    private fun moveGround(dir: Move){

        val dist = current.Position.shift(dir)

        if (map.get(dist) == Terrain.Top)
            return

        val unit = unitMap[dist]
        if (unit != null){
            when (unit.Element) {
                Element.Ground -> return
                Element.Thunder -> removeUnit(dist)
            }
        }

        if (map.get(dist) == Terrain.Electro)
        {
            genElectro()
            map.set(dist, Terrain.Grass)
        }

        unitMap[dist] = current
        unitMap.remove(current.Position)
        current.moveTo(dist)

        turn()
    }

    private fun moveThunder(dir: Move) {
        val dist : Point? = findElectro(dir)
        if (dist == null)
            return

        unitMap[dist] = current
        unitMap.remove(current.Position)
        current.moveTo(dist)

        turn()
    }

    private fun genElectro() {
        val empty: Array<Point> = getEmptyForElectro()
        if (empty.size == 0)
            return

        val rand = Random().nextInt(empty.size)

        map.set(empty[rand], Terrain.Electro)
    }

    private fun findElectro(dir: Move): Point?{
        var start = current.Position
        var n:Int
            when (dir){
                Move.Up -> n = start.Y
                Move.Down -> n = map.Height - start.Y - 1
                Move.Left -> n = start.X
                Move.Right -> n = map.Width - start.X - 1
            }

        for (i in 1..n){
            start = start.shift(dir).shift(dir.counterClock())
            var step = start
            for (j in 0..i*2){
                if (map.containsPoint(step) && (map.get(step) == Terrain.Electro || map.get(step) == Terrain.Top) && !unitMap.containsKey(step))
                    return step

                    step = step.shift(dir.clockwise())
            }
        }

        return null
    }

    private fun removeUnit(pos: Point){
        val removing = unitMap[pos]
        unitMap.remove(pos)
        if (removing != null) {
            units.delete(removing)
        }
    }

    private fun groundsWin(){
        winner = Element.Ground
    }

    private fun thundersWin(){
        winner = Element.Thunder
    }

    private fun isMovableTo(dir: Move): Boolean{
        when (dir){
            Move.Down -> return current.Y < map.Height - 1
            Move.Up -> return current.Y > 0
            Move.Right -> return current.X < map.Width - 1
            Move.Left -> return current.X > 0
        }
    }

    private fun getEmptyForElectro(): Array<Point>{
        val emptyCells = mutableListOf<Point>()
        //потестить
        for (i in 0 until map.Width){
            for (j in 0 until map.Height){
                if (map.get(i, j) == Terrain.Grass && !unitMap.containsKey(Point(i, j)))
                    emptyCells.add(Point(i, j))
            }
        }

        return emptyCells.toTypedArray()
    }
}



