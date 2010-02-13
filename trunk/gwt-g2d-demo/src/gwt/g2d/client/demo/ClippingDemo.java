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
import gwt.g2d.client.graphics.Gradient;
import gwt.g2d.client.graphics.KnownColor;
import gwt.g2d.client.graphics.LinearGradient;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.shapes.CircleShape;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;
import gwt.g2d.client.math.Matrix;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the use of clipping a shape.
 * 
 * @author hao1300@gmail.com
 */
public class ClippingDemo extends ReferenceDemo {
	private final Matrix matrix = new Matrix();
	
	public ClippingDemo(Panel parentContainer) {
		super("Clipping Demo", parentContainer,
				"https://developer.mozilla.org/samples/canvas-tutorial/6_2_canvas_clipping.html");
	}

	@Override
	public void initialize() {
		super.initialize();
		Surface surface = getPrimarySurface();
		int size = surface.getWidth();
		matrix.setIdentity();
		surface.setTransform(matrix);

		surface.fillRectangle(0, 0, size, size)
				.setTransform(matrix.mutableTranslate(size / 2, size / 2))
				.clipShape(new CircleShape(0, 0, size / 2.0 * .8));

		Gradient gradient = new LinearGradient(0, -size / 2, 0, size / 2)
				.addColorStop(0, new Color(35, 34, 86))
				.addColorStop(1, new Color(20, 55, 120));
		
		surface.setFillStyle(gradient)
				.fillRectangle(-size / 2, -size / 2, size, size);

		// draw stars
		surface.setFillStyle(KnownColor.WHITE);
		for (int j = 0; j < 500; j++) {
			surface.save()
					.translate(size / 2 - Random.nextInt(size), 
							size / 2 - Random.nextInt(size));
			drawStar(Random.nextInt(4) + 2);
			surface.restore();
		}
	}

	@Override
	public void update() {

	}

	private void drawStar(double r) {
		Surface surface = getPrimarySurface();
		surface.save();
		ShapeBuilder shapeBuilder = new ShapeBuilder().moveTo(r, 0);
		for (int i = 0; i < 9; i++) {
			shapeBuilder.rotate(Math.PI / 5);
			if (i % 2 == 0) {
				shapeBuilder.drawLineTo((r / 0.525731) * 0.200811, 0);
			} else {
				shapeBuilder.drawLineTo(r, 0);
			}
		}
		surface.fillShape(shapeBuilder.build())
				.restore();
	}
}
