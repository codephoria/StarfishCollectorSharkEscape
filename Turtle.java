package io.github.codephoria.starfishcollectorsharks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Turtle extends BaseActor {
    boolean isAlive;

    public Turtle(float x, float y, Stage s) {
        super(x, y, s);

        String[] filenames = {"turtle-1.png", "turtle-2.png", "turtle-3.png", "turtle-4.png", "turtle-5.png", "turtle-6.png"};
        loadAnimationFromFiles(filenames, 0.1f, true);

        setAcceleration(400);
        setMaxSpeed(100);
        setDeceleration(400);

        setBoundaryPolygon(8);

        isAlive = true;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            accelerateAtAngle(180);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            accelerateAtAngle(0);
        }
        if (Gdx.input.isKeyPressed(Keys.UP)){
            accelerateAtAngle(90);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)){
            accelerateAtAngle(270);
        }

        applyPhysics(delta);

        setAnimationPaused(!isMoving());

        if (getSpeed() > 0){
            setRotation(getMotionAngle());
        }

        boundToWorld();

        if (isAlive){
            alignCamera();
        }

    }

    public void dies(){
        isAlive = false;
        clearActions();
        addAction(Actions.fadeOut(1));
        addAction(Actions.after(Actions.removeActor()));
    }
}
