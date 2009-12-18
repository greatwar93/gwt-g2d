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
import gwt.g2d.client.graphics.LinearGradient;
import gwt.g2d.client.graphics.TextBaseline;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.user.client.ui.Panel;

/**
 * Reference: <a href=
 * "http://dev.opera.com/articles/view/html-5-canvas-the-basics">
 * http://dev.opera.com/articles/view/html-5-canvas-the-basics</a>
 * 
 * @author hao1300@gmail.com
 */
public class GradientDemo extends ReferenceDemo {

	public GradientDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer,
				"http://dev.opera.com/articles/view/html-5-canvas-the-basics");
	}

	@Override
	public void initialize() {
		super.initialize();
		// The hue spectrum used by HSV color picker charts.
		Color[] hue = { KnownColor.RED, 
				KnownColor.YELLOW,
				KnownColor.GREEN,
				KnownColor.CYAN,
				KnownColor.BLUE,
				KnownColor.MAGENTA,
				KnownColor.RED };

		LinearGradient gradient = new LinearGradient(0, 0, 220, 0);
		
	// Add the color stops.
		for (int i = 0; i < hue.length; i++) {
			gradient.addColorStop(i / 6.0, hue[i]);
		}
	
		// Use the gradient for the fillStyle.
		getPrimarySurface().setFillStyle(gradient)
		
				// Now let's draw a rectangle with a black shadow.
				.setShadowOffset(new Vector2(5, 5))
				.setShadowBlur(4)
				.setShadowColor(new Color(0, 0, 0, .5))
				.fillRectangle(5, 5, 200, 100)
				
		    // For effect, let's also draw some text: "Hello world!".
		    .setFont("bold 36px sans-serif")
		    .setTextBaseline(TextBaseline.TOP)
		    .fillText("Hello world!", 5, 120, 200)

		    .setStrokeStyle(KnownColor.GRAY)
		    .strokeText("Hello world!", 5, 120, 200);		       
	}
}
