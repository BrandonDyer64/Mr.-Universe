package dyer.brandon.mr.universe.screens.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import dyer.brandon.mr.universe.screens.Particle;

/**
 * Created by Brandon Dyer on 3/21/16.
 */
public class Star extends Particle {

    public static final Texture texture = new Texture("star-1.png");
    public static final Texture texCloud = new Texture("cloud.png");

    public Star(float x, float y) {
        this(x, y, 1000, 100, new Vector2(0, 0));
    }

    public Star(float x, float y, float mass, float radius, Vector2 velocity) {
        super(x, y, mass, radius, velocity);
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        move(delta);
        batch.draw(texCloud, position.x - radius * 18 * 5, position.y - radius * 18 * 5, radius * 36 * 5, radius * 36 * 5);
        batch.draw(Particle.texture, position.x - radius * 18, position.y - radius * 18, radius * 36, radius * 36);
        batch.draw(texture, position.x - radius, position.y - radius, radius * 2, radius * 2);
        System.out.println(velocity.x);
    }
}
