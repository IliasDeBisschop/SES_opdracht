package be.kuleuven.candycrush.model;


import java.util.Comparator;
import java.util.stream.Stream;

public record Position(int rowNr, int columNr, BoardSize boardSize) {
    public Position{
        if (rowNr < 0) throw new IllegalArgumentException("row mag niet negatief zijn");
        if (rowNr >= boardSize.row()) throw new IllegalArgumentException("row mag niet buiten het bord vallen");
        if (columNr < 0) throw new IllegalArgumentException("colum mag niet negatief zijn");
        if (columNr >= boardSize.colum()) throw new IllegalArgumentException("colum mag niet buiten het bord vallen");
    }
    public int toIndex(){
      return boardSize.colum()*rowNr+columNr;
    }

    static public Position fromIndex(int index, BoardSize boardSize){
        if(index >= boardSize.row()* boardSize.colum()) throw new IllegalArgumentException();
        int row = index / boardSize.colum();
        int colum =  index % boardSize.colum();
        return new Position(row, colum, boardSize);
    }
    public Iterable<Position> neighborPositions() {
        return boardSize.positions().stream()
                .filter(position -> Math.abs(position.rowNr()-this.rowNr())<=1 &&
                                    Math.abs(position.columNr-this.columNr)<=1)
                .toList();
    }

    public boolean isLastColumn(){
        return this.rowNr == boardSize().row() - 1;
    }

    public Stream<Position> walkLeft(){
        return boardSize.positions().stream()
                .filter(position -> position.rowNr() == this.rowNr())
                .filter(position -> position.columNr()<= this.columNr())
                .sorted(Comparator.comparingInt(Position::columNr).reversed());

    }
    public Stream<Position> walkRight(){
        return boardSize.positions().stream()
                .filter(position -> position.rowNr() == this.rowNr())
                .filter(position -> position.columNr() >= this.columNr())
                .sorted(Comparator.comparingInt(Position::columNr));
    }
    public Stream<Position> walkUp(){
        return boardSize.positions().stream()
                .filter(position -> position.columNr() == this.columNr())
                .filter(position -> position.rowNr() <= this.rowNr())
                .sorted(Comparator.comparingInt(Position::rowNr).reversed());
    }
    public Stream<Position> walkDown(){
        return boardSize.positions().stream()
                .filter(position -> position.columNr() == this.columNr())
                .filter(position -> position.rowNr() >= this.rowNr())
                .sorted(Comparator.comparingInt(Position::rowNr));
    }



}

