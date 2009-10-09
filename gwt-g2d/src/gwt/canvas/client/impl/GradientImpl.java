/*
 * Copyright 2008-2009 Oliver Zoran
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gwt.canvas.client.impl;

import gwt.canvas.client.Gradient;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The default implementation of the gradient style.
 */
public abstract class GradientImpl extends Gradient {

	protected JavaScriptObject gradient;

	public void addColorStop(double offset, String color) {
		addNativeColorStop(offset, color);
	}

	private native void addNativeColorStop(double offset, String color) /*-{
		this.@gwt.canvas.client.impl.GradientImpl::gradient.addColorStop(offset, color);
	}-*/;
}
