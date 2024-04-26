package human.est0y.chesslike.fen;

import human.est0y.chesslike.domain.Board;
import human.est0y.chesslike.domain.LetterHolder;

public interface FenBoardConverter<CELL,COLOR extends Enum<COLOR>,PIECE_TYPE extends Enum<PIECE_TYPE> & LetterHolder,
        STATE,STRUCTURE> {

    String getFen(Board<CELL, COLOR, PIECE_TYPE, STATE, STRUCTURE> board);

    Board<CELL, COLOR, PIECE_TYPE, STATE, STRUCTURE>
    getBoard(String fen);
}
