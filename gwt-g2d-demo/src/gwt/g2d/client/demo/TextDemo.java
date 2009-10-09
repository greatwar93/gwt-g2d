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
import gwt.g2d.client.graphics.TextBaseline;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.user.client.ui.Panel;

/**
 * Demo for rendering text.
 * 
 * @author hao1300@gmail.com
 */
public class TextDemo extends AbstractDemo {
	public TextDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}

	@Override
	public void initialize() {
		getParentContainer().add(getPrimarySurface());
		getPrimarySurface().clear()
				.setFont("serif large 20px")
				.setTextBaseline(TextBaseline.TOP)
				.setFillStyle(KnownColor.RED)
				.fillText("Fill Text", 0, 20)
				.setFillStyle(KnownColor.BLUE)
				.strokeText("Stroke Text", 0, 50)
				.setShadowOffset(new Vector2(2, 2))
				.setShadowBlur(1)
				.setShadowColor(KnownColor.GREEN);
	}

	@Override
	public void update() {

	}
}
