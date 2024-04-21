package be.kuleuven.candycrush.view;

import be.kuleuven.candycrush.candies.*;
import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.CandycrushModel;
import be.kuleuven.candycrush.model.Position;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
        Iterator<Candy> iter = model.getSpeelbord().board.iterator();
        while(iter.hasNext()) {
            var candy = iter.next();
            Rectangle rectangle = new Rectangle(position.rowNr() * widthCandy, position.columNr() * heigthCandy, widthCandy,heigthCandy);
            rectangle.setFill(TRANSPARENT);
            rectangle.setStroke(BLACK);
            Node candyNode = makeCandyShape(position, candy);
            getChildren().addAll(rectangle,candyNode);
            if (position.isLastColumn()) {
                try {
                    position = new Position(0, position.columNr()+1, position.boardSize());
                } catch (IllegalArgumentException e) {}
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
        Rectangle rectangle = new Rectangle(position.rowNr() * widthCandy, position.columNr() * heigthCandy, widthCandy,heigthCandy);

        double centerX = rectangle.getX() + rectangle.getWidth() / 2;
        double centerY = rectangle.getY() + rectangle.getHeight() / 2;

        switch (candy){
            case BaseDestroyerCandy c -> {
                Rectangle candyRec = new Rectangle(widthCandy, heigthCandy);
                candyRec.setFill(MEDIUMVIOLETRED);

                candyRec.setLayoutX(centerX - candyRec.getBoundsInLocal().getWidth() / 2);
                candyRec.setLayoutY(centerY - candyRec.getBoundsInLocal().getHeight() / 2);
                return candyRec;
            }
            case ExplosiveSugar c ->  {
                Rectangle candyRec = new Rectangle(widthCandy, heigthCandy);
                candyRec.setFill(GREENYELLOW);

                candyRec.setLayoutX(centerX - candyRec.getBoundsInLocal().getWidth() / 2);
                candyRec.setLayoutY(centerY - candyRec.getBoundsInLocal().getHeight() / 2);
                return candyRec;
            }
            case ExtraSweet c -> {
                Rectangle candyRec = new Rectangle(widthCandy, heigthCandy);
                candyRec.setFill(LIMEGREEN);

                candyRec.setLayoutX(centerX - candyRec.getBoundsInLocal().getWidth() / 2);
                candyRec.setLayoutY(centerY - candyRec.getBoundsInLocal().getHeight() / 2);
                return candyRec;
            }
            case MoreCandies c -> {
                Rectangle candyRec = new Rectangle(widthCandy, heigthCandy);
                candyRec.setFill(DARKGOLDENROD);

                candyRec.setLayoutX(centerX - candyRec.getBoundsInLocal().getWidth() / 2);
                candyRec.setLayoutY(centerY - candyRec.getBoundsInLocal().getHeight() / 2);
                return candyRec;
            }
            case NormalCandy c -> {
                Circle candyCi = new Circle(widthCandy / 2);
                switch (c.color()){
                    case 0 -> candyCi.setFill(LIGHTSKYBLUE);
                    case 1 -> candyCi.setFill(BLUE);
                    case 2 -> candyCi.setFill(BLUEVIOLET);
                    case 3 -> candyCi.setFill(DEEPSKYBLUE);
                    default -> throw new IllegalArgumentException("deze kleur bestaat niet");
                }
                candyCi.setLayoutX(centerX);
                candyCi.setLayoutY(centerY);

                return candyCi;
            }
        }
    }
}
