package be.kuleuven.candycrush.model;

import be.kuleuven.CheckNeighboursInGrid;
import be.kuleuven.candycrush.candies.NormalCandy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class CandycrushModelTests {

    @Test
    public void spelerIlias_aanroepenVanDeConstructor_constructorOK(){
        CandycrushModel model = new CandycrushModel("Ilias");
        assert (model.getSpeler() == "Ilias");
        assert (model.getSpeelbord().size() == 16);
        assert (model.getBoardSize().row() == 4);
        assert (model.getBoardSize().colum()== 4);
        assert (model.getScore()== 0);
    }
    @Test
    public void Spelbord0010110220130111index5_aanroepenVanGetSameNeighboursIds_Retrun2_4_10(){
        CheckNeighboursInGrid ch = new CheckNeighboursInGrid();
        List<Integer> grid = Arrays.asList(0, 0, 1, 0,
                1, 1, 0, 2,
                2, 0, 1, 3,
                0, 1, 1, 1);
        ArrayList<Integer> resultaat = ch.getSameNeighboursIds(grid,4,4,5);
        int[] controle ={2, 4, 10};
        for(int i=0; i<resultaat.size();i++){
            assert (resultaat.get(i) == controle[i]);
        }
    }
    @Test
    public void Spelbord0010110220130111index14_aanroepenVanGetSameNeighboursIds_Retrun10_13_15(){
        CheckNeighboursInGrid ch = new CheckNeighboursInGrid();
        List<Integer> grid = Arrays.asList(0, 0, 1, 0,
                1, 1, 0, 2,
                2, 0, 1, 3,
                0, 1, 1, 1);
        ArrayList<Integer> resultaat = ch.getSameNeighboursIds(grid,4,4,14);
        int[] controle ={10, 13, 15};
        for(int i=0; i<resultaat.size();i++){
            assert (resultaat.get(i) == controle[i]);
        }
    }

    @Test
    public void Spelbord0010110220130111index0_aanroepenVanGetSameNeighboursIds_Retrun1(){
        CheckNeighboursInGrid ch = new CheckNeighboursInGrid();
        List<Integer> grid = Arrays.asList(0, 0, 1, 0,
                1, 1, 0, 2,
                2, 0, 1, 3,
                0, 1, 1, 1);
        ArrayList<Integer> resultaat = ch.getSameNeighboursIds(grid,4,4,0);
        int[] controle ={1};
        for(int i=0; i<resultaat.size();i++){
            assert (resultaat.get(i) == controle[i]);
        }
    }

    @Test
    public void score0EnVerhoogMet4_aanroepenIncreaseScore_return4(){
        CandycrushModel model = new CandycrushModel("Ilias");

        model.increaseScore(4);

        assert(model.getScore()==4);

    }
    @Test
    public void NieuwBord_constructorOproepen_Alle16plaatsenHebbenEenCandyZijn() {
        CandycrushModel model = new CandycrushModel("Ilias");

        for (Candy i : model.getSpeelbord()) {
            assert (i != null);
        }
    }
    @Test
    public void Spelbord0010110220130111Index0_oproepenVanCandyWithIndexSelected_scoreVeranderNiet(){
        CandycrushModel model = new CandycrushModel("Ilias");
        int[] candyColor =
                            {0, 0, 1, 0,
                            1, 1, 0, 2,
                            2, 0, 1, 3,
                            0, 1, 1, 1};
        var speelbord = new ArrayList<Candy>();
        for (int i = 0; i < 16; i++){
            speelbord.add(new NormalCandy(candyColor[i]));
        }
        model.setSpeelbord(speelbord);
        model.candyWithIndexSelected(Position.fromIndex(0,model.getBoardSize()));
        assert(model.getScore() ==0);
    }
    @Test
    public void Spelbord0010110220130111Index14_oproepenVanCandyWithIndexSelected_scoreVeandertNaar4(){
        CandycrushModel model = new CandycrushModel("Ilias");
        int[] candyColor = {0, 0, 1, 0,
                1, 1, 0, 2,
                2, 0, 1, 3,
                0, 1, 1, 1};
        var speelbord = new ArrayList<Candy>();
        for (int i = 0; i < 16; i++){
            speelbord.add(new NormalCandy(candyColor[i]));
        }

        model.setSpeelbord(speelbord);
        model.candyWithIndexSelected(Position.fromIndex(14, model.getBoardSize()) );
        System.out.println(model.getScore());
        assert(model.getScore() == 4);
    }
    @Test
    public void Spelbord0000000000000000Index5_oproepenVanCandyWithIndexSelected_scoreVeandertNaar9(){
        CandycrushModel model = new CandycrushModel("Ilias");
        int[] candyColor = {0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0};
        var speelbord = new ArrayList<Candy>();
        for (int i = 0; i < 16; i++){
            speelbord.add(new NormalCandy(candyColor[i]));
        }
        model.setSpeelbord(speelbord);
        model.candyWithIndexSelected(Position.fromIndex(5, model.getBoardSize()));
        assert(model.getScore() ==9);
    }

    @Test
    public void boad2rijenen4kolommenPosition12_oproepenVanToIndex_Returns6(){
        Position position = new Position(1,2,new BoardSize(2,4));
        System.out.println(position.toIndex());
        assert (position.toIndex() == 5);
    }

    @Test
    public void boad5rijenen6kolommenPosition32_oproepenVanToIndex_Returns(){
        Position position = new Position(3,2,new BoardSize(5,6));
        System.out.println(position.toIndex());
        assert (position.toIndex() == 13);
    }

    @Test
    public void board2rijenEn4kolommen_oproepenfromIndexMetIndex7_returnPosition1_3(){
        BoardSize boardSize = new BoardSize(2,4);
        Position position = Position.fromIndex(7, boardSize);
        assert (position.rowNr()==1 && position.columNr() == 3);
    }

    @Test
    public void board4rijenEn4kolommen_oproepenfromIndexMetIndex14_returnPosition2_3(){
        BoardSize boardSize =  new BoardSize(4,4);
        Position position = Position.fromIndex(14,boardSize);
        System.out.println(position);
        assert (position.equals(new Position(2,3,boardSize)));
    }

    @Test
    public void oproepenfromIndexMetIndexOngeldigeIndex_TrowExeption(){
        assertThrows(IllegalArgumentException.class, ()->Position.fromIndex(30,new BoardSize(1,1)));
    }

    @Test
    public void board4rijenEn4kolommen_oproepenNeighborPositions1_1_return012468910(){
        BoardSize boardSize = new BoardSize(4,4);
        Position position = new Position(1,1,boardSize);
        var result = position.neighborPositions();
        var correct = new ArrayList<Position>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                correct.add(new Position(i,j,boardSize));

            }
        }
        assert(result.equals(correct));
    }

    @Test
    public void board4rijenEn4kolommen_oproepenNeighborPositions0_0_return145(){
        BoardSize boardSize = new BoardSize(4,4);
        Position position = new Position(0,0,boardSize);
        var result = position.neighborPositions();
        var correct = new ArrayList<Position>();
        correct.add(new Position(0,0,boardSize));
        correct.add(new Position(0,1,boardSize));
        correct.add(new Position(1,0,boardSize));
        correct.add(new Position(1,1,boardSize));
        assert (result.equals(correct));
    }

    @Test
    public void board4rijenEn4kolommenPosition3_3_oproepenisLastColumn_resultTrue(){
        Position position = new Position(3,3, new BoardSize(4,4));
        assert (position.isLastColumn());
    }

    @Test
    public void board3rijenEn3kolommen_oproepenPositions_relultDePositions(){
        BoardSize boardSize = new BoardSize(3,3);
        var correct = new ArrayList<Position>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++) {
                correct.add(new Position(i,j,boardSize));
            }
        }
        assert (boardSize.positions().equals(correct));
    }

}
