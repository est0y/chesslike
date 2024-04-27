package human.est0y.chesslike.directions;

import human.est0y.chesslike.domain.Board;

import java.util.List;

public interface EmptyCellDirection<CELL, DIRECTION> {

    List<CELL> getEmptyToOccupied(CELL cell, DIRECTION direction, Board<CELL, ?, ?, ?, ?> board);

    List<CELL> getEmptyToOccupied(CELL cell, DIRECTION direction, Board<CELL, ?, ?, ?, ?> board, int maxPath);
}
