package gwt.g2d.client.demo;

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;
import gwt.g2d.client.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;

/**
 * Animation Demo - Converted to GWT from <a
 * href="http://blogoscoped.com/files/canvas-test.html">
 * http://blogoscoped.com/files/canvas-test.html</a>
 * 
 * @author hao1300@gmail.com
 */
public class AnimationDemo extends AbstractDemo {
	private static final Vector2 MIN = new Vector2(50, 50);
	private static final Vector2 MAX = new Vector2(550, 550);
	private static final int MAX_SPRITES = 80;

	private final List<Sprite> sprites = new ArrayList<Sprite>();

	public AnimationDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}

	@Override
	public void initialize() {
		getParentContainer().clear();
		getParentContainer().add(getPrimarySurface());
		getParentContainer().add(new InlineLabel("Reference: "));
		getParentContainer().add(new Anchor(
				"http://blogoscoped.com/files/canvas-test.html",
				"http://blogoscoped.com/files/canvas-test.html"));
		sprites.clear();
		getPrimarySurface().clear();
		sprites.add(new Sprite());
	}

	@Override
	public void update() {
		for (Sprite sprite : sprites) {
			sprite.changeAppearance();
			sprite.move();
			sprite.draw(getPrimarySurface());
		}
		if (sprites.size() <= MAX_SPRITES && Random.nextInt(1000) <= 1) {
			sprites.add(new Sprite());
		}
	}

	private static int getRandomInt(double min, double max) {
		return (int) (((max + 1 - min) * Random.nextDouble()) + min);
	}

	private static class Sprite {
		private static final int COLOR_MIN = 100, COLOR_MAX = 230;
		private static final Vector2 SPEED_MAX = new Vector2(6);
		private Vector2 pos, speed;
		private final int[] color = new int[3];
		private final int[] colorSpeed = {1, 1, 1};

		public Sprite() {
			pos = new Vector2(MAX).scale(.5).add(
					new Vector2(getRandomInt(-50, 50), getRandomInt(-50, 50)));
			speed = new Vector2(SPEED_MAX);
			for (int i = 0; i < 3; i++) {
				color[i] = getRandomInt(COLOR_MIN, COLOR_MAX);
			}
		}

		public void move() {
			pos.mutableAdd(speed);
			if (pos.getX() < MIN.getX()) {
				pos.setX(MIN.getX());
				speed.setX(getRandomSpeed(SPEED_MAX.getX()));
			} else if (pos.getX() > MAX.getX()) {
				pos.setX(MAX.getX());
				speed.setX(-getRandomSpeed(SPEED_MAX.getX()));
			}
			if (pos.getY() < MIN.getY()) {
				pos.setY(MIN.getY());
				speed.setY(getRandomSpeed(SPEED_MAX.getY()));
			} else if (pos.getY() > MAX.getY()) {
				pos.setY(MAX.getY());
				speed.setY(-getRandomSpeed(SPEED_MAX.getY()));
			}
		}

		private void changeAppearance() {
			for (int i = 0; i < color.length; i++) {
				color[i] += colorSpeed[i];
				if (color[i] < COLOR_MIN) {
					color[i] = COLOR_MIN + getRandomInt(0, 10);
					colorSpeed[i] *= -1;
				} else if (color[i] > COLOR_MAX) {
					color[i] = COLOR_MAX;
					colorSpeed[i] *= -1;
				}
			}
		}

		public void draw(Surface surface) {
			int padding = 2;
			int fluff = 10;
			int size = 10;
			surface.setFillStyle(new Color(color[0], color[1], color[2], .5));
			for (int x = -padding; x <= padding; x++) {
				for (int y = -padding; y <= padding; y++) {
					surface.fillShape(new ShapeBuilder()
							.drawCircle(pos.add(new Vector2(
										x * getRandomInt(1, fluff), 
										y * getRandomInt(1, fluff))), 
										size)
							.build());
				}
			}
		}

		private int getRandomSpeed(double maxSpeed) {
			return getRandomInt(maxSpeed, maxSpeed * 1.5);
		}
	}
}
