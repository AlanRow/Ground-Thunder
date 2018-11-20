package pack

import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class GameWindow () : Frame("GraT - Ground and Thunder") {

    fun start() {
        val terrains = arrayOf(arrayOf(Terrain.Grass, Terrain.Grass), arrayOf(Terrain.Electro, Terrain.Electro))
        val unit1 = Unit(0, 0, Element.Ground)
        val unit2 = Unit(1, 1, Element.Thunder)
        val units = arrayOf(arrayOf(unit1, null), arrayOf(null, unit2))
        val unitsQueue = Queue<Unit?>();
        unitsQueue.enqueue(unit1)
        unitsQueue.enqueue(unit2)
        val state = GameState(terrains, units, this,  unitsQueue)

        this.setSize(800, 600)
        val field = FieldCanvas(state)
        this.add(field)
        this.addKeyListener(KeyMover(state))

        this.setVisible(true)
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