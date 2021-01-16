package io.github.codephoria.starfishcollectorsharks;

public class StarfishGame extends BaseGame {
    @Override
    public void create() {
        setActiveScreen(new MenuScreen());
    }
}
