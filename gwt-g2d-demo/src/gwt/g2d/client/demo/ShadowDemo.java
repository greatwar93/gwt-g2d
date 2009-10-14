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
import gwt.g2d.client.math.Vector2;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo for rendering shadow.
 * Reference: <a href=
 * "http://dev.opera.com/articles/view/html-5-canvas-the-basics">
 * http://dev.opera.com/articles/view/html-5-canvas-the-basics</a>
 * 
 * @author hao1300@gmail.com
 */
public class ShadowDemo extends AbstractDemo {
	public ShadowDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}

	@Override
	public void initialize() {
		getParentContainer().clear();
		getParentContainer().add(getPrimarySurface());
		getParentContainer().add(new InlineLabel("Reference: "));
		getParentContainer().add(new Anchor(
				"http://dev.opera.com/articles/view/html-5-canvas-the-basics",
				"http://dev.opera.com/articles/view/html-5-canvas-the-basics"));
		getPrimarySurface().clear()
				.setShadowOffset(new Vector2(5, 5))
				.setShadowBlur(4)
				.setShadowColor(new Color(255, 0, 0, .5))
				.setFillStyle(KnownColor.BLUE)
				.fillRectangle(20, 20, 150, 100);
	}

	@Override
	public void update() {

	}
}
