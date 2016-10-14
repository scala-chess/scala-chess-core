
type Validator = (Field, Int) => Boolean
type Terminator = (Piece, Field, Int) => Boolean


def king(currentPosition: Position) = {
    val validator: Validator

    check(
}

def check(piece: Piece, start: Position, direction: Direction, validator: Validator, terminator: Terminator, step:Int = 0) Vector[Position]
    val pos = start + direction
    val field = getField(pos)
    val valid = validator field
    val terminate = 
    val vector = if (terminator field) {
        []
    } else {
        check(piece, pos, direction, validator, terminator, step + 1)
    }

    return if (validator field) {
        field :: vector
    } else {
        vector
    }
}

def maxStepTerminator(maxStep:Int): Terminator = {
    (piece, field, step) => step == maxStep
}

def onEnemy
