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
import gwt.g2d.client.graphics.canvas.ImageDataAdapter;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.user.client.ui.Panel;

/**
 * Demo for pixel manipulation.
 * 
 * @author hao1300@gmail.com
 */
public class PixelManipulatorDemo extends AbstractDemo {
	public PixelManipulatorDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}

	@Override
	public void initialize() {
		getParentContainer().add(getPrimarySurface());
		getPrimarySurface().fillBackground(KnownColor.BLUE);
		ImageDataAdapter imageData = getPrimarySurface().getImageData(0, 0, 100, 100);
		for (int r = 0; r < imageData.getHeight(); r++) {
			for (int c = 0; c < imageData.getWidth(); c++) {
				imageData.setColor(r, c, KnownColor.GOLD);
			}
		}
		getPrimarySurface().putImageData(imageData, Vector2.ZERO);
	}

	@Override
	public void update() {

	}
}
