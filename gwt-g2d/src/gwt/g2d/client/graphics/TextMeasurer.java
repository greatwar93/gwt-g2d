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

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Helper class for measure the size of a text in pixels.
 * 
 * @author hao1300@gmail.com
 */
public class TextMeasurer {
	
	/**
	 * Returns the advance width with the metrics of the text.
	 * 
	 * @param surface the surface where the text are to be rendered.
	 * @param text the text to be rendered.
	 */
	public double measureTextWidth(Surface surface, String text) {
		return surface.measureText(text);
	}
	
	/**
	 * Returns the advance height of the text in pixel when it is rendered
	 * in the given surface.
	 * 
	 * @param surface the surface where the text are to be rendered.
	 * @param text the text to be rendered.
	 */
	public double measureTextHeight(Surface surface, String text) {
	  HTML html = new HTML();
	  RootPanel.get().add(html);
	  html.setHTML("<div style='font:" + surface.getFont()
	  		+ ";margin:0px;border:0px;padding:0px;'>" + text + "</div>");
		double h = html.getOffsetHeight();
		RootPanel.get().remove(html);
		return h;
	}
}
