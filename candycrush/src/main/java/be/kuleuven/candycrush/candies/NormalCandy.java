package be.kuleuven.candycrush.candies;

import be.kuleuven.candycrush.model.Candy;

public record NormalCandy(int color) implements Candy {
    public NormalCandy{
        if(color>3) throw new IllegalArgumentException("Color mag niet groter zijn als 3.");
        if(color<0) throw new IllegalArgumentException("Color mag niet negatief zijn.");
    }
    @Override
    public Candy copy() {
        return new NormalCandy(this.color);
    }
}
