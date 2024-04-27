package human.est0y.chesslike.directions;

public class NotExistSquareException extends RuntimeException {
    public NotExistSquareException(int newX, int newY) {
        super(newX + "," + newY);
    }
}
