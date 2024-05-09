package be.kuleuven.candycrush.model;

import java.util.List;
import java.util.function.Function;

public class Board<E> {
    public List<E> board;
    public BoardSize boardSize;

    public Board(BoardSize boardSize, List<E>board) {
        this.board = board;
        this.boardSize = boardSize;
    }

    public E getCellAt(Position position){
        return board.get(position.toIndex());
    }

    public void replaceCellAt(Position position, E newCell){
        board.set(position.toIndex(), newCell);
    }

    public <T extends E> void fill(Function<Position, T> cellCreator){
        if (this.board.isEmpty()){
            for(int i = 0; i < boardSize.colum() * boardSize.row(); i++ ){
                board.add(i, cellCreator.apply(Position.fromIndex(i, boardSize)));
            }
        }
        for(int i = 0; i < boardSize.colum() * boardSize.row(); i++ ){
            board.set(i, cellCreator.apply(Position.fromIndex(i, boardSize)));
        }
    }

    public Board<? super E> copyTo(Board<? super E> otherBoard){
        if(!otherBoard.boardSize.equals(boardSize)) throw new IllegalArgumentException("moet hetzelfde grote board zijn");
        for(int i = 0; i < boardSize.colum() * boardSize.row(); i++ ){
            otherBoard.replaceCellAt(Position.fromIndex(i, boardSize), board.get(i));
        }
        return otherBoard;
    }
}


