package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.candies.NormalCandy;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashSet;

import static be.kuleuven.candycrush.model.CandycrushModel.createBoardFromString;

public class BoardMaxScoreTest {
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
}
