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

import gwt.g2d.client.graphics.PatternRepetition;
import gwt.g2d.client.graphics.TextAlign;
import gwt.g2d.client.graphics.TextBaseline;
import gwt.g2d.client.graphics.canvas.CanvasPattern;
import gwt.g2d.client.graphics.shapes.CircleShape;
import gwt.g2d.resources.client.ExternalImageResource;
import gwt.g2d.resources.client.ImageElementResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo for rendering pattern.
 * Reference: <a href=
 * "http://diveintohtml5.org/canvas.html">
 * http://diveintohtml5.org/canvas.html</a>
 * 
 * @author hao1300@gmail.com
 */
public class PatternDemo extends ReferenceDemo {
	public PatternDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer,
				"http://diveintohtml5.org/canvas.html");
	}

	@Override
	public void initialize() {
		super.initialize();
		Resources.INSTANCE.pastel().getImage(new ResourceCallback<ImageElementResource>() {
			@Override
			public void onSuccess(ImageElementResource resource) {
				CanvasPattern pattern = getPrimarySurface().createPattern(
						resource.getImage(), PatternRepetition.REPEAT);
				getPrimarySurface().setFillStyle(pattern)
						.fillRectangle(0, 0, 600, 200)
						.fillShape(new CircleShape(100, 300, 100))
						.setFont("60px sans-serif")
						.setTextAlign(TextAlign.CENTER)
						.setTextBaseline(TextBaseline.MIDDLE)
						.fillText("Hello World", 420, 300);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Error: " + e.getMessage());
			}
		});
	}
	
	interface Resources extends ClientBundle {
		static final Resources INSTANCE = GWT.create(Resources.class);
		
		@Source("images/pastel.png")
		ExternalImageResource pastel();
	}
}
