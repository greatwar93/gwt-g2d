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
package gwt.g2d.client.graphics;

import java.util.Collection;

/**
 * Represents a gradient which can be used for fill style or stroke style.
 * 
 * @author hao1300@gmail.com
 */
public class Gradient {
	private final gwt.canvas.client.Gradient nativeGradient;
	
	public Gradient(gwt.canvas.client.Gradient nativeGradient) {
		this.nativeGradient = nativeGradient;
	}
	
	/**
	 * Adds a color at the given offset point.
	 * 
	 * @param offset offset in [0.0, 1.0] where the color is to be applied.
	 * @param color color at the given offset.
	 * @return self to support chaining.
	 */
	public Gradient addColorStop(double offset, Color color) {
		nativeGradient.addColorStop(offset, color.getColorCode());
		return this;
	}
	
	/**
	 * Adds a color stop.
	 * 
	 * @param colorStop the color stop to add.
	 * @return self to support chaining.
	 */
	public Gradient addColorStop(ColorStop colorStop) {
		return addColorStop(colorStop.getOffset(), colorStop.getColor());
	}
	
	/**
	 * Adds a collection of color stops.
	 * 
	 * @param colorStops the collection of color stops to add.
	 * @return self to support chaining.
	 */
	public Gradient addColorStops(Collection<ColorStop> colorStops) {
		for (ColorStop colorStop : colorStops) {
			addColorStop(colorStop);
		}
		return this;
	}
	
	/**
	 * Adds an array of color stops.
	 * 
	 * @param colorStops the array of color stops to add.
	 * @return self to support chaining.
	 */
	public Gradient addColorStops(ColorStop... colorStops) {
		for (ColorStop colorStop : colorStops) {
			addColorStop(colorStop);
		}
		return this;
	}
	
	/**
	 * Creates an underlying gradient implementation.
	 */
	gwt.canvas.client.Gradient createNativeGradient() {
		return nativeGradient;
	}
}
