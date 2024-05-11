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

    private CandycrushModel(BoardSize size) {
        this.speler = "speler";
        score = 0;
        boardSize = size;
        speelbord = new Board<>(boardSize, new HashMap<>());
    }

    public static CandycrushModel createBoardFromString(String configuration) {
        var lines = configuration.toLowerCase().lines().toList();
        BoardSize size = new BoardSize(lines.size(), lines.getFirst().length());
        var model = createNewModel(size); // deze moet je zelf voorzien
        for (int row = 0; row < lines.size(); row++) {
            var line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                model.setCandyAt(new Position(row, col, size), characterToCandy(line.charAt(col)));
            }
        }
        return model;
    }

    private void setCandyAt(Position position, Candy candy) {
        speelbord.board.put(position,candy);
    }

    private static CandycrushModel createNewModel(BoardSize size) {
        return new CandycrushModel(size);
    }

    private static Candy characterToCandy(char c) {
        return switch (c) {
            case '.' -> null;
            case 'o' -> new NormalCandy(0);
            case '*' -> new NormalCandy(1);
            case '#' -> new NormalCandy(2);
            case '@' -> new NormalCandy(3);
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
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

}
