package io.github.codephoria.starfishcollectorsharks;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shark extends BaseActor {
    boolean isGoingLeft;
    float basicAcceleration;

    public Shark(float x, float y, Stage s, float speed) {
        super(x, y, s);
        loadTexture("sharky.png");
        setMaxSpeed(1000);
        basicAcceleration = speed;
        setAcceleration(basicAcceleration);
        setDeceleration(300);
        setBoundaryPolygon(8);
        isGoingLeft = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (!isGoingLeft && (this.getX() < (worldBounds.width/2 + this.getWidth()))) {
            setAcceleration(basicAcceleration);
            accelerateAtAngle(0);
        } else if (!isGoingLeft) {
            decelerate(delta);
            if (getSpeed() <= 0){
                setAcceleration(basicAcceleration);
                accelerateAtAngle(180);
                isGoingLeft = true;
                setAnimation(loadTexture("sharky-l.png"));
            }

        } else if (isGoingLeft && this.getX() > (250 + this.getWidth()) ){
            accelerateAtAngle(180);
        } else {
            decelerate(delta);
            if (getSpeed() <= 0){
                setAcceleration(basicAcceleration);
                accelerateAtAngle(0);
                isGoingLeft = false;
                setAnimation(loadTexture("sharky.png"));

            }

        }
        applyPhysics(delta);
        boundToWorld();

    }
}
