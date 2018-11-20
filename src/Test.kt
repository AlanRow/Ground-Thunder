package pack

fun GameStateTests() {
    println("Game State Tests:")
    //tryMoveTest()
}

/*fun tryMoveTest(){
    val terrains = arrayOf(arrayOf(Terrain.Grass, Terrain.Grass), arrayOf(Terrain.Electro, Terrain.Electro))
    val unit1 = Unit(0, 0, Element.Ground)
    val unit2 = Unit(1, 1, Element.Thunder)
    val units = arrayOf(arrayOf(unit1, null), arrayOf(null, unit2))
    val unitsQueue = Queue<Unit?>();
    unitsQueue.enqueue(unit1)
    unitsQueue.enqueue(unit2)

    val state = GameState(terrains, units, unitsQueue)

    state.tryMove(Move.Right)

    if (unit1.X != 1 || unit1.Y != 0)
        println("Unit position is X: " + unit1.X + "; " + "Y: " + unit1.Y + ", when was expected (1, 0)!!!")

    if (units[0][0] != null)
        println("Somebody is on cell (0, 0), but nobody must be there!!!")

    if (units[0][1] != unit1)
        println("The unit 1 doesn't be on cell (1, 0), where he must to be!!!")


    state.tryMove(Move.Left)

    if (unit2.X != 0 || unit2.Y != 1)
        println("Unit position is X: " + unit1.X + "; " + "Y: " + unit1.Y + ", when was expected (0, 1)!!!")

    if (units[1][1] != null)
        println("Somebody is on cell (1, 1), but nobody must be there!!!")

    if (units[1][0] != unit2)
        println("The unit 2 doesn't be on cell (0, 1), where he must to be!!!")
}*/


fun queueTest() {
    println("Queue Tests:")

    initQueueTest()
    enqueueTest()
    dequeueTest()
    isEmptyTest()
}

fun initQueueTest(){
    val queue = Queue<Int>()

    if (queue.head != null)
        println("head not equals null!!!")

    if (queue.tail != null)
        println("tail not equals null!!!")
}

fun enqueueTest(){
    val queue = Queue<Int>()
    queue.enqueue(2)

    if (queue.peek() != 2)
        println("head is " + queue.peek() + " but was expected 2!!!")

}

fun dequeueTest() {

    val queue = Queue<Int>()
    queue.enqueue(2)
    queue.enqueue(5)
    queue.enqueue(-1768)


    if (queue.dequeue() != 2)
        println("taken from queue is" + queue.peek() + "but was expected 2!!!")

    if (queue.peek() != 5)
        println("head is" + queue.peek() + "but was expected 5!!!")


    if (queue.dequeue() != 5)
        println("taken from queue is" + queue.peek() + "but was expected 5!!!")
}

fun isEmptyTest(){
    val queue = Queue<Int>()

    if (!queue.isEmpty())
        println("The created queue is not empty!!!")

    queue.enqueue(2)
    queue.dequeue()

    if (!queue.isEmpty())
        println("The empty queue is not empty!!!")

}
