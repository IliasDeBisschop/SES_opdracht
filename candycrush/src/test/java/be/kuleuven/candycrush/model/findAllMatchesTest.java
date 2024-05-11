package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.candies.NormalCandy;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static be.kuleuven.candycrush.model.CandycrushModel.createBoardFromString;

public class findAllMatchesTest {
    @Test
    public void lijstMet2NormalCandy2_controleOpNormalCandy2_firstTwoHaveCandy_returnTrue(){
        CandycrushModel model = createBoardFromString("""
                               ##*
                               o*#""");
        var stream = model.getBoardSize().positions().stream();
        var candy = new NormalCandy(2);
        var result = model.firstTwoHaveCandy(candy,stream);

        assert(result);
    }
    @Test
    public void lijstMet2NormalCandy2_controleOpNormalCandy1_firstTwoHaveCandy_returnFalse(){
        CandycrushModel model = createBoardFromString("""
                               ##*
                               o*#""");
        var stream = model.getBoardSize().positions().stream();
        var candy = new NormalCandy(1);
        var result = model.firstTwoHaveCandy(candy,stream);

        assert(!result);
    }
    @Test
    public void lijstMet1NormalCandy2_controleOpNormalCandy1_firstTwoHaveCandy_returnFalse(){
        CandycrushModel model = createBoardFromString("""
                               #""");
        var stream = model.getBoardSize().positions().stream();
        var candy = new NormalCandy(1);
        var result = model.firstTwoHaveCandy(candy,stream);

        assert(!result);
    }
    @Test
    public void lijstMet2NormalCandy2_controleOpEerstCandy_firstTwoHaveCandy_returnTrue(){
        CandycrushModel model = createBoardFromString("""
                               ##*
                               o*#""");
        var stream = model.getBoardSize().positions().stream();

        var candy = model.getBoardSize().positions().stream()
                .limit(1)
                .map(pos->model.getSpeelbord().getCellAt(pos))
                .toList().getFirst();

        var result = model.firstTwoHaveCandy(candy,stream);

        assert(result);
    }
    @Test
    public void lijstMet2VerschillendeNormalCandy_controleOpEerstCandy_firstTwoHaveCandy_returnFalse(){
        CandycrushModel model = createBoardFromString("""
                               #**
                               o*#""");
        var stream = model.getBoardSize().positions().stream();

        var candy = model.getBoardSize().positions().stream()
                .limit(1)
                .map(pos->model.getSpeelbord().getCellAt(pos))
                .toList().getFirst();

        var result = model.firstTwoHaveCandy(candy,stream);

        assert(!result);
    }
}
