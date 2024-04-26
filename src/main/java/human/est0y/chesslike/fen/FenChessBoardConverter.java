package human.est0y.chesslike.fen;

import human.est0y.chesslike.domain.Piece;
import human.est0y.chesslike.domain.chess.Color;
import human.est0y.chesslike.domain.chess.PieceType;
import human.est0y.chesslike.domain.chess.Square;
import human.est0y.chesslike.domain.chess.State;

import java.util.Map;

public interface FenChessBoardConverter
        extends FenBoardConverter<Square, Color, PieceType, State, Map<Square, Piece<Color, PieceType, State>>> {


}
