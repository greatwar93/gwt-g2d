/*
 * Copyright 2009 Hao Nguyen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwt.g2d.client.demo;

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.graphics.DirectShapeRenderer;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Panel;

/**
 * Animation Demo - Converted to GWT from <a
 * href="http://blogoscoped.com/files/canvas-test.html">
 * http://blogoscoped.com/files/canvas-test.html</a>
 * 
 * @author hao1300@gmail.com
 */
public class AnimationDemo extends ReferenceDemo {
	private static final Vector2 MIN = new Vector2(50, 50);
	private static final Vector2 MAX = new Vector2(550, 550);
	private static final int MAX_SPRITES = 80;

	private final List<Sprite> sprites = new ArrayList<Sprite>();
	private DirectShapeRenderer shapeRenderer;

	public AnimationDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer,
				"http://blogoscoped.com/files/canvas-test.html");
	}

	@Override
	public void initialize() {
		super.initialize();
		shapeRenderer = new DirectShapeRenderer(getPrimarySurface());
		sprites.clear();
		sprites.add(new Sprite());
	}

	@Override
	public void update() {
		for (Sprite sprite : sprites) {
			sprite.changeAppearance();
			sprite.move();
			sprite.draw(getPrimarySurface(), shapeRenderer);
		}
		if (sprites.size() <= MAX_SPRITES && Random.nextInt(1000) <= 1) {
			sprites.add(new Sprite());
		}
	}

	private static final int getRandomInt(double min, double max) {
		return (int) (((max + 1 - min) * Random.nextDouble()) + min);
	}

	private static class Sprite {
		private static final int COLOR_MIN = 100, COLOR_MAX = 230;
		private static final Vector2 SPEED_MAX = new Vector2(6);
		private final Vector2 pos, speed;
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

		public final void move() {
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

		private final void changeAppearance() {
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

		public final void draw(Surface surface, DirectShapeRenderer shapeRenderer) {
			int padding = 2;
			int fluff = 10;
			int size = 10;
			surface.setFillStyle(new Color(color[0], color[1], color[2], .5));
			for (int x = -padding; x <= padding; x++) {
				for (int y = -padding; y <= padding; y++) {
					shapeRenderer.beginPath()
							.drawCircle(pos.add(new Vector2(
										x * getRandomInt(1, fluff), 
										y * getRandomInt(1, fluff))), 
										size)
							.closePath()
							.fill();
				}
			}
		}

		private final int getRandomSpeed(double maxSpeed) {
			return getRandomInt(maxSpeed, maxSpeed * 1.5);
		}
	}
}
