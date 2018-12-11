package pack

import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener


//как добавить конструктор класса  (и можно ли?)?

class GameWindow () : Frame("GraT - Ground and Thunder") {

    fun start() {
        val terrains = getMapFromStrings("GGEG",
                                                    "EGGG",
                                                    "GEEG",
                                                    "EGGG")
        /*arrayOf(arrayOf(Terrain.Grass, Terrain.Grass, Terrain.Electro, Terrain.Grass),
                arrayOf(Terrain.Electro, Terrain.Grass, Terrain.Grass, Terrain.Grass),
                arrayOf(Terrain.Grass, Terrain.Electro, Terrain.Electro, Terrain.Grass),
                arrayOf(Terrain.Electro, Terrain.Grass, Terrain.Grass, Terrain.Grass))*/

        val unit1 = Unit(0, 0, Element.Ground)
        val unit2 = Unit(0, 3, Element.Thunder)
        val units = HashMap<Point, Unit>()
        units[unit1.Position] = unit1
        units[unit2.Position] = unit2

        val unitsQueue = Queue<Unit>();
        unitsQueue.enqueue(unit1)
        unitsQueue.enqueue(unit2)
        val state = GameState(terrains, units,  unitsQueue, this)

        setSize(800, 600)
        add(state.fieldCanvas)
        addKeyListener(KeyMover(state))

        //pack()
        setVisible(true)
    }

}

fun getMapFromStrings(vararg stringMap : String ) : Array2d<Terrain> {
    if (stringMap == null || stringMap.isEmpty() || stringMap[0] == null || stringMap[0].length == 0)
        throw Exception ("Uncorrect map format!");

    val map = Array2d<Terrain>(stringMap.size, stringMap[0].length, Terrain.Grass);

    for (str in 0 until map.Height){
        if (stringMap[str] == null || stringMap[str].length != map.Width)
            throw Exception ("Uncorrect map format!");
        for (c in 0 until map.Width){
            when (stringMap[str][c]){
                'G' -> map.set(c, str, Terrain.Grass);
                'E' -> map.set(c, str, Terrain.Electro);
                'T' -> map.set(c, str, Terrain.Top)
                else -> throw Exception ("Uncorrect map format!");
            }
        }
    }

    return map
}

class KeyMover(val state: GameState) : KeyListener {
    override fun keyTyped(ev: KeyEvent){
    }

    override fun keyPressed(ev: KeyEvent){

        println("I'm writing...")
        when (ev.keyCode) {
            KeyEvent.VK_W, KeyEvent.VK_UP -> state.tryMove(Move.Up)
            KeyEvent.VK_A, KeyEvent.VK_LEFT -> state.tryMove(Move.Left)
            KeyEvent.VK_D, KeyEvent.VK_RIGHT -> state.tryMove(Move.Right)
            KeyEvent.VK_S, KeyEvent.VK_DOWN -> state.tryMove(Move.Down)
            else -> println("I cannot catch anything...")
        }


    }

    override fun keyReleased(ev: KeyEvent){
    }
}