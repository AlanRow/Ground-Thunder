package pack

class Queue<T> {

    constructor() {
        head  = null
        tail = head
    }

    var head : QueueItem<T>?
    var tail : QueueItem<T>?

    fun enqueue (value: T){
        if (head == null) {
            head = QueueItem<T>(value, null)
            tail = head
        }
        else {
            tail!!.next = QueueItem<T>(value, null)
            tail = tail!!.next
        }
    }

    fun dequeue (): T{
        if (head == null)
            throw Exception("Queue is empty!!!")
        val result = head!!.value
        head = head!!.next
        return result
    }

    fun peek (): T{
        if (head == null)
            throw Exception("Queue is empty!!!")
        return head!!.value
    }

    fun isEmpty (): Boolean{
        return head == null
    }

    fun delete (rem: T){
        if (head == null)
            return

        var prev: QueueItem<T>? = null
        var cur = head

        if (cur!!.value == rem){
            head = cur.next
            return
        }

        prev = cur
        cur = cur.next

        while (cur != null){
            if (cur.value == rem){
                prev!!.next = cur.next
                return
            }

            prev = cur
            cur = cur.next
        }
    }
}

class QueueItem<T>{
    val value : T
    var next : QueueItem<T>?

    constructor(value: T, next: QueueItem<T>?){
        this.value = value
        this.next = next
    }
}