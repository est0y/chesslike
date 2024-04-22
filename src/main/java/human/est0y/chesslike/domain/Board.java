package human.est0y.chesslike.domain;

import java.util.Optional;

@SuppressWarnings("all")
public interface Board<CELL, COLOR extends Enum<COLOR>, PIECE_TYPE extends Enum<PIECE_TYPE>, STATE, STRUCTURE> {

    Optional<Piece<COLOR, PIECE_TYPE, STATE>> getPiece(CELL cell);

    void putPiece(CELL cell, Piece<COLOR, PIECE_TYPE, STATE> piece);

    void deletePiece(CELL cell);

    STRUCTURE getDataStructure();

}
