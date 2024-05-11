package be.kuleuven.candycrush.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WalkTest {
    //walkLeft
    @Test
    public void p0x0B5x5_walkLeft_return0x0(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,0,boardSize);

        var result = pos.walkLeft().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,0,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p0x2B5x5_walkLeft_return0x2en0x1en0x0(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,2,boardSize);

        var result = pos.walkLeft().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,2,boardSize));
        controle.add(new Position(0,1,boardSize));
        controle.add(new Position(0,0,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p0x4B5x5_walkLeft_return0x4en0x3en0x2en0x1en0x0(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,4,boardSize);

        var result = pos.walkLeft().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,4,boardSize));
        controle.add(new Position(0,3,boardSize));
        controle.add(new Position(0,2,boardSize));
        controle.add(new Position(0,1,boardSize));
        controle.add(new Position(0,0,boardSize));

        assert(result.equals(controle));
    }
    //walkLeft

    //walkRight
    @Test
    public void p0x4B5x5_walkRight_return0x4(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,4,boardSize);

        var result = pos.walkRight().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,4,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p0x2B5x5_walkRight_return0x2en0x3en0x4(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,2,boardSize);

        var result = pos.walkRight().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,2,boardSize));
        controle.add(new Position(0,3,boardSize));
        controle.add(new Position(0,4,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p0x0B5x5_walkRight_return0x4en0x3en0x2en0x1en0x0(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,0,boardSize);

        var result = pos.walkRight().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,0,boardSize));
        controle.add(new Position(0,1,boardSize));
        controle.add(new Position(0,2,boardSize));
        controle.add(new Position(0,3,boardSize));
        controle.add(new Position(0,4,boardSize));

        assert(result.equals(controle));
    }
    //walkRight

    //walkUp
    @Test
    public void p0x4B5x5_walkUp_return0x4(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,4,boardSize);

        var result = pos.walkUp().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,4,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p2x2B5x5_walkUp_return2x2en1x2en0x2(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(2,2,boardSize);

        var result = pos.walkUp().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(2,2,boardSize));
        controle.add(new Position(1,2,boardSize));
        controle.add(new Position(0,2,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p4x0B5x5_walkUp_return4x0en3x0en2x0en1x0en0x0(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(4,0,boardSize);

        var result = pos.walkUp().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(4,0,boardSize));
        controle.add(new Position(3,0,boardSize));
        controle.add(new Position(2,0,boardSize));
        controle.add(new Position(1,0,boardSize));
        controle.add(new Position(0,0,boardSize));

        assert(result.equals(controle));
    }
    //walkUp

    //walkDown
    @Test
    public void p4x4B5x5_walkDown_return4x4(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(4,4,boardSize);

        var result = pos.walkDown().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(4,4,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p2x2B5x5_walkDown_return2x2en3x2en4x2(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(2,2,boardSize);

        var result = pos.walkDown().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(2,2,boardSize));
        controle.add(new Position(3,2,boardSize));
        controle.add(new Position(4,2,boardSize));

        assert(result.equals(controle));
    }
    @Test
    public void p0x0B5x5_walkDown_return4x0en3x0en2x0en1x0en0x0(){
        var boardSize =new BoardSize(5,5);
        var pos = new Position(0,0,boardSize);

        var result = pos.walkDown().toList();

        var controle = new ArrayList<Position>();
        controle.add(new Position(0,0,boardSize));
        controle.add(new Position(1,0,boardSize));
        controle.add(new Position(2,0,boardSize));
        controle.add(new Position(3,0,boardSize));
        controle.add(new Position(4,0,boardSize));

        assert(result.equals(controle));
    }
    //walkDown
}
