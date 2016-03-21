package dyer.brandon.mr.universe.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Brandon Dyer on 3/20/16.
 */
public class Particle {

    public static final double _G = 6.754 * 10E-4;
    public static final Texture texture = new Texture("particle.png");

    public Vector2 position, velocity;
    public float mass;
    public float radius;

    public static Random random = new Random();

    public Particle(float x, float y, float mass, float radius, Vector2 velocity) {
        position = new Vector2(x, y);
        this.mass = mass;
        this.radius = radius;
        this.velocity = velocity;
    }

    public void render(SpriteBatch batch, float delta) {
        move(delta);
        batch.draw(texture, position.x - radius * 9, position.y - radius * 9, radius * 18, radius * 18);
    }

    protected void move(float delta) {
        Vector2 netForce = getNetForce();
        velocity.x += netForce.x / mass;
        velocity.y += netForce.y / mass;
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
    }

    public Vector2 getNetForce() {
        Vector2 netForce = new Vector2(0, 0);
        for (Particle p : GameScreen.particles) {
            if (p != this) {
                double direction = Math.atan2(p.position.y - position.y, p.position.x - position.x);
                double distX = p.position.x - position.x;
                double distY = p.position.y - position.y;
                double distance = Math.sqrt(distX * distX + distY * distY);
                double force = _G * mass * p.mass / (distance == 0 ? 0.0000001f : distance);
                netForce.x += (float) (Math.cos(direction) * force);
                netForce.y += (float) (Math.sin(direction) * force);
            }
        }
        return netForce;
    }

}
