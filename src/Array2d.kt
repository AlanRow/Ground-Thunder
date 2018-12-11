package pack

class Array2d <T> (val Height: Int, val Width: Int, value : T){
    private val arr : Array<Any> = Array(Width * Height) {_ -> value as Any};


    fun get (x : Int, y: Int) : T{
        if (!containsPoint(x, y))
            throw Exception("Index was out from array! Array size is width: " + Width +"; height: " + Height + ". But point was x: " + x + "y: " + y);

        return arr[y * Width + x] as T;
    }

    fun get (p: Point) : T{
        return get(p.X, p.Y)
    }

    fun set (x : Int, y: Int, value : T){
        if (!containsPoint(x, y))
            throw Exception("Index was out from array! Array size is width: " + Width +"; height: " + Height + ". But point was x: " + x + "y: " + y);

        arr[y * Width + x] = value as Any;
    }

    fun set (p : Point, value : T){
        set(p.X, p.Y, value)
    }


    fun containsPoint(x : Int, y : Int) : Boolean{
        return y < Height && y >= 0 && x < Width && x >= 0
    }

    fun containsPoint(p: Point) : Boolean{
        return containsPoint(p.X, p.Y)
    }
}