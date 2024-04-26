package human.est0y.chesslike.squares;


import human.est0y.chesslike.domain.chess.Square;

public interface SquareCache {
    Square getSquare(int x, int y);
}
