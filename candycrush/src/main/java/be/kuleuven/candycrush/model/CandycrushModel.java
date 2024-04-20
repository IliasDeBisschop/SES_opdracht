package be.kuleuven.candycrush.model;

import be.kuleuven.CheckNeighboursInGrid;
import be.kuleuven.candycrush.candies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;


public class  CandycrushModel {
    private String speler;
    private ArrayList<Candy> speelbord;
    private BoardSize boardSize;
    private int score;



    public CandycrushModel(String speler) {
        this.speler = speler;
        speelbord = new ArrayList<>();
        score = 0;
        boardSize = new BoardSize(4,4);

        for (int i = 0; i < boardSize.colum()* boardSize.row(); i++){
            speelbord.add(randomCandy());
        }
    }

    public int getScore() {
        return score;
    }

    public String getSpeler() {
        return speler;
    }

    public ArrayList<Candy> getSpeelbord() {
        return speelbord;
    }

    public BoardSize getBoardSize() { return boardSize; }

    public void candyWithIndexSelected(Position position){
        //TODO: update method so it also changes direct neighbours of same type and updates score
        if (position.toIndex() != -1){
            System.out.println(position.toIndex());
            List<Position> result = StreamSupport.stream(getSameNeighbourPositions(position).spliterator(),true).toList();

            if(result.size()>=3) {
                for (Position pos : result) {
                    speelbord.set(pos.toIndex(), randomCandy());
                }
                increaseScore(result.size());
            }
        }else{
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }
    public Iterable<Position> getSameNeighbourPositions(Position position){
        return StreamSupport.stream(position.neighborPositions().spliterator(),true)
                .filter(position1 -> speelbord.get(position1.toIndex()).equals(speelbord.get(position.toIndex())))
                .toList();
         }
    public void setSpeelbord(ArrayList<Candy> speelbord) {
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
