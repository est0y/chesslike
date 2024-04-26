package human.est0y.chesslike.fen;

import human.est0y.chesslike.domain.Board;
import human.est0y.chesslike.domain.Piece;
import human.est0y.chesslike.domain.chess.Color;
import human.est0y.chesslike.domain.chess.PieceType;
import human.est0y.chesslike.domain.chess.Square;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleFenBoardConverterTest {

    @Mock
    private Board<Square, Color, PieceType, String, Map<Square, Piece<Color, PieceType, String>>> board;

    @Test
    void getFen() {
        var piece1 = new Piece<>(Color.WHITE, PieceType.KING, "");
        var piece2 = new Piece<>(Color.BLACK, PieceType.QUEEN, "");
        var piece3 = new Piece<>(Color.WHITE, PieceType.ROOK, "");
        when(board.getPiece(any())).thenReturn(Optional.empty());
        when(board.getPiece(new Square(1, 1))).thenReturn(Optional.of(piece1));
        when(board.getPiece(new Square(7, 3))).thenReturn(Optional.of(piece2));
        when(board.getPiece(new Square(3, 2))).thenReturn(Optional.of(piece3));
        when(board.getBoardSize()).thenReturn(new Square(7, 3));
        var converter = new SimpleFenBoardConverter<>(Square::new, PieceType.values(), (c, pt) -> c.name() + pt.name());
        String fen = converter.getFen(board);
        Assertions.assertEquals("6q/2R4/K6", fen);
    }

    @Test
    void getBoard() {
        var converter = new SimpleFenBoardConverter<>(Square::new, PieceType.values(), (c, pt) -> c.name() + pt.name());
        var board = converter.getBoard("6q/2R4/K6");
        var piece1 = board.getPiece(new Square(1, 1)).orElseThrow();
        Assertions.assertEquals(PieceType.KING, piece1.getPieceType());
        Assertions.assertEquals(Color.WHITE, piece1.getColor());
        Assertions.assertEquals("WHITEKING", piece1.getState());
        var piece2 = board.getPiece(new Square(3, 2)).orElseThrow();
        Assertions.assertEquals(PieceType.ROOK, piece2.getPieceType());
        Assertions.assertEquals(Color.WHITE, piece2.getColor());
        Assertions.assertEquals("WHITEROOK", piece2.getState());

    }
}
