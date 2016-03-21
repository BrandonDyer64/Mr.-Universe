package dyer.brandon.mr.universe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

import dyer.brandon.mr.universe.MyGdxGame;
import dyer.brandon.mr.universe.screens.particles.Galaxy;

/**
 * Created by Brandon Dyer on 3/20/16.
 */
public class GameScreen implements Screen, InputProcessor {

    public static LinkedList<Particle> particles = new LinkedList<Particle>();
    public static LinkedList<Particle> partToAdd = new LinkedList<Particle>();
    public static LinkedList<Particle> partToSub = new LinkedList<Particle>();

    public SpriteBatch batch;
    public static float speed = 5f;

    public static int fingersDown = 0;
    public static int iteration = 0;

    public static Particle cameraFollow = null;

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        particles.add(new Galaxy(0, 0, 300));
    }

    @Override
    public void render(float delta) {
        iteration++;
        {
            if (Gdx.input.isTouched() && !Gdx.input.justTouched() && fingersDown < 2) {
                MyGdxGame.camera.position.x -= MyGdxGame.getDeltaX(MyGdxGame.camera) * 4;
                MyGdxGame.camera.position.y += MyGdxGame.getDeltaY(MyGdxGame.camera) * 4;
                cameraFollow = null;
            }
        }
        if (cameraFollow != null) {
            MyGdxGame.setCameraLocation(cameraFollow.position.x, cameraFollow.position.y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            if (cameraFollow == null) {
                float close = 2000000000;
                for (Particle p : particles) {
                    float dx = MyGdxGame.camera.position.x - p.position.x;
                    float dy = MyGdxGame.camera.position.y - p.position.y;
                    float distance = (float) Math.sqrt(dx * dx + dy * dy);
                    if (distance < close) {
                        close = distance;
                        cameraFollow = p;
                    }
                }
            } else {
                cameraFollow = null;
            }

        }
        MyGdxGame.updateCamera();
        MyGdxGame.camera.update();
        batch.setProjectionMatrix(MyGdxGame.camera.combined);
        batch.begin();
        {
            for (Particle p : particles) {
                p.render(batch, delta * speed);
            }
            for (Particle p : partToAdd) {
                particles.add(p);
            }
            partToAdd.clear();
            for (Particle p : partToSub) {
                particles.remove(p);
            }
            partToSub.clear();
        }
        batch.end();
    }

    public static Particle addParticle(Particle p) {
        partToAdd.add(p);
        System.out.println("Added particle");
        return p;
    }

    public static Particle subtractParticle(Particle p) {
        partToSub.add(p);
        System.out.println("Removed particle");
        return p;
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        fingersDown++;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        fingersDown--;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        MyGdxGame.cameraZoom *= Math.pow(2, amount);
        return false;
    }
}
