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
import gwt.g2d.client.graphics.ImageLoader;
import gwt.g2d.client.graphics.canvas.ImageDataAdapter;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo for pixel manipulation.

 * Reference <a href=
 * "http://dev.opera.com/articles/view/html-5-canvas-the-basics">
 * http://dev.opera.com/articles/view/html-5-canvas-the-basics</a>
 * 
 * @author hao1300@gmail.com
 */
public class PixelManipulatorDemo extends ReferenceDemo {
	public PixelManipulatorDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer, 
				"http://dev.opera.com/articles/view/html-5-canvas-the-basics");
	}

	@Override
	public void initialize() {
		add(new Label("Pixel manipulation is not available under IE."));
		super.initialize();
		ImageLoader.loadImages("images/gwt-logo.png", new ImageLoader.CallBack() {
			@Override
			public void onImagesLoaded(ImageElement[] imageElements) {
				ImageElement image = imageElements[0];
				getPrimarySurface().drawImage(image, 0, 0);
				
				ImageDataAdapter imageData = getPrimarySurface()
						.getImageData(0, 0, image.getWidth(), image.getHeight());
				for (int r = 0; r < image.getWidth(); r++) {
					for (int c = 0; c < image.getHeight(); c++) {
						Color color = imageData.getColor(r, c);
						imageData.setColor(r, c, new Color(
								255 - color.getR(), 
								255 - color.getG(), 
								255 - color.getB()));
					}
				}
				getPrimarySurface().putImageData(imageData, image.getWidth(), 0);
			}
		});
	}

	@Override
	public void update() {

	}
}