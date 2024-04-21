package human.est0y.chesslike.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;



@SuppressWarnings("all")
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Piece<COLOR extends Enum<COLOR>, PIECE_TYPE extends Enum<PIECE_TYPE>, STATE> {

    private final COLOR color;

    private final PIECE_TYPE pieceType;

    private final STATE state;

    public <T> T getState() {
        return (T) state;
    }

}
