package human.est0y.chesslike.domain.chess;

import human.est0y.chesslike.domain.LetterHolder;

public enum PieceType implements LetterHolder {
    PAWN('p'),
    KNIGHT('n'),
    BISHOP('b'),
    QUEEN('q'),
    KING('k'),
    ROOK('r');

    private final char letter;

    PieceType(char letter) {
        this.letter = letter;
    }

    @Override
    public char getLetter() {
        return letter;
    }
}
