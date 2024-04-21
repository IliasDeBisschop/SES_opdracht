package be.kuleuven.candycrush.model;


import java.util.stream.StreamSupport;

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
        return StreamSupport.stream(boardSize.positions().spliterator(), true)
                .filter(position -> Math.abs(position.rowNr()-this.rowNr())<=1 &&
                                    Math.abs(position.columNr-this.columNr)<=1)
                .toList();
    }
// inisiele neighborPositions voor 8.4
//        return IntStream.range(0, boardSize.row())
//                .boxed()
//                .flatMap(row ->
//                        IntStream.range(0, boardSize.colum())
//                .mapToObj(column -> new Position(row, column, boardSize)))
//                .filter(a->!((a.columNr > this.rowNr + 1 || a.rowNr > this.rowNr + 1)
//                              ||(a.columNr < this.rowNr - 1 || a.rowNr < this.rowNr - 1)))
//                .filter(a->!((a.rowNr == this.rowNr && a.columNr == this.columNr)))
//                .toList();

    public boolean isLastColumn(){
        return this.rowNr == boardSize().row() - 1;
    }

}

