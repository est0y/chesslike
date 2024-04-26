package human.est0y.chesslike.fen;

import human.est0y.chesslike.squares.SquareCache;
import human.est0y.chesslike.domain.Board;
import human.est0y.chesslike.domain.LetterHolder;
import human.est0y.chesslike.domain.Piece;
import human.est0y.chesslike.domain.SimpleBoard;
import human.est0y.chesslike.domain.chess.Color;
import human.est0y.chesslike.domain.chess.Square;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


public class SimpleFenBoardConverter<PIECE_TYPE extends Enum<PIECE_TYPE> & LetterHolder, STATE>
        implements FenBoardConverter
        <Square, Color, PIECE_TYPE, STATE, Map<Square, Piece<Color, PIECE_TYPE, STATE>>> {

    private final SquareCache squareCache;

    private final Map<Character, PIECE_TYPE> pieceTypeByLetter;

    private final BiFunction<Color, PIECE_TYPE, STATE> defaultState;


    public SimpleFenBoardConverter(SquareCache squareCache,
                                   PIECE_TYPE[] pieceTypes,
                                   BiFunction<Color, PIECE_TYPE, STATE> defaultState) {
        this.squareCache = squareCache;
        this.pieceTypeByLetter = Arrays.stream(pieceTypes)
                .collect(Collectors.toMap(pt -> Character.toLowerCase(pt.getLetter()), pt -> pt));
        this.defaultState = defaultState;
    }

    @Override
    public String getFen(Board<Square, Color, PIECE_TYPE, STATE, Map<Square, Piece<Color, PIECE_TYPE, STATE>>> board) {
        StringBuilder resultBuilder = new StringBuilder();

        Square size = board.getBoardSize();
        for (int y = size.getY(); y > 0; y--) {
            resultBuilder.append(processRank(board, size, y, (stringBuilder, piece) -> {
                var letter = piece.getPieceType().getLetter();
                letter = piece.getColor() == Color.WHITE ? Character.toUpperCase(letter) : Character.toLowerCase(letter);
                stringBuilder.append(letter);
            }));
            resultBuilder.append("/");
        }
        resultBuilder.deleteCharAt(resultBuilder.length() - 1);

        return resultBuilder.toString();
    }

    @Override
    public Board<Square, Color, PIECE_TYPE, STATE, Map<Square, Piece<Color, PIECE_TYPE, STATE>>>
    getBoard(String fen) {
        var horizontals = fen.split("/");
        Square size = null;
        Map<Square, Piece<Color, PIECE_TYPE, STATE>> map = new HashMap<>();
        for (int row = horizontals.length, counter = 0; row != 0; row--, counter++) {
            char[] chars = horizontals[counter].toCharArray();
            int col = 0;
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    col += Character.getNumericValue(c);
                } else {
                    Color pieceColor = Character.isLowerCase(c) ? Color.BLACK : Color.WHITE;
                    var pieceType = pieceTypeByLetter.get(Character.toLowerCase(c));
                    Square square = new Square(col + 1, row);
                    var state = defaultState.apply(pieceColor, pieceType);
                    map.put(square, new Piece<>(pieceColor, pieceTypeByLetter.get(Character.toLowerCase(c)), state));
                    col++;
                }
            }
            if (size == null) {
                size = new Square(col, horizontals.length);
            }
        }
        return new SimpleBoard<>(map, size);
    }

    private String processRank(Board<Square, Color, PIECE_TYPE, STATE, Map<Square, Piece<Color, PIECE_TYPE, STATE>>>
                                       board,
                               Square boardSize, int y,
                               BiConsumer<StringBuilder, Piece<Color, PIECE_TYPE, STATE>> consumer) {
        StringBuilder rankBuilder = new StringBuilder();
        int emptySquaresCount = 0;
        for (int x = 1; x <= boardSize.getX(); x++) {
            Square square = squareCache.getSquare(x, y);
            var optionalPiece = board.getPiece(square);
            if (optionalPiece.isPresent()) {
                if (emptySquaresCount > 0) {
                    rankBuilder.append(emptySquaresCount);
                    emptySquaresCount = 0;
                }
                consumer.accept(rankBuilder, optionalPiece.get());
            } else {
                emptySquaresCount++;
            }
        }
        if (emptySquaresCount > 0) {
            rankBuilder.append(emptySquaresCount);
        }
        return rankBuilder.toString();
    }
}
