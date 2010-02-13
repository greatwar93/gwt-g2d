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

import gwt.g2d.client.graphics.DirectShapeRenderer;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the use of clipping a shape.
 * 
 * @author hao1300@gmail.com
 */
public class ShapeDemo extends AbstractDemo {

	public ShapeDemo(Panel parentContainer) {
		super("Shape Demo", parentContainer);
	}

	@Override
	public void initialize() {
		add(new Label("The left surface is rendered using DirectShapeRenderer, "
				+ "the right surface is rendered using ShapeBuilder."));
		Surface surface = getPrimarySurface();
		surface.setSize(220, 220);
		add(surface);
		drawDirectly(surface);
				
		Surface surface2 = new Surface(surface.getSize());
		add(surface2);
		drawWithShapeBuilder(surface2);
	}

	@Override
	public void update() {

	}
	
	/**
	 * Draws directly onto the surface using {@link DirectShapeRenderer}.
	 */
	private void drawDirectly(Surface surface) {
		DirectShapeRenderer shapeRenderer = new DirectShapeRenderer(surface);
		shapeRenderer.beginPath()
				.drawLineSegment(40, 20, 180, 20)
				.drawQuadraticCurveTo(200, 20, 200, 40)
				.drawLineTo(200, 180)
				.drawQuadraticCurveTo(200, 200, 180, 200)
				.drawLineTo(40, 200)
				.drawQuadraticCurveTo(20, 200, 20, 180)
				.drawLineTo(20, 40)
				.drawQuadraticCurveTo(20, 20, 40, 20)
				.moveTo(50, 50)
				.drawArc(50, 50, 15, (float) Math.toRadians(30), (float) Math.toRadians(-30), false)
				.closePath()
				.stroke();
		shapeRenderer.beginPath();
		for (int i = 0; i < 8; i++) {
			shapeRenderer.drawCircle(80 + i * 12, 50, 1);
		}
		shapeRenderer.closePath().fill();
	}
	
	/**
	 * Draws onto the surface using {@link ShapeBuilder}.
	 */
	private void drawWithShapeBuilder(Surface surface) {
		surface.strokeShape(new ShapeBuilder()
				.drawLineSegment(40, 20, 180, 20)
				.drawQuadraticCurveTo(200, 20, 200, 40)
				.drawLineTo(200, 180)
				.drawQuadraticCurveTo(200, 200, 180, 200)
				.drawLineTo(40, 200)
				.drawQuadraticCurveTo(20, 200, 20, 180)
				.drawLineTo(20, 40)
				.drawQuadraticCurveTo(20, 20, 40, 20)
				.moveTo(50, 50)
				.drawArc(50, 50, 15, (float) Math.toRadians(30), (float) Math.toRadians(-30), false)
				.build());
		ShapeBuilder dotShapeBuilder = new ShapeBuilder();
		for (int i = 0; i < 8; i++) {
			dotShapeBuilder.drawCircle(80 + i * 12, 50, 1);
		}
		surface.fillShape(dotShapeBuilder.build());
	}
}
