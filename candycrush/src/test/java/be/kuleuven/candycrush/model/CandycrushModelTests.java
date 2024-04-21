package be.kuleuven.candycrush.model;

import be.kuleuven.CheckNeighboursInGrid;
import be.kuleuven.candycrush.candies.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class CandycrushModelTests {

    @Test
    public void spelerIlias_aanroepenVanDeConstructor_constructorOK(){
        CandycrushModel model = new CandycrushModel("Ilias");
        assert (model.getSpeler() == "Ilias");
        assert (model.getSpeelbord().board.size() == 16);
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

        for (Candy i : model.getSpeelbord().board) {
            assert (i != null);
        }
    }
    @Test
    public void Spelbord0010110220130111Index0_oproepenVanCandyWithIndexSelected_scoreVeranderNiet(){
        CandycrushModel model = new CandycrushModel("Ilias");
        var boardsize = new BoardSize(4,4);
        int[] candyColor = {0, 0, 1, 0,
                            1, 1, 0, 2,
                            2, 0, 1, 3,
                            0, 1, 1, 1};
        var speelbord = new Board<Candy>(boardsize,new ArrayList<>());

        speelbord.fill((position -> new NormalCandy(candyColor[position.toIndex()])));

        model.setSpeelbord(speelbord);
        model.candyWithIndexSelected(Position.fromIndex(0,model.getBoardSize()));
        assert(model.getScore() ==0);
    }
    @Test
    public void Spelbord0010110220130111Index14_oproepenVanCandyWithIndexSelected_scoreVeandertNaar4(){
        CandycrushModel model = new CandycrushModel("Ilias");
        var boardsize = new BoardSize(4,4);
        int[] candyColor = {0, 0, 1, 0,
                            1, 1, 0, 2,
                            2, 0, 1, 3,
                            0, 1, 1, 1};
        var speelbord = new Board<Candy>(boardsize,new ArrayList<>());

        speelbord.fill((position -> new NormalCandy(candyColor[position.toIndex()])));

        model.setSpeelbord(speelbord);
        model.candyWithIndexSelected(Position.fromIndex(14, model.getBoardSize()) );
        System.out.println(model.getScore());
        assert(model.getScore() == 4);
    }
    @Test
    public void Spelbord0000000000000000Index5_oproepenVanCandyWithIndexSelected_scoreVeandertNaar9(){
        CandycrushModel model = new CandycrushModel("Ilias");
        var boardsize = new BoardSize(4,4);
        int[] candyColor = {0, 0, 0, 0,
                            0, 0, 0, 0,
                            0, 0, 0, 0,
                            0, 0, 0, 0};
        var speelbord = new Board<Candy>(boardsize,new ArrayList<>());

        speelbord.fill((position -> new NormalCandy(candyColor[position.toIndex()])));

        model.setSpeelbord(speelbord);
        model.candyWithIndexSelected(Position.fromIndex(5, model.getBoardSize()));
        assert(model.getScore() ==9);
    }

    @Test
    public void boad2rijenen4kolommenPosition12_oproepenVanToIndex_Returns6(){
        Position position = new Position(1,2,new BoardSize(2,4));
        System.out.println(position.toIndex());
        assert (position.toIndex() == 6);
    }

    @Test
    public void boad5rijenen6kolommenPosition32_oproepenVanToIndex_Returns(){
        Position position = new Position(3,2,new BoardSize(5,6));
        System.out.println(position.toIndex());
        assert (position.toIndex() == 20);
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
        assert (position.equals(new Position(3,2,boardSize)));
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
    @Test
    public void board4bij4Pos0_0_islastcolum_false(){
        Position position = new Position(0,0,new BoardSize(4,4));
        assert (!position.isLastColumn());
    }

    @Test
    public void board4bij4Pos3_0tot3_islastcolum_true(){
        for (int i=0;i<4;i++){
            Position position = new Position(3,i,new BoardSize(4,4));
            System.out.println(position);
            assert (position.isLastColumn());
        }
    }

    @Test
    public void niewBoardMakenVanIntegersWaardeGelijkAanIndex_getIndex0_return0(){
        var boardSize = new BoardSize(1,1);
        var boardIn = new ArrayList<Integer>();
        boardIn.add(0);

        var board = new Board<Integer>(boardSize, boardIn);
        var result = board.getCellAt(Position.fromIndex(0,boardSize));

        assert result.equals(0);
    }

    @Test
    public void niewBoardMakenVanIntegersWaardeGelijkAanIndex_getIndex2_return2(){
        var boardSize = new BoardSize(2,2);
        var boardIn = new ArrayList<Integer>();
        boardIn.add(0);
        boardIn.add(1);
        boardIn.add(2);
        boardIn.add(3);

        var board = new Board<Integer>(boardSize, boardIn);
        var result = board.getCellAt(Position.fromIndex(2,boardSize));

        assert result.equals(2);
    }

    @Test
    public void niewBoardMakenVanIntegersWaardeGelijkAanIndex_ReplaceCellAtp0met10_return10(){
        var boardSize = new BoardSize(1,1);
        var boardIn = new ArrayList<Integer>();
        boardIn.add(0);

        var board = new Board<Integer>(boardSize, boardIn);
        board.replaceCellAt(Position.fromIndex(0,boardSize),10);
        var result = board.getCellAt(Position.fromIndex(0,boardSize));
        assert result.equals(10);
    }

    @Test
    public void niewBoardMakenVanIntegers_fillAllesOp0(){
        var boardSize = new BoardSize(2,2);
        var boardIn = new ArrayList<Integer>();
        var board = new Board<Integer>(boardSize,boardIn);
        board.fill((position)->0);
        var result = new ArrayList<Integer>();
        for (int i=0;i<4;i++){
            result.add(0);
        }
        assert (board.board.equals(result));
    }

    @Test
    public void niewBoardMakenVanIntegers_fillAllesOpHunInex(){
        var boardSize = new BoardSize(2,2);
        var boardIn = new ArrayList<Integer>();
        var board = new Board<Integer>(boardSize,boardIn);
        board.fill((position)->0);
        board.fill(Position::toIndex);
        var result = new ArrayList<Integer>();
        for (int i=0;i<4;i++){
            result.add(i);
        }
        assert (board.board.equals(result));
    }

    @Test
    public void nieuwBoardMakenVanIntersWaardeHunIndex_copyTo(){
        var boardSize = new BoardSize(2,2);
        var boardIn = new ArrayList<Integer>();
        var board = new Board<Integer>(boardSize,boardIn);
        var otherBoard = new Board<Integer>(boardSize,boardIn);
        board.fill(Position::toIndex);

        otherBoard = board.copyTo(otherBoard);

        var result = new ArrayList<Integer>();
        for (int i=0;i<4;i++){
            result.add(i);
        }
        assert (otherBoard.board.equals(result));

    }

    @Test
    public void niewBoardMakenMetCandy_getIndex0_return0(){
        var boardSize = new BoardSize(1,1);
        var boardIn = new ArrayList<Integer>();
        boardIn.add(0);

        var board = new Board<Integer>(boardSize, boardIn);
        var result = board.getCellAt(Position.fromIndex(0,boardSize));

        assert result.equals(0);
    }

    @Test
    public void niewBoardMakenMetVerschillendeCandy_getIndex2_return2(){
        var boardSize = new BoardSize(2,2);
        var boardIn = new ArrayList<Candy>();
        boardIn.add(new MoreCandies());
        boardIn.add(new ExtraSweet());
        boardIn.add(new ExplosiveSugar());
        boardIn.add(new BaseDestroyerCandy());

        var board = new Board<Candy>(boardSize, boardIn);
        var result = board.getCellAt(Position.fromIndex(2,boardSize));

        assert result.equals(new ExplosiveSugar());
    }

    @Test
    public void niewBoardMakenMetCandy_ReplaceCellAtp0metMoarCany(){
        var boardSize = new BoardSize(1,1);
        var boardIn = new ArrayList<Candy>();
        boardIn.add(new NormalCandy(0));

        var board = new Board<Candy>(boardSize, boardIn);
        board.replaceCellAt(Position.fromIndex(0,boardSize),new MoreCandies());
        var result = board.getCellAt(Position.fromIndex(0,boardSize));
        assert result.equals(new MoreCandies());
    }

    @Test
    public void niewBoardMakenMetCandy_fillAllesOp0(){
        var boardSize = new BoardSize(2,2);
        var boardIn = new ArrayList<Candy>();
        var board = new Board<Candy>(boardSize,boardIn);
        board.fill((position)->new MoreCandies());
        var result = new ArrayList<Candy>();
        for (int i=0;i<4;i++){
            result.add(new MoreCandies());
        }
        assert (board.board.equals(result));
    }

    @Test
    public void nieuwBoardMakenVanCandy_copyTo(){
        var boardSize = new BoardSize(2,2);
        var boardIn = new ArrayList<Candy>();
        var board = new Board<Candy>(boardSize,boardIn);
        var otherBoard = new Board<Candy>(boardSize,boardIn);
        board.fill((position)->new NormalCandy(0));

        otherBoard = board.copyTo(otherBoard);

        var result = new ArrayList<Candy>();
        for (int i=0;i<4;i++){
            result.add(new NormalCandy(0));
        }
        assert (otherBoard.board.equals(result));

    }
}