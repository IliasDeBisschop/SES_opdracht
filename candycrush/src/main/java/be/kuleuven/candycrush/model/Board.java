package be.kuleuven.candycrush.model;

import java.util.Map;
import java.util.function.Function;

public class Board<E> {
    public Map<Position, E> board;
    public BoardSize boardSize;

    public Board(BoardSize boardSize, Map<Position, E> board) {
        this.board = board;
        this.boardSize = boardSize;
    }

    public E getCellAt(Position position){
        return board.get(position);
    }

    public void replaceCellAt(Position position, E newCell){
        board.replace(position, newCell);

    }

    public <T extends E> void fill(Function<Position, T> cellCreator) {
        for (Position position : this.boardSize.positions()) {
            board.put(position, cellCreator.apply(position));
        }
    }

    public void copyTo(Board<? super E> otherBoard){
        if(!otherBoard.boardSize.equals(boardSize)) throw new IllegalArgumentException("moet hetzelfde grote board zijn");
        for(var position : boardSize.positions()){
            otherBoard.replaceCellAt(position, board.get(position));
        }
    }
}


