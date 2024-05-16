package be.kuleuven.candycrush.candies;

import be.kuleuven.candycrush.model.Candy;

public record BaseDestroyerCandy() implements Candy {
    @Override
    public Candy copy() {
        return new BaseDestroyerCandy();
    }
}
