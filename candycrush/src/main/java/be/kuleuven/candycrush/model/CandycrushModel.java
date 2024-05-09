package be.kuleuven.candycrush.model;

import be.kuleuven.CheckNeighboursInGrid;
import be.kuleuven.candycrush.candies.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class  CandycrushModel {
    private String speler;
    private Board<Candy> speelbord;
    private BoardSize boardSize;
    private int score;



    public CandycrushModel(String speler) {
        this.speler = speler;
        score = 0;
        boardSize = new BoardSize(4,4);
        speelbord = new Board<>(boardSize, new HashMap<>());
        speelbord.fill((position)->randomCandy());
    }

    public int getScore() {
        return score;
    }

    public String getSpeler() {
        return speler;
    }

    public Board<Candy> getSpeelbord() {
        return speelbord;
    }

    public BoardSize getBoardSize() { return boardSize; }

    public void candyWithIndexSelected(Position position){
        //TODO: update method so it also changes direct neighbours of same type and updates score
        if (position.toIndex() != -1){
            List<Position> result = StreamSupport.stream(getSameNeighbourPositions(position).spliterator(),true).toList();

            if(result.size()>=3) {
                for (Position pos : result) {
                    speelbord.replaceCellAt(pos, randomCandy());
                }
                increaseScore(result.size());
            }
        }else{
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }
    public Iterable<Position> getSameNeighbourPositions(Position position){
        return StreamSupport.stream(position.neighborPositions().spliterator(),true)
                .filter(position1 -> speelbord.getCellAt(position1).equals(speelbord.getCellAt(position)))
                .toList();
         }
    public void setSpeelbord(Board<Candy> speelbord) {
        this.speelbord = speelbord;
    }
    public void increaseScore(int add){
        if (add < 0 ) throw new IllegalArgumentException();
        this.score+=add;
    }

    public Candy randomCandy(){
        Random random = new Random();
        int candy = random.nextInt(8);
        return switch (candy) {
            case 4 -> new BaseDestroyerCandy();
            case 5 -> new ExplosiveSugar();
            case 6 -> new ExtraSweet();
            case 7 -> new MoreCandies();
            default -> new NormalCandy(candy);
        };

    }
    public Set<List<Position>> findAllMatches(){
        var horizaleM = horizontalStartingPositions().map(this::longestMatchToRight)
                        .filter(list -> list.size() >= 3);

        var verticaleM = verticalStartingPositions().map(this::longestMatchDown)
                        .filter(list -> list.size() >= 3);

        return Stream.concat(horizaleM, verticaleM).collect(Collectors.toSet());
    }

    public boolean firstTwoHaveCandy(Candy candy, Stream<Position> positions){
        var list = positions.limit(2).filter(pos->speelbord.board.get(pos).equals(candy)).toList();
        if (list.size() <= 1) return false;
        else return true;
    }



    public Stream<Position> horizontalStartingPositions(){
        return boardSize.positions().stream().filter(position -> position.columNr() == 0 ||
                        firstTwoHaveCandy(speelbord.getCellAt(position), position.walkRight()));
    }

    public Stream<Position> verticalStartingPositions(){
        return boardSize.positions().stream().filter(position -> position.rowNr() == 0 ||
                firstTwoHaveCandy(speelbord.getCellAt(position), position.walkDown()));
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
}
