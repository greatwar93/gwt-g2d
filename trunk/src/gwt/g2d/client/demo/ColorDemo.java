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

import gwt.g2d.client.graphics.KnownColor;
import gwt.g2d.client.math.Rectangle;


public class ColorDemo extends AbstractDemo {

	public ColorDemo(String demoName) {
		super(demoName);
	}

	@Override
	public void initialize() {
		getPrimarySurface().setBackgroundColor(KnownColor.BLACK);
		Rectangle rectangle = new Rectangle(0, 0, 50, 50);
		for (KnownColor c : KnownColor.getKnownColors()) {
			getPrimarySurface().setFillStyle(c).fillRectangle(rectangle);
			if (rectangle.getX() < getPrimarySurface().getWidth() - rectangle.getWidth()) {
				rectangle.setX(rectangle.getX() + rectangle.getWidth());
			} else {
				rectangle.setX(0);
				rectangle.setY(rectangle.getY() + rectangle.getHeight());
			}
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void draw() {		
	}
}