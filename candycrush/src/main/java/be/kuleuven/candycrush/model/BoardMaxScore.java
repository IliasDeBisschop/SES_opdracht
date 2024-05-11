package be.kuleuven.candycrush.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardMaxScore {
    private Board<Candy> speelbord;

    public BoardMaxScore(Board<Candy> speelbord) {
        this.speelbord = speelbord;
    }
    public BoardSize getBoardSize() {
        return speelbord.boardSize;
    }

    public boolean firstTwoHaveCandy(Candy candy, Stream<Position> positions){
        var list = positions.limit(2).filter(pos->speelbord.board.get(pos).equals(candy)).toList();
        if (list.size() <= 1) return false;
        else return true;
    }

    public Set<List<Position>> findAllMatches(){
        var horizaleM = horizontalStartingPositions().map(this::longestMatchToRight)
                .filter(list -> list.size() >= 3);

        var verticaleM = verticalStartingPositions().map(this::longestMatchDown)
                .filter(list -> list.size() >= 3);

        return Stream.concat(horizaleM, verticaleM).collect(Collectors.toSet());
    }

    public Stream<Position> horizontalStartingPositions(){
        return speelbord.boardSize.positions().stream().filter(position -> position.columNr() == 0 ||
                !firstTwoHaveCandy(speelbord.getCellAt(position), position.walkLeft()));
    }

    public Stream<Position> verticalStartingPositions(){
        return speelbord.boardSize.positions().stream().filter(position -> position.rowNr() == 0 ||
                !firstTwoHaveCandy(speelbord.getCellAt(position), position.walkUp()));
    }
    public List<Position> longestMatchToRight(Position pos){
        return pos.walkRight()
                .takeWhile(position -> speelbord.getCellAt(pos).equals(speelbord.getCellAt(position)))
                .toList();
    }
    public List<Position> longestMatchDown(Position pos){
        return pos.walkDown()
                .takeWhile(position -> speelbord.getCellAt(pos).equals(speelbord.getCellAt(position)))
                .toList();
    }

    public Board<Candy> getSpeelbord() {
        return speelbord;
    }
    public void clearMatch(List<Position> match){
        if (match.isEmpty()) return;

        var firstCandy = match.getFirst();
        speelbord.board.remove(firstCandy);
        var newMatch = match.subList(1, match.size());
        clearMatch(newMatch);
    }
    public void fallDownTo(Position pos){
        if (pos.rowNr() == 0) return;

        if (!speelbord.board.containsKey(pos)){
            var listNestPositions = new ArrayList<>(
                                 pos.walkUp().skip(1)
                                .toList()
                            );
            if (listNestPositions.isEmpty()) return;
            var nextPosition = listNestPositions.removeFirst();
            while (speelbord.getCellAt(nextPosition) == null && !listNestPositions.isEmpty()){
                nextPosition = listNestPositions.removeFirst();
            }

            if (speelbord.getCellAt(nextPosition) != null){
                speelbord.replaceCellAt(pos, speelbord.getCellAt(nextPosition));
                speelbord.board.remove(nextPosition);
            }
        }

        var nextPos = new Position(pos.rowNr()-1, pos.columNr(), pos.boardSize());
        fallDownTo(nextPos);
    }
}
