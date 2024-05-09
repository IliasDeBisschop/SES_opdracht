package be.kuleuven.candycrush.model;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.IntStream;

public record BoardSize(int row, int colum) {
    public BoardSize {
        if (row < 0) throw new IllegalArgumentException("rows must be non-negative");
        if (colum < 0) throw new IllegalArgumentException("colums must be non-negative");
    }
    public Collection<Position> positions(){
        return IntStream.range(0, this.row())
                .boxed()
                .flatMap(row -> IntStream.range(0, this.colum())
                        .mapToObj(column -> new Position(row, column, this)))
                .toList();

    }
}