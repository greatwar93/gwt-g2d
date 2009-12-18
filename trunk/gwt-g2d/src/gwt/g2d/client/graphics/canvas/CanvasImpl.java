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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * The default implementation of the canvas element.
 * 
 * This is inspired by Oliver Zoran's gwt-canvas.
 *  
 * @see <a href="http://www.whatwg.org/specs/web-apps/current-work/#the-canvas">
 * 			http://www.whatwg.org/specs/web-apps/current-work/#the-canvas</a>
 * @see <a href="http://www.w3.org/html/wg/html5/#the-canvas">
 * 			http://www.w3.org/html/wg/html5/#the-canvas</a>
 * @see <a href="http://canvex.lazyilluminati.com/tests/tests/results.html">
 * 			http://canvex.lazyilluminati.com/tests/tests/results.html</a>
 */
public class CanvasImpl implements Canvas {
	protected ContextImpl context;
	protected Element element;
	
	@Override
	public void init(Element element, int width, int height) {
		this.element = element;
		setWidth(width);
		setHeight(height);
		this.context = ContextImpl.as(getJsContext(element));
	}

	/**
	 * Sets the width for the canvas.
	 * 
	 * @param width
	 */
	@Override
	public void setWidth(int width) {
		element.setAttribute("width", width + "px");
	}
	
	/**
	 * Sets the height for the canvas.
	 * 
	 * @param height
	 */
	@Override
	public void setHeight(int height) {
		element.setAttribute("height", height + "px");
	} 
	
	/**
	 * Gets the context for rendering onto the canvas.
	 */
	@Override
	public Context getContext() {
		return context;
	}
	
	protected native JavaScriptObject getJsContext(Element element) /*-{
		return element.getContext("2d");
	}-*/;
}