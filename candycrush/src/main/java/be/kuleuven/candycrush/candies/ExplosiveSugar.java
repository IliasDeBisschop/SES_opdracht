package be.kuleuven.candycrush.candies;

import be.kuleuven.candycrush.model.Candy;

public record ExplosiveSugar() implements Candy {
    @Override
    public Candy copy() {
        return new ExplosiveSugar();
    }
}
