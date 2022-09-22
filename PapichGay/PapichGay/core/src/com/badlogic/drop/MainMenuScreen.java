package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MainMenuScreen implements Screen {


    final Papich game;
    TextureRegion backGround;
    OrthographicCamera camera;
    Music qqMusik;

    public MainMenuScreen(final Papich gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        qqMusik = Gdx.audio.newMusic(Gdx.files.internal("fonMenuMus1.mp3"));
        qqMusik.play();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        backGround = new TextureRegion(new Texture("BackGround.jpg"));
        game.batch.begin();
        game.font.draw(game.batch, backGround.toString(),0,0);
        game.font.draw(game.batch, "Papichu zdorovya drugim zymla puhom", 315, 260);
        game.font.draw(game.batch, "Now click on the screen to play", 315, 240);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
