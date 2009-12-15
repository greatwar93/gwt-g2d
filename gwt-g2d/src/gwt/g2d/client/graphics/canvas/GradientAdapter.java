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

/**
 * Adapter for accessing the gradient interface.
 * 
 * @author hao1300@gmail.com
 */
public class GradientAdapter {
	private final JavaScriptObject nativeGradient;
	
	GradientAdapter(JavaScriptObject nativeGradient) {
		this.nativeGradient = nativeGradient;
	}
	
	/**
	 * Adds a color stop to the native gradient.
	 */
	public native void addColorStop(double offset, String color) /*-{
		this.@gwt.g2d.client.graphics.canvas.GradientAdapter::nativeGradient.addColorStop(
				offset, color);
	}-*/;
	
	/**
	 * Gets the underlying gradient implementation.
	 */
	final JavaScriptObject getNativeGradient() {
		return nativeGradient;
	}
}
