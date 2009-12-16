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
package gwt.g2d.client.graphics.canvas;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;

/**
 * Adapter for accessing the canvas implementation.
 * 
 * @author hao1300@gmail.com
 */
public class CanvasAdapter {
	private final CanvasImpl canvas = (CanvasImpl) GWT.create(CanvasImpl.class);
	private final Element canvasElement;
	private int width, height;
	
	/**
	 * Initializes the canvas.
	 * 
	 * @param canvasElement an element with the canvas tag.
	 * @param width width of the canvas in pixels
	 * @param height height of the canvas in pixels.
	 */
	public CanvasAdapter(Element canvasElement, int width, int height) {
		this.canvasElement = canvasElement;
		canvas.init(canvasElement, width, height);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Sets the width of the canvas in pixels.
	 */
	public void setWidth(int width) {
		this.width = width;
		canvas.setWidth(width);
	}
	
	/**
	 * Sets the height of the canvas in pixels.
	 */
	public void setHeight(int height) {
		this.height = height;
		canvas.setHeight(height);
	}
	
	/**
	 * Gets the width of the canvas.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the canvas.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the canvas element.
	 */
	public Element getElement() {
		return canvasElement;
	}
	
	/**
	 * Gets the canvas' context.
	 */
	public Context getContext() {
		return canvas.getContext();
	}
}
