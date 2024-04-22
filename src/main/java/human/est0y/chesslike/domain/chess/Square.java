package human.est0y.chesslike.domain.chess;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Square {
    private final int x;

    private final int y;
}