package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.candies.NormalCandy;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashSet;

import static be.kuleuven.candycrush.model.CandycrushModel.createBoardFromString;

public class BoardMaxScoreTest {
    //hulpfunctie
    public void printSolution(BoardMaxScore.Solution solution){
        System.out.println("score: "+ solution.score());
        System.out.println("solution size: "+ solution.switches().size());

        solution.switches().forEach(System.out::println);
    }
    //firstTwoHaveCandy
    @Test
    public void lijstMet2NormalCandy2_controleOpNormalCandy2_firstTwoHaveCandy_returnTrue(){
        CandycrushModel model = createBoardFromString("""
                               ##*
                               o*#""");
        var speelboard = new BoardMaxScore(model.getSpeelbord());

        var stream = model.getBoardSize().positions().stream();
        var candy = new NormalCandy(2);
        var result = speelboard.firstTwoHaveCandy(candy,stream);

        assert(result);
    }
    @Test
    public void lijstMet2NormalCandy2_controleOpNormalCandy1_firstTwoHaveCandy_returnFalse(){
        CandycrushModel model = createBoardFromString("""
                               ##*
                               o*#""");
        var speelboard = new BoardMaxScore(model.getSpeelbord());

        var stream = model.getBoardSize().positions().stream();
        var candy = new NormalCandy(1);
        var result = speelboard.firstTwoHaveCandy(candy,stream);

        assert(!result);
    }
    @Test
    public void lijstMet1NormalCandy2_controleOpNormalCandy1_firstTwoHaveCandy_returnFalse(){
        CandycrushModel model = createBoardFromString("""
                               #""");
        var speelboard = new BoardMaxScore(model.getSpeelbord());

        var stream = model.getBoardSize().positions().stream();
        var candy = new NormalCandy(1);
        var result = speelboard.firstTwoHaveCandy(candy,stream);

        assert(!result);
    }
    @Test
    public void lijstMet2NormalCandy2_controleOpEerstCandy_firstTwoHaveCandy_returnTrue(){
        CandycrushModel model = createBoardFromString("""
                               ##*
                               o*#""");
        var speelboard = new BoardMaxScore(model.getSpeelbord());
        var stream = model.getBoardSize().positions().stream();

        var candy = model.getBoardSize().positions().stream()
                .limit(1)
                .map(pos->model.getSpeelbord().getCellAt(pos))
                .toList().getFirst();

        var result = speelboard.firstTwoHaveCandy(candy,stream);

        assert(result);
    }
    @Test
    public void lijstMet2VerschillendeNormalCandy_controleOpEerstCandy_firstTwoHaveCandy_returnFalse(){
        CandycrushModel model = createBoardFromString("""
                               #**
                               o*#""");

        var stream = model.getBoardSize().positions().stream();
        var speelboard = new BoardMaxScore(model.getSpeelbord());

        var candy = model.getBoardSize().positions().stream()
                .limit(1)
                .map(pos->model.getSpeelbord().getCellAt(pos))
                .toList().getFirst();

        var result = speelboard.firstTwoHaveCandy(candy,stream);

        assert(!result);
    }
//    firstTwoHaveCandy

//    horizontalStartingPositions
    @Test
    public void newBoard_findHSP_returnControle1(){
        CandycrushModel model = createBoardFromString("""
                **#
                @**
                o*#""");
        var board = new BoardMaxScore(model.getSpeelbord());

        var result = board.horizontalStartingPositions().toList();
        var controle1 = new ArrayList<Position>();
        controle1.add(new Position(0,0,model.getBoardSize()));
        controle1.add(new Position(0,2,model.getBoardSize()));
        controle1.add(new Position(1,0,model.getBoardSize()));
        controle1.add(new Position(1,1,model.getBoardSize()));
        controle1.add(new Position(2,0,model.getBoardSize()));
        controle1.add(new Position(2,1,model.getBoardSize()));
        controle1.add(new Position(2,2,model.getBoardSize()));

        assert(result.equals(controle1));
    }
    @Test
    public void newBoard_findHSP_returnControle2(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                *o@#""");
        var board = new BoardMaxScore(model.getSpeelbord());

        var result = board.horizontalStartingPositions().toList();
        var controle2 = new ArrayList<Position>();
        controle2.add(new Position(0,0,model.getBoardSize()));
        controle2.add(new Position(0,1,model.getBoardSize()));

        controle2.add(new Position(1,0,model.getBoardSize()));
        controle2.add(new Position(1,1,model.getBoardSize()));
        controle2.add(new Position(1,2,model.getBoardSize()));

        controle2.add(new Position(2,0,model.getBoardSize()));
        controle2.add(new Position(2,1,model.getBoardSize()));
        controle2.add(new Position(2,2,model.getBoardSize()));
        controle2.add(new Position(2,3,model.getBoardSize()));

        controle2.add(new Position(3,0,model.getBoardSize()));
        controle2.add(new Position(3,1,model.getBoardSize()));
        controle2.add(new Position(3,2,model.getBoardSize()));
        controle2.add(new Position(3,3,model.getBoardSize()));
        assert(result.equals(controle2));
    }
//    horizontalStartingPositions

//    verticalStartingPositions
    @Test
    public void newBoard_findVSP_returnControle1(){
        CandycrushModel model = createBoardFromString("""
                    **#
                    @**
                    o*#""");
        var board = new BoardMaxScore(model.getSpeelbord());

        var result = board.verticalStartingPositions().toList();
        var controle1 = new ArrayList<Position>();
        controle1.add(new Position(0,0,model.getBoardSize()));
        controle1.add(new Position(0,1,model.getBoardSize()));
        controle1.add(new Position(0,2,model.getBoardSize()));

        controle1.add(new Position(1,0,model.getBoardSize()));
        controle1.add(new Position(1,2,model.getBoardSize()));

        controle1.add(new Position(2,0,model.getBoardSize()));
        controle1.add(new Position(2,2,model.getBoardSize()));

        assert(result.equals(controle1));
    }
    @Test
    public void newBoard_findVSP_returnControle2(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                *o@#""");
        var board = new BoardMaxScore(model.getSpeelbord());

        var result = board.verticalStartingPositions().toList();
        var controle2 = new ArrayList<Position>();
        controle2.add(new Position(0,0,model.getBoardSize()));
        controle2.add(new Position(0,1,model.getBoardSize()));
        controle2.add(new Position(0,2,model.getBoardSize()));
        controle2.add(new Position(0,3,model.getBoardSize()));

        controle2.add(new Position(1,0,model.getBoardSize()));
        controle2.add(new Position(1,1,model.getBoardSize()));

        controle2.add(new Position(2,0,model.getBoardSize()));
        controle2.add(new Position(2,1,model.getBoardSize()));
        controle2.add(new Position(2,2,model.getBoardSize()));

        controle2.add(new Position(3,0,model.getBoardSize()));
        controle2.add(new Position(3,1,model.getBoardSize()));
        controle2.add(new Position(3,2,model.getBoardSize()));
        controle2.add(new Position(3,3,model.getBoardSize()));
        assert(result.equals(controle2));
    }
//    verticalStartingPositions

//    longestMatchToRight
    @Test
    public void newModel_longestMatchToRight_returnControle(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        var result = board.longestMatchToRight(new Position(0,1,board.getBoardSize()));
        var controle = new ArrayList<Position>();
        controle.add(new Position(0,1,board.getBoardSize()));
        controle.add(new Position(0,2,board.getBoardSize()));
        controle.add(new Position(0,3,board.getBoardSize()));
        assert(result.equals(controle));

        result = board.longestMatchToRight(new Position(1,0,board.getBoardSize()));
        controle = new ArrayList<>();
        controle.add(new Position(1,0,board.getBoardSize()));
        assert(result.equals(controle));

        result = board.longestMatchToRight(new Position(1,2,board.getBoardSize()));
        controle = new ArrayList<>();
        controle.add(new Position(1,2,board.getBoardSize()));
        controle.add(new Position(1,3,board.getBoardSize()));
        assert(result.equals(controle));
    }
//    longestMatchToRight

//    longestMatchDown
@Test
public void newModel_longestMatchDown_returnControle(){
    CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
    var board = new BoardMaxScore(model.getSpeelbord());
    var result = board.longestMatchDown(new Position(0,3,board.getBoardSize()));
    var controle = new ArrayList<Position>();
    controle.add(new Position(0,3,board.getBoardSize()));
    controle.add(new Position(1,3,board.getBoardSize()));
    controle.add(new Position(2,3,board.getBoardSize()));
    assert(result.equals(controle));

    result = board.longestMatchDown(new Position(1,0,board.getBoardSize()));
    controle = new ArrayList<>();
    controle.add(new Position(1,0,board.getBoardSize()));
    assert(result.equals(controle));

    result = board.longestMatchDown(new Position(2,1,board.getBoardSize()));
    controle = new ArrayList<>();
    controle.add(new Position(2,1,board.getBoardSize()));
    controle.add(new Position(3,1,board.getBoardSize()));
    assert(result.equals(controle));

    result = board.longestMatchDown(new Position(0,1,board.getBoardSize()));
    controle = new ArrayList<>();
    controle.add(new Position(0,1,board.getBoardSize()));
    assert(result.equals(controle));
}
//    longestMatchDown

//    findAllMatches
    @Test
    public void newModel_findAllMatches_returnControle(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        var controle = new HashSet<ArrayList<Position>>();
        var temp = new ArrayList<Position>();
        temp.add(new Position(0,3,board.getBoardSize()));
        temp.add(new Position(1,3,board.getBoardSize()));
        temp.add(new Position(1,3,board.getBoardSize()));
        controle.add(temp);
        temp.clear();
        temp.add(new Position(0,1,board.getBoardSize()));
        temp.add(new Position(0,2,board.getBoardSize()));
        temp.add(new Position(0,3,board.getBoardSize()));
        controle.add(temp);

        var result = board.findAllMatches();
        assert result.equals(controle);
    }
    @Test
    public void newModel2_findAllMatches_returnControle(){
        CandycrushModel model = createBoardFromString("""
                ****
                @#**
                o***
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        var controle = new HashSet<ArrayList<Position>>();
        var temp = new ArrayList<Position>();
        temp.add(new Position(0,2,board.getBoardSize()));
        temp.add(new Position(1,2,board.getBoardSize()));
        temp.add(new Position(1,2,board.getBoardSize()));
        controle.add(temp);
        temp.clear();
        temp.add(new Position(0,3,board.getBoardSize()));
        temp.add(new Position(1,3,board.getBoardSize()));
        temp.add(new Position(1,3,board.getBoardSize()));
        controle.add(temp);
        temp.clear();
        temp.add(new Position(0,0,board.getBoardSize()));
        temp.add(new Position(0,1,board.getBoardSize()));
        temp.add(new Position(0,2,board.getBoardSize()));
        temp.add(new Position(0,3,board.getBoardSize()));
        controle.add(temp);
        temp.clear();
        temp.add(new Position(2,1,board.getBoardSize()));
        temp.add(new Position(2,2,board.getBoardSize()));
        temp.add(new Position(2,3,board.getBoardSize()));
        controle.add(temp);

        var result = board.findAllMatches();
        assert result.equals(controle);
    }
//    clearMatch
    @Test
    public void newModel_clearMatch_returnControle(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        var match = board.longestMatchToRight(new Position(0,1,board.getBoardSize()));

        board.clearMatch(match);

        assert !board.getSpeelbord().board.containsKey(new Position(0,1,board.getBoardSize()));
        assert !board.getSpeelbord().board.containsKey(new Position(0,2,board.getBoardSize()));
        assert !board.getSpeelbord().board.containsKey(new Position(0,3,board.getBoardSize()));
    }
    @Test
    public void newModel_clearMatch2_returnControle(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        var match = board.longestMatchDown(new Position(0,3,board.getBoardSize()));

        board.clearMatch(match);

        assert !board.getSpeelbord().board.containsKey(new Position(0,3,board.getBoardSize()));
        assert !board.getSpeelbord().board.containsKey(new Position(1,3,board.getBoardSize()));
        assert !board.getSpeelbord().board.containsKey(new Position(1,3,board.getBoardSize()));
    }

//    clearMatch

//    fallDownTo
    @Test
    public void newModel_fallDownTo_returnControle(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        var match = board.longestMatchDown(new Position(0,3,board.getBoardSize()));
        board.clearMatch(match);
        board.fallDownTo(new Position(3,3,board.getBoardSize()));

        assert board.getSpeelbord().getCellAt(new Position(0,3,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(1,3,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(2,3,board.getBoardSize())) == null;
    }
    @Test
    public void newModel_fallDownTo2_returnControle(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        var match = board.longestMatchToRight(new Position(3,0,board.getBoardSize()));
        board.clearMatch(match);
        board.fallDownTo(new Position(3,0,board.getBoardSize()));
        board.fallDownTo(new Position(3,1,board.getBoardSize()));

        assert board.getSpeelbord().getCellAt(new Position(0,0,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(0,1,board.getBoardSize())) == null;
    }
//    fallDownTo

//    updateBoard
    @Test
    public void newModel_updateBoard_returnControle(){
        CandycrushModel model = createBoardFromString("""
                o***
                @#**
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        assert board.updateBoard();
        assert board.getScore() == 6;

        assert board.getSpeelbord().getCellAt(new Position(0,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(0,1,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(0,2,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(0,3,board.getBoardSize())) == null;

        assert board.getSpeelbord().getCellAt(new Position(1,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(1,1,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(1,2,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(1,3,board.getBoardSize())) == null;

        assert board.getSpeelbord().getCellAt(new Position(2,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(2,1,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(2,2,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(2,3,board.getBoardSize())) == null;

        assert board.getSpeelbord().getCellAt(new Position(3,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(3,1,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(3,2,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(3,3,board.getBoardSize())) != null;

    }
    @Test
    public void newModel2_updateBoard_returnControle() {
        CandycrushModel model = createBoardFromString("""
                o*@*
                @***
                o*#*
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        assert board.updateBoard();
        assert board.getScore() == 10;
        assert board.getSpeelbord().getCellAt(new Position(0,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(0,1,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(0,2,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(0,3,board.getBoardSize())) == null;

        assert board.getSpeelbord().getCellAt(new Position(1,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(1,1,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(1,2,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(1,3,board.getBoardSize())) == null;

        assert board.getSpeelbord().getCellAt(new Position(2,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(2,1,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(2,2,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(2,3,board.getBoardSize())) == null;

        assert board.getSpeelbord().getCellAt(new Position(3,0,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(3,1,board.getBoardSize())) == null;
        assert board.getSpeelbord().getCellAt(new Position(3,2,board.getBoardSize())) != null;
        assert board.getSpeelbord().getCellAt(new Position(3,3,board.getBoardSize())) != null;

    }
    @Test
    public void newModel3_updateBoard_returnControle() {
        CandycrushModel model = createBoardFromString("""
                o*@*
                @#@*
                o*#@
                **@#""");
        var board = new BoardMaxScore(model.getSpeelbord());
        assert !board.updateBoard();
        }

    @Test
    public void newModel_updateBoardMetL_returnScore5(){
        CandycrushModel model = createBoardFromString("""
                ***
                @#*
                o@*
                *o@""");
        var board = new BoardMaxScore(model.getSpeelbord());
        board.updateBoard();
        assert board.getScore() == 5;
    }
//    updateBoard

//    maximizeScore
    @Test
    public void newModel_maxScore_returnControle(){
        CandycrushModel model1 = createBoardFromString("""
                               @@o#
                               o*#o
                               @@**
                               *#@@""");
        var board = new BoardMaxScore(model1.getSpeelbord());
        var solution = board.maximizeScore();
        printSolution(solution);

        var controle = new ArrayList<ArrayList<Position>>();

        var temp = new ArrayList<Position>();
        temp.add(new Position(2,1,board.getBoardSize()));
        temp.add(new Position(3,1,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(1,0,board.getBoardSize()));
        temp.add(new Position(1,1,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(1,3,board.getBoardSize()));
        temp.add(new Position(2,3,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(2,1,board.getBoardSize()));
        temp.add(new Position(3,1,board.getBoardSize()));
        controle.add(temp);

        assert solution.score() == 16;
        assert solution.switches().equals(controle);
    }
    @Test
    public void newModel2_maxScore_returnControle(){
        CandycrushModel model2 = createBoardFromString("""
                                   #oo##
                                   #@o@@
                                   *##o@
                                   @@*@o
                                   **#*o""");
        var board = new BoardMaxScore(model2.getSpeelbord());
        var solution = board.maximizeScore();
        printSolution(solution);

        var controle = new ArrayList<ArrayList<Position>>();

        var temp = new ArrayList<Position>();
        temp.add(new Position(2,0,board.getBoardSize()));
        temp.add(new Position(2,1,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(3,3,board.getBoardSize()));
        temp.add(new Position(3,4,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(1,2,board.getBoardSize()));
        temp.add(new Position(1,3,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(2,2,board.getBoardSize()));
        temp.add(new Position(3,2,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(2,1,board.getBoardSize()));
        temp.add(new Position(2,2,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(4,2,board.getBoardSize()));
        temp.add(new Position(4,3,board.getBoardSize()));
        controle.add(temp);

        temp = new ArrayList<>();
        temp.add(new Position(4,3,board.getBoardSize()));
        temp.add(new Position(4,4,board.getBoardSize()));
        controle.add(temp);

        assert solution.score() == 23;
        assert solution.switches().equals(controle);
    }
    @Test
    public void newModel3_maxScore_returnControle(){
        CandycrushModel model3 = createBoardFromString("""
                                   #@#oo@
                                   @**@**
                                   o##@#o
                                   @#oo#@
                                   @*@**@
                                   *#@##*""");
        var board = new BoardMaxScore(model3.getSpeelbord());
        var solution = board.maximizeScore();
        printSolution(solution);

        assert solution.switches().size() == 9;
        assert solution.score() == 33;
    }


}


