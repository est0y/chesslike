package human.est0y.chesslike.directions;

import human.est0y.chesslike.domain.Board;
import human.est0y.chesslike.domain.Piece;
import human.est0y.chesslike.domain.chess.Square;
import human.est0y.chesslike.domain.squares.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptySquareDirectionTest {

    @Mock
    private BoardNavigator<Square, Direction> boardNavigator;

    @Mock
    private Board<Square, ?, ?, ?, ?> board;

    @Test
    void getEmptyToOccupied() {
        when(board.getBoardSize()).thenReturn(new Square(8,8));
        when(boardNavigator.nextCell(new Square(1, 1), new Square(8, 8), Direction.RIGHT))
                .thenReturn(new Square(2, 1));
        when(boardNavigator.nextCell(new Square(2, 1), new Square(8, 8), Direction.RIGHT))
                .thenReturn(new Square(3, 1));
        when(board.getPiece(any(Square.class))).thenReturn(Optional.empty());
        when(board.getPiece(new Square(3, 1))).thenReturn(Optional.of(new Piece<>(null,null,null)));
        var em = new EmptySquareDirection(boardNavigator);
        var squares = em.getEmptyToOccupied(new Square(1, 1), Direction.RIGHT, board);
        Assertions.assertEquals(squares.get(0),new Square(2,1));

    }

    @Test
    void testGetEmptyToOccupied() {
        when(board.getBoardSize()).thenReturn(new Square(8,8));
        when(boardNavigator.nextCell(new Square(1, 1), new Square(8, 8), Direction.RIGHT))
                .thenReturn(new Square(2, 1));
        when(boardNavigator.nextCell(new Square(2, 1), new Square(8, 8), Direction.RIGHT))
                .thenReturn(new Square(3, 1));
        when(board.getPiece(any(Square.class))).thenReturn(Optional.empty());
        when(board.getPiece(new Square(3, 1))).thenReturn(Optional.of(new Piece<>(null,null,null)));
        var em = new EmptySquareDirection(boardNavigator);
        var squares = em.getEmptyToOccupied(new Square(1, 1), Direction.RIGHT, board,2);
        Assertions.assertEquals(squares.get(0),new Square(2,1));
    }
}