package human.est0y.chesslike.directions;

public interface BoardNavigator<CELL, DIRECTION extends Enum<DIRECTION>> {
    CELL nextCell(CELL startCell, CELL boardSize, DIRECTION direction);
}
