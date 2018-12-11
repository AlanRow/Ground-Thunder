package pack

import java.awt.*
import java.awt.event.*

class FieldCanvas : Canvas{

    val state: GameState
    val container :Frame

    constructor(state: GameState, cont :Frame){
        this.state = state
        this.setSize(700, 500)
        container = cont
    }

    override fun paint(g: Graphics){
        val cellWidth = width / state.map.Width
        val cellHeight = height / state.map.Height

        for(j in 0 until state.map.Height){
            for (i in 0 until state.map.Width){
                when(state.map.get(i, j)) {
                    Terrain.Electro -> g.color = Color.WHITE
                    Terrain.Grass -> g.color = Color.GREEN
                }

                g.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight)

                var unit = state.unitMap[Point(i, j)]
                if (unit != null){
                    when(unit.Element){
                        Element.Ground -> g.color = Color.orange
                        Element.Thunder -> g.color = Color.CYAN
                    }

                    g.fillOval(i * cellWidth + 10, j * cellHeight + 10, cellWidth - 20, cellHeight - 20)

                    if (unit == state.current){
                        val g2 =  g as Graphics2D

                        g2.color = Color.RED
                        g2.setStroke ( BasicStroke(5.0f) )
                        g2.drawRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight)
                    }
                }
            }
        }

        /*if (state.winner != Element.None) {
            val winText = Label()
            winText.setLocation(width / 2 - 100, height / 2 - 125)
            winText.setSize(400, 240)

            when (state.winner) {
                Element.Ground -> winText.text = "Grounds win!"
                Element.Thunder -> winText.text = "Thunderss win!"
            }
            container.add(winText)
        }*/
    }


}

