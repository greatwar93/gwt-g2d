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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;

/**
 * The default implementation of the canvas widget.
 * 
 * (This file has been modified by hao1300@gmail.com to allow for some of the
 *  newer features of HTML canvas.)
 *  
 * @see <a href="http://www.whatwg.org/specs/web-apps/current-work/#the-canvas">
 * http://www.whatwg.org/specs/web-apps/current-work/#the-canvas</a>
 * @see <a href="http://www.w3.org/html/wg/html5/#the-canvas">
 * http://www.w3.org/html/wg/html5/#the-canvas</a>
 * @see <a href="http://canvex.lazyilluminati.com/tests/tests/results.html">
 * http://canvex.lazyilluminati.com/tests/tests/results.html</a>
 */
public class CanvasImpl {

	/////////////////////////////////////////////////////////////////
	// PRIVATE/PROTECTED MEMBERS/METHODS
	/////////////////////////////////////////////////////////////////

	protected JavaScriptObject context;
	
	public native void init(Element element) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context = element.getContext("2d");
	}-*/;

	/////////////////////////////////////////////////////////////////
	// CANVAS STATE METHODS
	/////////////////////////////////////////////////////////////////

	public native void restore() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.restore();
	}-*/;

	public native void save() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.save();
	}-*/;

	public native void rotate(double angle) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.rotate(angle);
	}-*/;

	public native void scale(double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.scale(x, y);
	}-*/;

	public native void translate(double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.translate(x, y);
	}-*/;
	
	public native void transform(double m11, double m12, double m21, double m22, 
			double dx, double dy) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.transform(m11, m12, m21, 
				m22, dx, dy);
	}-*/;
	
	public native void setTransform(double m11, double m12, double m21, double m22, 
			double dx, double dy) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.setTransform(m11, m12, m21, 
				m22, dx, dy);
	}-*/;

	/////////////////////////////////////////////////////////////////
	// WORKING WITH PATHS
	/////////////////////////////////////////////////////////////////

	public native void arc(double x, double y, double radius, double startAngle, double endAngle, boolean antiClockwise) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.arc(x, y, radius, startAngle, endAngle, antiClockwise);
	}-*/;

	public native void cubicCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
	}-*/;

	public native void quadraticCurveTo(double cpx, double cpy, double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.quadraticCurveTo(cpx, cpy, x, y);
	}-*/;

	public native void beginPath() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.beginPath();
	}-*/;

	public native void closePath() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.closePath();
	}-*/;

	public native void moveTo(double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.moveTo(x, y);
	}-*/;

	public native void lineTo(double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.lineTo(x, y);
	}-*/;

	public native void rect(double x, double y, double w, double h) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.rect(x, y, w, h);
	}-*/;
	
	public native void clip() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.clip();
	}-*/;
	
	public native boolean isPointInPath(double x, double y) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.isPointInPath(x, y);
	}-*/;

	/////////////////////////////////////////////////////////////////
	// STROKING AND FILLING
	/////////////////////////////////////////////////////////////////

	public native void clear() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.clearRect(-1e4, -1e4, 2e4, 2e4);
	}-*/;

	public native void fill() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.fill();
	}-*/;

	public native void stroke() /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.stroke();
	}-*/;

	public native void clearRect(double x, double y, double w, double h) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.clearRect(x, y, w, h);
	}-*/;
	
	public native void fillRect(double x, double y, double w, double h) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.fillRect(x, y, w, h);
	}-*/;

	public native void strokeRect(double x, double y, double w, double h) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.strokeRect(x, y, w, h);
	}-*/;

	/////////////////////////////////////////////////////////////////
	// GRADIENT STYLES
	/////////////////////////////////////////////////////////////////

	public native JavaScriptObject createLinearGradient(double x0, 
			double y0, double x1, double y1) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.createLinearGradient(
				x0, y0, x1, y1);
	}-*/;

	public native JavaScriptObject createRadialGradient(double x0, 
			double y0, double r0, double x1, double y1, double r1) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.createRadialGradient(
				x0, y0, r0, x1, y1, r1);
	}-*/;

	/////////////////////////////////////////////////////////////////
	// DRAWING IMAGES
	/////////////////////////////////////////////////////////////////

	public native void drawImage(ImageElement image, double sx, double sy, double sWidth, double sHeight,
			double dx, double dy, double dWidth, double dHeight) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.drawImage(image, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight);
	}-*/;

	/////////////////////////////////////////////////////////////////
	// DRAWING TEXTS
	/////////////////////////////////////////////////////////////////
	
	public native void fillText(String text, double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.fillText(text, x, y);
	}-*/;
	
	public native void fillText(String text, double x, double y, double maxWidth) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.fillText(text, x, y, maxWidth);
	}-*/;
	
	public native void strokeText(String text, double x, double y) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.strokeText(text, x, y);
	}-*/;
	
	public native void strokeText(String text, double x, double y, double maxWidth) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.strokeText(text, x, y, maxWidth);
	}-*/;
	
	/////////////////////////////////////////////////////////////////
	// PIXEL MANIPULATION
	/////////////////////////////////////////////////////////////////
	
	public native JavaScriptObject createImageData(double width, double height) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.createImageData(width, height);
	}-*/;
	
	public native JavaScriptObject getImageData(double x, double y, double width, double height) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.getImageData(x, y, width, height);
	}-*/;
	
	public native void putImageData(JavaScriptObject imageData, double x, double y, 
			double dirtyX, double dirtyY, double dirtyWidth, double dirtyHeight) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.putImageData(imageData, x, y);
		//,dirtyX, dirtyY, dirtyWidth, dirtyHeight);
	}-*/;
	
	/////////////////////////////////////////////////////////////////
	// SETTERS AND GETTERS
	/////////////////////////////////////////////////////////////////

	public native void setGlobalAlpha(double globalAlpha) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.globalAlpha = globalAlpha;
	}-*/;

	public native double getGlobalAlpha() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.globalAlpha;
	}-*/;

	public native void setGlobalCompositeOperation(String globalCompositeOperation) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.globalCompositeOperation = globalCompositeOperation;
	}-*/;

	public native String getGlobalCompositeOperation() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.globalCompositeOperation;
	}-*/;

	public native void setStrokeStyle(String strokeStyle) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.strokeStyle = strokeStyle;
	}-*/;
	
	public native void setStrokeStyle(JavaScriptObject strokeStyle) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.strokeStyle = strokeStyle;
	}-*/;

	public native String getStrokeStyle() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.strokeStyle;
	}-*/;

	public native void setFillStyle(JavaScriptObject fillStyle) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.fillStyle = fillStyle;
	}-*/;

	public native void setFillStyle(String fillStyle) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.fillStyle = fillStyle;
	}-*/;

	public native String getFillStyle() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.fillStyle;
	}-*/;

	public native void setLineWidth(double lineWidth) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.lineWidth = lineWidth;
	}-*/;

	public native double getLineWidth() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.lineWidth;
	}-*/;

	public native void setLineCap(String lineCap) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.lineCap = lineCap;
	}-*/;

	public native String getLineCap() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.lineCap;
	}-*/;

	public native void setLineJoin(String lineJoin) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.lineJoin = lineJoin;
	}-*/;

	public native String getLineJoin() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.lineJoin;
	}-*/;

	public native void setMiterLimit(double miterLimit) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.miterLimit = miterLimit;
	}-*/;

	public native double getMiterLimit() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.miterLimit;
	}-*/;
	
	public native void setFont(String font) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.font = font;
	}-*/;
	
	public native String getFont() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.font;
	}-*/;
	
	public native void setTextAlign(String textAlign) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.textAlign = textAlign;
	}-*/;

	public native String getTextAlign() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.textAlign;
	}-*/;

	public native void setTextBaseline(String textBaseline) /*-{
		this.@gwt.canvas.client.impl.CanvasImpl::context.textBaseline = textBaseline;
	}-*/;

	public native String getTextBaseline() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.textBaseline;
	}-*/;
	
	public native double measureText(String text) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.measureText(text).width;
	}-*/;
	
	public native double getShadowOffsetX() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowOffsetX;
	}-*/;
	
	public native void setShadowOffsetX(double shadowOffsetX) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowOffsetX = shadowOffsetX;
	}-*/;
	
	public native double getShadowOffsetY() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowOffsetY;
	}-*/;
	
	public native void setShadowOffsetY(double shadowOffsetY) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowOffsetY = shadowOffsetY;
	}-*/;
	
	public native double getShadowBlur() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowBlur;
	}-*/;
	
	public native void setShadowBlur(double shadowBlur) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowBlur = shadowBlur;
	}-*/;
	
	public native String getShadowColor() /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowColor;
	}-*/;
	
	public native void setShadowColor(String shadowColor) /*-{
		return this.@gwt.canvas.client.impl.CanvasImpl::context.shadowColor = shadowColor;
	}-*/; 
}
