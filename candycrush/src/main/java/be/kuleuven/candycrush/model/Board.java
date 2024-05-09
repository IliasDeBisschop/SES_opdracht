package be.kuleuven.candycrush.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
// start
public class Board<E> {
    public Map<Position, E> board;
    public Map<E, Position> reverseBoard;
    public BoardSize boardSize;

    public Board(BoardSize boardSize, Map<Position, E> board) {
        this.board = board;
        this.reverseBoard = board.entrySet().stream()
                            .collect(Collectors
                                     .toMap(Map.Entry::getValue, Map.Entry::getKey));
        this.boardSize = boardSize;
    }
    public Board(Map<E, Position> reverseBoard,BoardSize boardSize) {
        this.reverseBoard = reverseBoard;
        this.board = reverseBoard.entrySet().stream()
                .collect(Collectors
                        .toMap(Map.Entry::getValue, Map.Entry::getKey));
        this.boardSize = boardSize;
    }
    public E getCellAt(Position position){
        return board.get(position);
    }

    public List<Position> getPositionsOfElement(E cell) {
        var result = new ArrayList<Position>();
        for (var entry : reverseBoard.entrySet()) {
            var cel = entry.getKey();
            if (cel != null && cel.equals(cell)) {
                result.add(entry.getValue());
            }
        }
        return Collections.unmodifiableList(result);

    }

    public void replaceCellAt(Position position, E newCell){
        board.replace(position, newCell);
        reverseBoard.replace(newCell, position);
    }

    public <T extends E> void fill(Function<Position, T> cellCreator) {
        for (Position position : this.boardSize.positions()) {
            E cell = cellCreator.apply(position);
            board.put(position, cell);
            reverseBoard.put(cell, position);
        }
    }

    public void copyTo(Board<? super E> otherBoard){
        if(!otherBoard.boardSize.equals(boardSize)) throw new IllegalArgumentException("moet hetzelfde grote board zijn");
        for(var position : boardSize.positions()){
            otherBoard.replaceCellAt(position, board.get(position));
        }
    }
}


