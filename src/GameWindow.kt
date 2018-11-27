package pack

import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class GameWindow () : Frame("GraT - Ground and Thunder") {

    fun start() {
        val terrains = arrayOf(arrayOf(Terrain.Grass, Terrain.Grass, Terrain.Electro, Terrain.Grass),
                arrayOf(Terrain.Electro, Terrain.Grass, Terrain.Grass, Terrain.Grass),
                arrayOf(Terrain.Grass, Terrain.Electro, Terrain.Electro, Terrain.Grass),
                arrayOf(Terrain.Electro, Terrain.Grass, Terrain.Grass, Terrain.Grass))

        val unit1 = Unit(0, 0, Element.Ground)
        val unit2 = Unit(0, 3, Element.Thunder)
        val units = arrayOf(arrayOf(unit1, null, null, null),
                    arrayOf<Unit?>(null, null, null, null),
                    arrayOf<Unit?>(null, null, null, null),
                    arrayOf(unit2, null, null, null))

        val unitsQueue = Queue<Unit?>();
        unitsQueue.enqueue(unit1)
        unitsQueue.enqueue(unit2)
        val state = GameState(terrains, units, this,  unitsQueue)

        setSize(800, 600)
        val field = FieldCanvas(state)
        add(field)
        addKeyListener(KeyMover(state))

        //pack()
        setVisible(true)
    }

}

class KeyMover(val state: GameState) : KeyListener {
    override fun keyTyped(ev: KeyEvent){
    }

    override fun keyPressed(ev: KeyEvent){

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