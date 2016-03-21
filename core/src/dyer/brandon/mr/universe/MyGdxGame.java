package dyer.brandon.mr.universe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import dyer.brandon.mr.universe.screens.GameScreen;

public class MyGdxGame extends Game {

    public static final int WIDTH = 16, HEIGHT = 16;
    public static OrthographicCamera camera;
    public static float cameraZoom = 1.0f;

    @Override
    public void create() {
        setScreen(new GameScreen());
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        resizeStatic(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    public static Screen setTheScreen(Screen screen) {
        ((Game) Gdx.app.getApplicationListener()).setScreen(screen);
        return screen;
    }

    public static void setCameraLocation(float x, float y) {
        camera.position.x = x;
        camera.position.y = y;
        resizeStatic(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public static void updateCamera() {
        resizeStatic(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Gets the width of a camera with specified height
     *
     * @param camHeight The height of the camera.
     * @return The width of the camera.
     */
    public static int getCamWidth(int camHeight) {
        // cam.height / cam.width = width / height
        // cam.width = (width * cam.height) / height
        int camWidth = (Gdx.graphics.getWidth() * camHeight) / Gdx.graphics.getHeight();
        return camWidth;
    }

    /**
     * Gets the height of a camera with specified height
     *
     * @param camWidth The width of the camera.
     * @return The height of the camera.
     */
    public static int getCamHeight(int camWidth) {
        // cam.height / cam.width = width / height
        // cam.width = (width * cam.height) / height
        int camHeight = (Gdx.graphics.getHeight() * camWidth) / Gdx.graphics.getWidth();
        return camHeight;
    }

    public static void resizeStatic(int width, int height) {
        if ((float) WIDTH / HEIGHT < (float) width / height) {
            camera.viewportHeight = HEIGHT * cameraZoom;
            camera.viewportWidth = getCamWidth(HEIGHT) * cameraZoom;
        } else {
            camera.viewportHeight = getCamHeight(WIDTH) * cameraZoom;
            camera.viewportWidth = WIDTH * cameraZoom;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        resizeStatic(width, height);
    }

    public static float getX(Camera camera) {
        return camera.position.x + (Gdx.input.getX() - Gdx.graphics.getWidth() / 2) * (camera.viewportWidth / Gdx.graphics.getWidth());
    }

    public static float getY(Camera camera) {
        return camera.position.y + (Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight() / 2)
                * (camera.viewportHeight / Gdx.graphics.getHeight());
    }

    public static float getDeltaX(Camera camera) {
        return Gdx.input.getDeltaX() * (camera.viewportWidth / Gdx.graphics.getWidth());
    }

    public static float getDeltaY(Camera camera) {
        return Gdx.input.getDeltaY() * (camera.viewportHeight / Gdx.graphics.getHeight());
    }

}
