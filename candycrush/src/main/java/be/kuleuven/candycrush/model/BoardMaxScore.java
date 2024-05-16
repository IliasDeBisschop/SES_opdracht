package be.kuleuven.candycrush.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardMaxScore {
    private Board<Candy> speelbord;
    private int score;

    public BoardMaxScore(Board<Candy> speelbord) {
        this.speelbord = speelbord;
        score = 0;
    }

    public BoardMaxScore(Board<Candy> speelbord, int score) {
        this.speelbord = speelbord;
        this.score = score;
    }
    public void increaseScore(int add){
        if (add < 0 ) throw new IllegalArgumentException();
        this.score+=add;
    }

    public int getScore() {
        return score;
    }
    public BoardSize getBoardSize() {
        return speelbord.boardSize;
    }
    public Board<Candy> getSpeelbord() {
        return speelbord;
    }

    public boolean firstTwoHaveCandy(Candy candy, Stream<Position> positions){
        var list = positions.limit(2).filter(pos-> speelbord.getCellAt(pos) != null
                                                    && speelbord.board.get(pos).equals(candy))
                .toList();
        return list.size() > 1;
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
        if (speelbord.getCellAt(pos) == null) return new ArrayList<>();
        return pos.walkRight()
                .takeWhile(position -> speelbord.getCellAt(pos).equals(speelbord.getCellAt(position)))
                .toList();
    }
    public List<Position> longestMatchDown(Position pos){
        if (speelbord.getCellAt(pos) == null) return new ArrayList<>();
        return pos.walkDown()
                .takeWhile(position -> speelbord.getCellAt(pos).equals(speelbord.getCellAt(position)))
                .toList();
    }


    public int clearMatch(List<Position> match){
        if (match.isEmpty()) return 0;

        var firstCandy = match.getFirst();
        var candy = speelbord.board.remove(firstCandy);
        var newMatch = match.subList(1, match.size());

        if (candy == null) return clearMatch(newMatch);
        return 1 + clearMatch(newMatch);
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
                speelbord.board.put(pos, speelbord.getCellAt(nextPosition));
                speelbord.board.remove(nextPosition);
            }
        }

        var nextPos = new Position(pos.rowNr()-1, pos.columNr(), pos.boardSize());
        fallDownTo(nextPos);
    }
    public boolean updateBoard(){
        var matches = findAllMatches();
        if (matches.isEmpty()) return false;

        for (var match: matches){
            var scoreMatch = clearMatch(match);
            increaseScore(scoreMatch);
        }
        for (int col = 0; col < speelbord.boardSize.colum(); col++){
            fallDownTo(new Position(speelbord.boardSize.row()-1, col,speelbord.boardSize));
        }
        updateBoard();
        return true;
    }
    public Solution maximizeScore(){
        return maximizeScore(new ArrayList<>(), null);
    }
    private Solution maximizeScore(List<List<Position>> solution, Solution bestSoFar ){
        if (speelbord.board.size()<=3){
            if (bestSoFar == null || score > bestSoFar.score ||
                    score == bestSoFar.score && solution.size() < bestSoFar.switches().size()){
                return new Solution(new ArrayList<>(solution), score);
            } else return bestSoFar;
        }
        for (var pos: speelbord.boardSize.positions().stream()
                .filter(position -> speelbord.board.containsKey(position))
                .toList()){
            var neighbours = new ArrayList<Position>();
            if (!pos.isLastColumn()){
                neighbours.add(new Position(pos.rowNr()+1, pos.columNr(), pos.boardSize()));
            }
            if (pos.columNr() != speelbord.boardSize.colum() - 1) {
                neighbours.add(new Position(pos.rowNr(), pos.columNr()+1, pos.boardSize()));
            }
            for (var pos2: neighbours){
                if (speelbord.board.containsKey(pos) && speelbord.board.containsKey(pos2)) {
                    if (matchAfterSwitch(pos, pos2)) {
                        var tempB = deepCopyBoard(speelbord.board) ;
                        int tempS = score;
                        switchCandy(pos, pos2);
                        List<Position> switchPs = new ArrayList<>();
                        switchPs.add(pos);
                        switchPs.add(pos2);
                        solution.add(switchPs);

                        updateBoard();
                        var result = maximizeScore(solution, bestSoFar);
                        bestSoFar = new Solution(new ArrayList<>(result.switches), result.score);

                        speelbord.board.clear();
                        speelbord.board.putAll(tempB);
                        score = tempS;
                        solution.removeLast();
                    }
                }
            }
        }
        if (bestSoFar == null || score > bestSoFar.score||
                score == bestSoFar.score && solution.size() < bestSoFar.switches().size()){
            return new Solution(new ArrayList<>(solution), score);
        } else return bestSoFar;
    }

    private void switchCandy(Position pos, Position pos2) {
        if (!pos.boardSize().equals(pos2.boardSize()) &&
                !pos.boardSize().equals(this.getBoardSize()))
            throw new IllegalArgumentException("de twee posities moeten in op het dit board liggen.");
        if (speelbord.getCellAt(pos) == null || speelbord.getCellAt(pos2) == null )
            throw new IllegalArgumentException("er moet een Candy zijn op bijde posities");

        var temp = speelbord.getCellAt(pos);
        speelbord.replaceCellAt(pos, speelbord.getCellAt(pos2));
        speelbord.replaceCellAt(pos2, temp);
    }

    public boolean matchAfterSwitch(Position pos, Position pos2) {
    switchCandy(pos,pos2);
    var matches = findAllMatches();
    switchCandy(pos,pos2);
    return !matches.isEmpty();
    }

    public record Solution(List<List<Position>> switches, int score){}

    private Map<Position, Candy> deepCopyBoard(Map<Position, Candy> original) {
        Map<Position, Candy> copy = new HashMap<>();
        for (Map.Entry<Position, Candy> entry : original.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().copy());
        }
        return copy;
    }


}
