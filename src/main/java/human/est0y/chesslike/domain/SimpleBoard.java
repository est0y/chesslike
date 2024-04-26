package human.est0y.chesslike.domain;

import human.est0y.chesslike.domain.chess.Square;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@SuppressWarnings("all")
@RequiredArgsConstructor
public class SimpleBoard<CELL, COLOR extends Enum<COLOR>, PIECE_TYPE extends Enum<PIECE_TYPE>, STATE>
        implements Board<CELL, COLOR, PIECE_TYPE, STATE, Map<Square,Piece<COLOR,PIECE_TYPE,STATE>>> {

    private final Map<CELL, Piece<COLOR, PIECE_TYPE, STATE>> pieces;

    private final CELL boardSize;

    public Optional<Piece<COLOR, PIECE_TYPE, STATE>> getPiece(CELL cell) {
        return Optional.ofNullable(pieces.get(cell));
    }

    public void putPiece(CELL cell, Piece<COLOR, PIECE_TYPE, STATE> piece) {
        pieces.put(cell, piece);
    }

    public void deletePiece(CELL cell) {
        pieces.remove(cell);
    }

    @Override
    public CELL getBoardSize() {
        return boardSize;
    }

    @Override
    public Map getDataStructure() {
        return pieces;
    }
}
