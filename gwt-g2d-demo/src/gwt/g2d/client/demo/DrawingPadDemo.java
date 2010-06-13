/*
 * Copyright 2010 Hao Nguyen
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
import gwt.g2d.client.graphics.LineCap;
import gwt.g2d.client.graphics.LineJoin;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo on how to draw on a canvas using the mouse manager.
 * 
 * @author hao1300@gmail.com
 */
public class DrawingPadDemo extends AbstractDemo implements MouseDownHandler,
		MouseMoveHandler, MouseUpHandler {
	private Vector2 lastPosition = new Vector2();
	private boolean isLeftButtonDown, isRightButtonDown;

	public DrawingPadDemo(Panel parentContainer) {
		super("Drawing Pad Demo", parentContainer);
		getPrimarySurface().addMouseDownHandler(this);
		getPrimarySurface().addMouseMoveHandler(this);
		getPrimarySurface().addMouseUpHandler(this);
	}

	@Override
	public void initialize() {
		add(new Label("Use the mouse to draw on the canvas below:"));
		add(getPrimarySurface());
	}

	@Override
	public void update() {
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		switch (event.getNativeButton()) {
			case NativeEvent.BUTTON_LEFT:
				isLeftButtonDown = true;
				break;
			case NativeEvent.BUTTON_RIGHT:
				isRightButtonDown = true;
				break;
		}
		lastPosition.set(event.getX(), event.getY());
		event.preventDefault();
		event.stopPropagation();
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (isLeftButtonDown || isRightButtonDown) {
			Surface surface = getPrimarySurface();
			surface.save().setLineCap(LineCap.ROUND).setLineJoin(LineJoin.ROUND);
			if (isLeftButtonDown) {
				surface.setStrokeStyle(KnownColor.BLACK).setLineWidth(5);
			} else if (isRightButtonDown) {
				surface.setStrokeStyle(KnownColor.WHITE).setLineWidth(20);
			}
			surface.strokeShape(new ShapeBuilder()
					.drawLineSegment(lastPosition, new Vector2(event.getX(), event.getY()))
					.build());
			surface.restore();
			lastPosition.set(event.getX(), event.getY());
		}		
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		switch (event.getNativeButton()) {
			case NativeEvent.BUTTON_LEFT:
				isLeftButtonDown = false;
				break;
			case NativeEvent.BUTTON_RIGHT:
				isRightButtonDown = false;
				break;
		}
		event.preventDefault();
		event.stopPropagation();
	}
}
