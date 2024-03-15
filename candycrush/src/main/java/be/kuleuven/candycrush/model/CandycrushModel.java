package be.kuleuven.candycrush.model;

import be.kuleuven.CheckNeighboursInGrid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class  CandycrushModel {
    private String speler;
    private ArrayList<Integer> speelbord;
    private int width;
    private int height;
    private int score;



    public CandycrushModel(String speler) {
        this.speler = speler;
        speelbord = new ArrayList<>();
        score = 0;
        width = 4;
        height = 4;

        for (int i = 0; i < width*height; i++){
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            speelbord.add(randomGetal);
        }
    }

    public int getScore() {
        return score;
    }

    public static void main(String[] args) {
        CandycrushModel model = new CandycrushModel("arne");
        int i = 1;
        Iterator<Integer> iter = model.getSpeelbord().iterator();
        while(iter.hasNext()){
            int candy = iter.next();
            System.out.print(candy);
            if(i% model.getWidth()==0){
                System.out.print("\n");
                i = 1;
            }
            i++;
        }
        System.out.print("\n");

    }
    public String getSpeler() {
        return speler;
    }

    public ArrayList<Integer> getSpeelbord() {
        return speelbord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void candyWithIndexSelected(int index){
        //TODO: update method so it also changes direct neighbours of same type and updates score
        if (index != -1){
            CheckNeighboursInGrid ch= new CheckNeighboursInGrid();
            ArrayList<Integer> result = new ArrayList<>();
            result = ch.getSameNeighboursIds(getSpeelbord(), getWidth(),getWidth(), index);
            result.add(index);
            if(result.size()>=3) {
                for(int i=0; i<result.size(); i++) {
                    Random random = new Random();
                    int randomGetal = random.nextInt(5) + 1;
                    speelbord.set(result.get(i), randomGetal);
                }
                increaseScore(result.size());
            }
        }else{
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }

    public void setSpeelbord(ArrayList<Integer> speelbord) {
        this.speelbord = speelbord;
    }

    public int getIndexFromRowColumn(int row, int column) {
        return column+row*width;
    }
    public void increaseScore(int add){
        this.score+=add;
    }
}
