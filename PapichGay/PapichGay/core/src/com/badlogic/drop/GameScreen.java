package com.badlogic.drop;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;
import java.util.Random;


public class GameScreen implements Screen {final Papich game;

    Texture ptsImage;
    Texture LovechImage;
    Sound dropSound;
    TextureRegion backGround;
    Music nnMusik;
    Rectangle xxx;
    OrthographicCamera camera;
    long lastDropTime;
    Array<Rectangle> padalyki;
    int chetchik = 0;


    public GameScreen(final Papich gam) {
        this.game = gam;
        backGround = new TextureRegion(new Texture("BackGround.jpg"), 0, 0, 1280, 720);


        ptsImage = new Texture(Gdx.files.internal("gtc.png"));

        LovechImage = new Texture(Gdx.files.internal("123.png"));


        dropSound = Gdx.audio.newSound(Gdx.files.internal("0drop.wav"));
        nnMusik = Gdx.audio.newMusic(Gdx.files.internal("Fon.mp3"));
        nnMusik.setLooping(true);


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);


        xxx = new Rectangle();

        xxx.x = 1280 / 2 - 64 / 2;

        xxx.y = 20;

        xxx.width = 64;
        xxx.height = 64;


        padalyki = new Array<Rectangle>();
        spawnRaindrop();

    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 1280 - 64);
        raindrop.y = 720;
        raindrop.width = 64;
        raindrop.height = 64;
        padalyki.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {

        camera.update();


        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backGround, 0,0);
        game.font.draw(game.batch, "points: " + chetchik, 0, 720);
        game.batch.draw(LovechImage, xxx.x, xxx.y);
        for (Rectangle raindrop : padalyki) {
            game.batch.draw(ptsImage, raindrop.x, raindrop.y);
        }
        game.batch.end();


        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            xxx.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            xxx.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            xxx.x += 200 * Gdx.graphics.getDeltaTime();


        if (xxx.x < 0)
            xxx.x = 0;
        if (xxx.x > 1280 - 64)
            xxx.x = 1280 - 64;


        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        Iterator<Rectangle> iter = padalyki.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(chetchik >= 20){
                raindrop.y -= 250 * Gdx.graphics.getDeltaTime();
            }
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(xxx)) {
                chetchik++;
                int min = 0;
                int max = 2;
                int diff = max - min;
                Random random = new Random();
                int i = random.nextInt(diff + 1);
                i += min;
                String f = String.valueOf(i);
                dropSound = Gdx.audio.newSound(Gdx.files.internal(f +"drop.wav"));
                dropSound.play();
                iter.remove();
                if (chetchik ==20){
                    dropSound = Gdx.audio.newSound(Gdx.files.internal("5drop.wav"));
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

        nnMusik.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        ptsImage.dispose();
        LovechImage.dispose();
        dropSound.dispose();
        nnMusik.dispose();
    }
    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }
}
