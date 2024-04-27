package human.est0y.chesslike.directions;

import human.est0y.chesslike.domain.chess.Square;
import human.est0y.chesslike.domain.squares.Direction;
import human.est0y.chesslike.squares.SquareCache;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SquareBoardNavigator implements BoardNavigator<Square, Direction> {

    private final SquareCache squareCache;

    @Override
    public Square nextCell(Square startCell, Square boardSize, Direction direction) {
        return switch (direction) {
            case UNKNOWN -> throw new IllegalArgumentException("Direction is UNKNOWN");
            case UP -> nextSquare(startCell, boardSize, 0, 1);
            case DOWN -> nextSquare(startCell, boardSize, 0, -1);
            case LEFT -> nextSquare(startCell, boardSize, -1, 0);
            case RIGHT -> nextSquare(startCell, boardSize, 1, 0);
            case LEFT_UP -> nextSquare(startCell, boardSize, -1, 1);
            case LEFT_DOWN -> nextSquare(startCell, boardSize, -1, -1);
            case RIGHT_UP -> nextSquare(startCell, boardSize, 1, 1);
            case RIGHT_DOWN -> nextSquare(startCell, boardSize, 1, -1);
        };
    }

    private Square nextSquare(Square square, Square boardSize, int horizontalTerm, int verticalTerm) {
        var x = square.getX();
        var y = square.getY();
        var newX = x + horizontalTerm;
        var newY = y + verticalTerm;
        var maxX = boardSize.getX();
        var maxY = boardSize.getY();
        if (newX <= maxX && newY <= maxY && newX > 0 && newY > 0) {
            return squareCache.getSquare(newX, newY);
        } else {
            throw new NotExistSquareException(newX, newY);
        }
    }
}
