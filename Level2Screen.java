package io.github.codephoria.starfishcollectorsharks;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Level2Screen extends BaseScreen {

    private Turtle turtle;
    private Starfish starfish;
    private boolean gameActive;
    private boolean win;

    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0, 0, mainStage);
        ocean.loadTexture("water-border.jpg");
        ocean.setSize(1200, 900);

        BaseActor.setWorldBounds(ocean);

        starfish = new Starfish(600,100,mainStage);
        starfish.centerAtPosition(600, 100);

        win = false;
        gameActive = true;

        new Shark(500,200,mainStage, 300);
        new Shark(100,400,mainStage, 150);
        new Shark(200,600,mainStage, 250);

        turtle = new Turtle(100, 700, mainStage);
    }

    @Override
    public void update(float dt) {

        for (BaseActor sharkActor : BaseActor.getList(mainStage, "io.github.codephoria.starfishcollectorsharks.Shark")){
            Shark shark = (Shark) sharkActor;
            if (turtle.overlaps(shark) && gameActive){
                turtle.dies();
                gameActive = false;
                BaseActor gameOverMessage = new BaseActor(0, 0, uiStage);
                gameOverMessage.loadTexture("game-over.png");
                gameOverMessage.centerAtPosition(400,300);
                gameOverMessage.setOpacity(0);
                gameOverMessage.addAction(Actions.delay(1));
                gameOverMessage.addAction(Actions.after(Actions.fadeIn(1)));
            }
        }

        if (turtle.overlaps(starfish) && !starfish.isCollected() && gameActive){
            starfish.collect();

            Whirlpool whirl = new Whirlpool(0,0,mainStage);
            whirl.centerAtActor(starfish);
            whirl.setOpacity(0.25f);
        }

        if (starfish.isCollected() && !win && gameActive){
            gameActive = false;
            BaseActor youWinMessage = new BaseActor(0,0, uiStage);
            youWinMessage.loadTexture("you-win.png");
            youWinMessage.centerAtPosition(400, 300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction(Actions.delay(1));
            youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
        }

    }
}
