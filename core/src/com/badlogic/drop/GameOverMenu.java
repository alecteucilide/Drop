package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.awt.event.ActionListener;

public class GameOverMenu implements Screen {

    final Drop game;
    private int dropsGathered;
    private Texture blueBackgroundImage;
    private Stage stage;
    private TextButton button;
    private TextButton.TextButtonStyle textButtonStyle;
    private BitmapFont font;

    OrthographicCamera camera;

    public GameOverMenu(final Drop game, int dropsGathered) {
        this.game = game;
        this.dropsGathered = dropsGathered;
        game.setGameOverMenu(this);

        blueBackgroundImage = new Texture(Gdx.files.internal("blueBackground.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        stage = new Stage(new ExtendViewport(800, 480));


        /*Table table = new Table();
        table.setFillParent(true);
        table.center().center();
        stage.addActor(table);*/

        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        button = new TextButton("Click here to play again", textButtonStyle);
        button.setBounds(380, 250, 50, 50);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        stage.addActor(button);
        /*table.add(button);
        table.row();*/

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(blueBackgroundImage, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Game over! ", 100, 150);
        game.font.draw(game.batch, "Drops gathered : "+String.valueOf(dropsGathered), 100, 100);
        game.batch.end();

        stage.draw();
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
