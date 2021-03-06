package io.github.codephoria.starfishcollectorsharks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen implements Screen {

    protected Stage mainStage;
    protected Stage uiStage;

    public BaseScreen(){
        mainStage = new Stage();
        uiStage = new Stage();
        initialize();
    }

    public abstract void initialize();

    public abstract void update(float dt);

    @Override
    public void render(float delta) {
        // act method
        uiStage.act(delta);
        mainStage.act(delta);

        // defined by user
        update(delta);

        // clear screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
        uiStage.draw();
    }

    //methods required by Screen interface:

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
