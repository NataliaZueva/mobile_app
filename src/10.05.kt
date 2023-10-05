class Robot(var x: Int, var y: Int, var direction: Direction) {
    fun stepForward() {
        when (direction) {
            Direction.RIGHT -> x++
            Direction.LEFT -> x--
            Direction.UP -> y++
            Direction.DOWN -> y--
        }
    }

    fun turnRight() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.DOWN
            Direction.LEFT -> Direction.UP
            Direction.UP -> Direction.RIGHT
            Direction.DOWN -> Direction.LEFT
        }
    }

    fun turnLeft() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.UP
            Direction.LEFT -> Direction.DOWN
            Direction.UP -> Direction.LEFT
            Direction.DOWN -> Direction.RIGHT
        }
    }

    fun getY() {
        println("(Координата y: ${y})")
    }

    fun getX() {
        println("(Координата x: ${x})")
    }

    override fun toString(): String {
        return "(${x}, ${y}), looks ${direction}"
    }
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}


fun moveRobot(r: Robot, toX: Int, toY: Int) {
    val diffX = toX - r.x
    val diffY = toY - r.y

    if (r.x != toX || r.y != toY) {
        if (diffX > 0) {
            for (i in 1..diffX) {
                r.stepForward()
                println(r)
            }
        } else if (diffX < 0) {
            r.turnLeft()
            for (i in 1..-diffX) {
                r.stepForward()
                println(r)

            }
        }

        if (diffY > 0) {
            r.turnRight()
            for (i in 1..diffY) {
                r.stepForward()
                println(r)

            }
        } else if (diffY < 0) {
            r.turnLeft()
            for (i in 1..-diffY) {
                r.stepForward()
                println(r)
            }
        }
    }
    println("Вы дошли")
}


fun main() {
    val robot = Robot(1, 1, Direction.UP)
    moveRobot(robot, 0, 0)
}