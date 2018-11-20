package pack

import java.awt.*
import java.awt.event.*


class FieldCanvas : Canvas{

    val state: GameState

    constructor(state: GameState){
        this.state = state
        this.setSize(700, 500)
    }

    override fun paint(g: Graphics){
        println("I draw... ")

        val cellWidth = width / state.map.width
        val cellHeight = height / state.map.height

        for(j in 0 until state.map.height){
            for (i in 0 until state.map.width){
                when(state.map[j][i]) {
                    Terrain.Electro -> g.color = Color.WHITE
                    Terrain.Grass -> g.color = Color.GREEN
                }

                g.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight)

                if (state.unitMap[j][i] != null){
                    when(state.unitMap[j][i]!!.Element){
                        Element.Ground -> g.color = Color.orange
                        Element.Thunder -> g.color = Color.CYAN
                    }

                    g.fillOval(i * cellWidth + 10, j * cellHeight + 10, cellWidth - 20, cellHeight - 20)
                }
            }
        }
    }


}

