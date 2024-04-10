package be.kuleuven.candycrush.model;

public record Position(int rowNr, int columNr, BoardSize boardSize) {
    public Position{
        if (rowNr < 0) throw new IllegalArgumentException();
        if (rowNr >= boardSize().row()) throw new IllegalArgumentException();
        if (columNr < 0) throw new IllegalArgumentException();
        if (columNr >= boardSize().colum()) throw new IllegalArgumentException();

    }
}
