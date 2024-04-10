package be.kuleuven.candycrush.model;

public record BoardSize(int row, int colum) {
    public BoardSize {
        if (row < 0) throw new IllegalArgumentException("rows must be non-negative");
        if (colum < 0) throw new IllegalArgumentException("colums must be non-negative");
    }
}