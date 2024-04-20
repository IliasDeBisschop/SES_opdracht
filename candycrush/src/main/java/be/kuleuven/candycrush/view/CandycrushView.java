package be.kuleuven.candycrush.view;

import be.kuleuven.candycrush.candies.*;
import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.CandycrushModel;
import be.kuleuven.candycrush.model.Position;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Collection;
import java.util.Iterator;

import static javafx.scene.paint.Color.*;

public class CandycrushView extends Region {
    private CandycrushModel model;
    private int widthCandy;
    private int heigthCandy;

    public CandycrushView(CandycrushModel model) {
        this.model = model;
        widthCandy = 30;
        heigthCandy = 30;
        update();
    }

    public void update(){
        getChildren().clear();
        Position position = new Position(0,0,model.getBoardSize());
        Iterator<Candy> iter = model.getSpeelbord().iterator();
        while(iter.hasNext()) {
            var candy = iter.next();
            Rectangle rectangle = new Rectangle(position.rowNr() * widthCandy, position.columNr() * heigthCandy, widthCandy,heigthCandy);
            rectangle.setFill(TRANSPARENT);
            rectangle.setStroke(BLACK);
            Node candyNode = makeCandyShape(position, candy);
            candyNode.setLayoutX(rectangle.getX() + (rectangle.getWidth() - candyNode.getBoundsInLocal().getWidth()) / 2);
            candyNode.setLayoutY(rectangle.getY() + (rectangle.getHeight() + candyNode.getBoundsInLocal().getHeight()) / 2);
            getChildren().addAll(rectangle,candyNode);

            if (position.isLastColumn()) {
                position = new Position(0, position.columNr()+1, position.boardSize());

            } else {
                position = new Position(position.rowNr()+1, position.columNr(), position.boardSize());
            }
        }
    }

    public int getIndexOfClicked(MouseEvent me){
        int index;
        try {
            Position position = new Position((int) me.getY()/heigthCandy, (int) me.getX()/widthCandy, model.getBoardSize());
            System.out.println(me.getX()+" - "+me.getY()+" - "+position.rowNr()+" - "+position.columNr());
            index = position.toIndex();
            System.out.println(index);

        } catch (IllegalArgumentException e){
            index = -1;
        }
        return index;

    }

    public Node makeCandyShape(Position position, Candy candy){

        switch (candy){
            case BaseDestroyerCandy c -> {
                Rectangle candyRec = new Rectangle();
                candyRec.setFill(MEDIUMVIOLETRED);

                return candyRec;
            }
            case ExplosiveSugar c ->  {
                Rectangle candyRec = new Rectangle();
                candyRec.setFill(GREENYELLOW);
                return candyRec;
            }
            case ExtraSweet c -> {
                Rectangle candyRec = new Rectangle();
                candyRec.setFill(LIMEGREEN);
                return candyRec;
            }
            case MoreCandies c -> {
                Rectangle candyRec = new Rectangle();
                candyRec.setFill(DARKGOLDENROD);
                return candyRec;
            }
            case NormalCandy c -> {
                Circle candyCi = new Circle();
                switch (c.color()){
                    case 0 -> candyCi.setFill(ALICEBLUE);
                    case 1 -> candyCi.setFill(BLUE);
                    case 2 -> candyCi.setFill(BLUEVIOLET);
                    case 3 -> candyCi.setFill(DEEPSKYBLUE);
                    default -> throw new IllegalArgumentException("deze kleur bestaat niet");
                }
                return candyCi;
            }
        }
    }
}
