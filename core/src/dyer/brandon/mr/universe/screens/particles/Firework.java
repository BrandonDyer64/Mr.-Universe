package dyer.brandon.mr.universe.screens.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import dyer.brandon.mr.universe.screens.GameScreen;
import dyer.brandon.mr.universe.screens.Particle;

/**
 * Created by Brandon Dyer on 3/21/16.
 */
public class Firework extends Particle {

    public final int particles;
    public final float delay;

    private float time = 0;

    public Firework(float x, float y, float launchForce, int particles, float delay) {
        super(x, y, 1, 1, new Vector2(0, 0));
        this.velocity = getLaunchVelocity(launchForce);
        this.particles = particles;
        this.delay = delay;
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        time += delta;
        if (time > delay) {
            for (int i = 0; i < particles; i++) {
                GameScreen.addParticle(new Particle(position.x + i / 16f, position.y, 1, 1, new Vector2(random.nextInt(7) - 3, random.nextInt(7) - 3)));
            }
            time = 0;
            GameScreen.subtractParticle(this);
        }
        move(delta);
        batch.draw(texture, position.x - radius * 9, position.y - radius * 9, radius * 18, radius * 18);
    }

    private Vector2 getLaunchVelocity(float mult) {
        Vector2 force = getNetForce();
        force.x *= -mult;
        force.y *= -mult;
        return force;
    }
}
