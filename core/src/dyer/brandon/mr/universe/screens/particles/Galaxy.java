package dyer.brandon.mr.universe.screens.particles;

import com.badlogic.gdx.math.Vector2;

import dyer.brandon.mr.universe.screens.GameScreen;
import dyer.brandon.mr.universe.screens.Particle;

/**
 * Created by Brandon Dyer on 3/21/16.
 */
public class Galaxy extends Particle {
    public Galaxy(float x, float y, int stars) {
        super(x, y, 1, 1, new Vector2(0, 0));
        for (int i = 0; i < stars; i++) {
            GameScreen.addParticle(new Star(x + i, y, random.nextInt(500) + 1000, random.nextInt(50) + 100, new Vector2(random.nextInt(1001) - 500, random.nextInt(1001) - 500)));
        }
        GameScreen.addParticle(new Particle(x, y + 1, 10000000, 100, new Vector2(0, 0)));
    }
}
