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

import gwt.canvas.client.Gradient;

/**
 * Adapter for accessing the gradient interface.
 * 
 * @author hao1300@gmail.com
 */
public class GradientAdapter {
	private Gradient nativeGradient;
	
	GradientAdapter(Gradient nativeGradient) {
		this.nativeGradient = nativeGradient;
	}
	
	/**
	 * Adds a color stop to the native gradient.
	 */
	public void addColorStop(double offset, String color) {
		nativeGradient.addColorStop(offset, color);
	}
	
	/**
	 * Gets the underlying gradient implementation.
	 */
	Gradient getNativeGradient() {
		return nativeGradient;
	}
}
