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

import com.google.gwt.dom.client.Element;

/**
 * Interface for the canvas element.
 * 
 * @author hao1300@gmail.com
 */
public interface Canvas {
	
	/**
	 * Initializes the canvas element.
	 * 
	 * @param element
	 * @param width
	 * @param height
	 */
	public void init(Element element, int width, int height);

	/**
	 * Sets the width for the canvas.
	 * 
	 * @param width
	 */
	public void setWidth(int width);
	
	/**
	 * Sets the height for the canvas.
	 * 
	 * @param height
	 */
	public void setHeight(int height);
	
	/**
	 * Gets the context for rendering onto the canvas.
	 */
	public Context getContext();
}
