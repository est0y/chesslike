package human.est0y.chesslike.directions;

import human.est0y.chesslike.domain.Board;
import human.est0y.chesslike.domain.chess.Square;
import human.est0y.chesslike.domain.squares.Direction;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EmptySquareDirection implements EmptyCellDirection<Square, Direction> {

    private final BoardNavigator<Square, Direction> boardNavigator;

    @Override
    public List<Square> getEmptyToOccupied(Square square, Direction direction, Board<Square, ?, ?, ?, ?> board) {
        List<Square> squares = new ArrayList<>();
        Square nowSquare = getNextEmptySquare(square, direction, board);
        while (nowSquare != null) {
            squares.add(nowSquare);
            nowSquare = getNextEmptySquare(nowSquare, direction, board);
        }
        return squares;
    }

    @Override
    public List<Square> getEmptyToOccupied(Square square, Direction direction, Board<Square, ?, ?, ?, ?> board,
                                           int maxPath) {
        List<Square> squares = new ArrayList<>();
        for (int i = 1; i <= maxPath; i++) {
            Square nowSquare = getNextEmptySquare(square, direction, board);
            if (nowSquare == null) {
                break;
            }
            squares.add(nowSquare);
            square = nowSquare;
        }
        return squares;
    }

    private Square getNextEmptySquare(Square square, Direction direction, Board<Square, ?, ?, ?, ?> board) {
        try {
            Square nextSquare = boardNavigator.nextCell(square, board.getBoardSize(), direction);
            if (board.getPiece(nextSquare).isEmpty()) {
                return nextSquare;
            }
        } catch (NotExistSquareException | NullPointerException e) {
            return null;
        }
        return null;
    }
}
