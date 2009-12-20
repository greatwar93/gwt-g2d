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

import gwt.g2d.client.graphics.Gradient;
import gwt.g2d.client.graphics.KnownColor;
import gwt.g2d.client.graphics.RadialGradient;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.shapes.Shape;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;
import gwt.g2d.client.math.Circle;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the use of drawing canvas onto canvas.
 * 
 * @author hao1300@gmail.com
 */
public class CanvasOnCanvasDemo extends AbstractDemo {
	
	public CanvasOnCanvasDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}

	@Override
	public void initialize() {
		add(new Label("This demo does not work under IE."));
		Surface originalCanvas = new Surface(300, 300);
		Circle smallCircle = new Circle(150, 150, 140);
		Circle bigCircle = new Circle(150, 150, 150);
		Shape smallCircleShape = new ShapeBuilder().drawCircle(smallCircle).build();
		Shape bigCircleShape = new ShapeBuilder().drawCircle(bigCircle).build();
		Gradient radialGradient = new RadialGradient(new Circle(150, 150, 0), smallCircle)
				.addColorStop(0, KnownColor.RED)
				.addColorStop(1, KnownColor.GREEN);
		originalCanvas.setFillStyle(KnownColor.BLACK)
				.fillShape(bigCircleShape)
				.setFillStyle(radialGradient)
				.fillShape(smallCircleShape);
		
		add(new Label("Original Canvas"));
		add(originalCanvas);
		
		add(new Label("Canvas with the original canvas drawn on top of it."));
		add(getPrimarySurface());
		
		getPrimarySurface().clear()
				.fillBackground(KnownColor.GRAY)
				.drawImage(originalCanvas.getCanvas(), 0, 0)
				.drawImage(originalCanvas.getCanvas(), 300, 0)
				.drawImage(originalCanvas.getCanvas(), 0, 300)
				.drawImage(originalCanvas.getCanvas(), 300, 300)
				.drawImage(originalCanvas.getCanvas(), 150, 150);
	}

	@Override
	public void update() {
	}
}
