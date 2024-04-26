package human.est0y.chesslike.fen;

import human.est0y.chesslike.domain.Board;
import human.est0y.chesslike.domain.Piece;
import human.est0y.chesslike.domain.chess.Color;
import human.est0y.chesslike.domain.chess.PieceType;
import human.est0y.chesslike.domain.chess.Square;
import human.est0y.chesslike.domain.chess.State;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class FenChessBoardConverterImpl implements FenChessBoardConverter {


    private final FenBoardConverter<Square,Color,PieceType,State,Map<Square,Piece<Color,PieceType,State>>>
            fenBoardConverter;

    @Override
    public String getFen(Board<Square, Color, PieceType, State, Map<Square, Piece<Color, PieceType, State>>> board) {
        return fenBoardConverter.getFen(board);
    }

    @Override
    public Board<Square, Color, PieceType, State, Map<Square, Piece<Color, PieceType, State>>> getBoard(String fen) {
        return fenBoardConverter.getBoard(fen);
    }
}
