trait Piece {
    def handleAsOrigin(action: Action)

    def handleAsTarget(action: Action)
}