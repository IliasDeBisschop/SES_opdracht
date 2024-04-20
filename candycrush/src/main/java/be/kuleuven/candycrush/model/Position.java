package be.kuleuven.candycrush.model;


import java.util.stream.StreamSupport;

public record Position(int rowNr, int columNr, BoardSize boardSize) {
    public Position{
        if (rowNr < 0) throw new IllegalArgumentException("row mag niet negatief zijn");
        if (rowNr >= boardSize.row()) throw new IllegalArgumentException("row mag niet buiten het bord vallen");
        if (columNr < 0) throw new IllegalArgumentException("colum mag niet negatief zijn");
        if (columNr >= boardSize.colum()) throw new IllegalArgumentException("colum mag niet buiten het bord vallen");
    }
    int toIndex(){
      return boardSize.colum()*rowNr+columNr;
    }
    static Position fromIndex(int index, BoardSize size){
        if(index >= size.row()* size.colum()) throw new IllegalArgumentException();
        int row = index % size.row();
        int colum =  index / size.row();
        return new Position(row, colum, size);
    }
    Iterable<Position> neighborPositions() {
        return StreamSupport.stream(boardSize.positions().spliterator(), true)
                .filter(a -> !((a.columNr > this.rowNr + 1 || a.rowNr > this.rowNr + 1)
                        || (a.columNr < this.rowNr - 1 || a.rowNr < this.rowNr - 1)))
                .filter(a -> !((a.rowNr == this.rowNr && a.columNr == this.columNr)))
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

    boolean isLastColumn(){
        return columNr == boardSize().colum()-1;
    }

}

