package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.candies.*;

public sealed interface Candy permits NormalCandy, BaseDestroyerCandy, ExplosiveSugar, ExtraSweet, MoreCandies {
    abstract public Candy copy();
}
