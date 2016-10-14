import Types.Direction
import model.Field

type Validator = (Piece, Field, Int) => Boolean
type Terminator = Validator

val horizontal = Vector((1, 0), (-1, 0))
val vertical = Vector((0, 1), (0, -1))
val diagonal = Vector((1, 1), (-1, -1), (1, -1), (-1, 1))
val allDirections = horizontal ++ vertical ++ diagonal

def king(currentPosition: Position) {
  val piece: Piece = new Piece {}
  val validator = notOwn
  val terminator = combine(Vector(maxStepTerminator(1), onPieceTerminator()))

  val postitions: Vector[Position] = allDirections.flatMap((dir) => {
    check(piece, currentPosition, Position.fromTuple(dir), validator, terminator)
  })
}

def check(piece: Piece, start: Position, direction: Direction, validator: Validator, terminator: Terminator, step: Int = 1): Vector[Position] = {
  val pos = start + direction
  //    val field = getField(pos)
  val field = Field(0, 1)

  val vector: Vector[Position] = if (terminator(piece, field, step)) {
    Vector.empty[Position]
  } else {
    check(piece, pos, direction, validator, terminator, step + 1)
  }

  return if (validator(piece, field, step)) {
    vector :+ new Position(field.x, field.y)
  } else {
    vector
  }
}

def maxStepTerminator(maxStep: Int): Terminator =
  (piece, field, step) => step == maxStep


def onPieceTerminator(): Terminator =
  (piece, field, step) => field.piece.isDefined

def notOwn(): Validator =
  (piece, field, step) => false // TODO check if color of piece is not equal

def combine(tors: Vector[Validator]): Validator = {
  (piece, field, step) => tors
    .map(v => v(piece, field, step))
    .reduce((a, b) => a || b)
}

