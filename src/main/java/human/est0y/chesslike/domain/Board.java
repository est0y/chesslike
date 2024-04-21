package human.est0y.chesslike.domain;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@SuppressWarnings("all")
@RequiredArgsConstructor
public class Board<CELL, COLOR extends Enum<COLOR>, PIECE_TYPE extends Enum<PIECE_TYPE>, STATE> {
    private final Map<CELL, Piece<COLOR, PIECE_TYPE, STATE>> pieces;

    public Piece<COLOR, PIECE_TYPE, STATE> getPiece(CELL cell) {
        return pieces.get(cell);
    }

    public void putPiece(CELL cell, Piece<COLOR, PIECE_TYPE, STATE> piece) {
        pieces.put(cell,piece);
    }

    public void deletePiece(CELL cell){
        pieces.remove(cell);
    }
}
