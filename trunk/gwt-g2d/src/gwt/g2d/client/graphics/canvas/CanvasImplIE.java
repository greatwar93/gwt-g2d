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
import com.google.gwt.dom.client.Style.Unit;

/**
 * The IE implementation of the canvas widget.
 * 
 * @author hao1300@gmail.com
 */
public class CanvasImplIE extends CanvasImpl {
	
	@Override
	public void init(Element element, int width, int height) {
		this.element = element;
		this.context = ContextImpl.as(getJsContext(element));
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void setWidth(int width) {
		element.getStyle().setWidth(width, Unit.PX);
	}
	
	@Override
	public void setHeight(int height) {
		element.getStyle().setHeight(height, Unit.PX);
	}
	
	@Override
	protected native JavaScriptObject getJsContext(Element element) /*-{
		$wnd.G_vmlCanvasManager.initElement(element);
		return element.getContext("2d");
	}-*/;
}
