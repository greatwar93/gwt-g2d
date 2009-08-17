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
import gwt.g2d.client.graphics.KnownColor;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;
import gwt.g2d.client.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Panel;

/**
 * Particles animation demo.
 * 
 * @author hao1300@gmail.com
 */
public class ParticlesDemo extends AbstractDemo {
	private static final int NUM_PARTICLES = 100;
	private final List<Particle> particles = new ArrayList<Particle>(NUM_PARTICLES);
	
	public ParticlesDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}
	
	@Override
	public void initialize() {
		getParentContainer().add(getPrimarySurface());
		particles.clear();
    for (int i = 0; i < NUM_PARTICLES; i++) {
    	particles.add(new Particle(
    			new Vector2(Random.nextInt(WIDTH), Random.nextInt(HEIGHT)),
    			new Vector2(Math.random(), Math.random()).normalize(),
    			new Color(Random.nextInt(256),
    					Random.nextInt(256),
    					Random.nextInt(256),
    					Math.random())));
    }
	}

	@Override
	public void update() {
		for (Particle p : particles) {
			Vector2 pos = p.getPosition();
			Vector2 vel = p.getVelocity();
			if (pos.getX() < 0) {
				vel.setX(Math.abs(vel.getX()));
			} else if (pos.getX() >= WIDTH) {
				vel.setX(-Math.abs(vel.getX()));
			}
			if (pos.getY() < 0) {
				vel.setY(Math.abs(vel.getY()));
			} else if (pos.getY() >= HEIGHT) {
				vel.setY(-Math.abs(vel.getY()));
			}
			pos.mutableAdd(p.getVelocity());
		}
		draw();
	}
	
	/**
	 * Draws the particles.
	 */
	private void draw() {
		getPrimarySurface().clear();
		for (Particle p : particles) {
			getPrimarySurface()
					.save()
					.setFillStyle(p.getColor())
					.fillShape(new ShapeBuilder()
							.moveTo(p.getPosition())
							.drawCircle(p.getPosition(), 2)
							.build())
					.restore();
		}
	}
	
	/**
	 * Simple particle class.
	 * 
	 * @author hao1300@gmail.com
	 */
	private static class Particle {
		private Vector2 position, velocity;
		private Color color;
		
		public Particle(Vector2 position, Vector2 velocity, Color color) {
			this.position = position;
			this.velocity = velocity;
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
		
		public Vector2 getPosition() {
			return position;
		}
		
		public Vector2 getVelocity() {
			return velocity;
		}
	}
}
