package human.est0y.chesslike.directions;

import human.est0y.chesslike.domain.chess.Square;
import human.est0y.chesslike.domain.squares.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SquareBoardNavigatorTest {

    private final SquareBoardNavigator navigator = new SquareBoardNavigator(Square::new);

    @Test
    void nextCell() {
        Assertions.assertEquals(new Square(4, 5), nextSquare(Direction.LEFT));
        Assertions.assertEquals(new Square(6, 5), nextSquare(Direction.RIGHT));
        Assertions.assertEquals(new Square(5, 6), nextSquare(Direction.UP));
        Assertions.assertEquals(new Square(5, 4), nextSquare(Direction.DOWN));
        Assertions.assertEquals(new Square(4, 6), nextSquare(Direction.LEFT_UP));
        Assertions.assertEquals(new Square(4, 4), nextSquare(Direction.LEFT_DOWN));
        Assertions.assertEquals(new Square(6, 6), nextSquare(Direction.RIGHT_UP));
        Assertions.assertEquals(new Square(6, 4), nextSquare(Direction.RIGHT_DOWN));
    }

    @Test
    void nextCellOutOfBoard() {
        Assertions.assertThrows(NotExistSquareException.class,
                () -> navigator.nextCell(new Square(5, 5), new Square(5, 5), Direction.RIGHT));
    }

    private Square nextSquare(Direction direction) {
        return navigator.nextCell(new Square(5, 5), new Square(8, 8), direction);
    }
}