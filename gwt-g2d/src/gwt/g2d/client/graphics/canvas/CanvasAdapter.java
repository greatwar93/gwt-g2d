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

import gwt.canvas.client.impl.CanvasImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;

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
	 * @param width width of the canvas in pixels
	 * @param height height of the canvas in pixels.
	 */
	public CanvasAdapter(int width, int height) {
		canvasElement = Document.get().createElement("canvas");
		setWidth(width);
		setHeight(height);
		canvas.init(canvasElement);
	}
	
	/**
	 * Sets the width of the canvas in pixels.
	 */
	public void setWidth(int width) {
		canvasElement.setAttribute("width", width + "px");
		this.width = width;
	}
	
	/**
	 * Sets the height of the canvas in pixels.
	 */
	public void setHeight(int height) {
		canvasElement.setAttribute("height", height + "px");
		this.height = height;
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
	 * Pushes the current state onto the stack.
	 */
	public void save() {
		canvas.save();
	}
  
	/**
	 * Pushes the current state onto the stack.
	 */
	public void restore() {
		canvas.restore();
	}
	
	/**
	 * Changes the transformation matrix to apply a scaling transformation with 
	 * the given characteristics.
	 */
	public void scale(double x, double y) {
		canvas.scale(x, y);
	}
	
	/**
	 * Changes the transformation matrix to apply a rotation transformation with 
	 * the given characteristics.
	 */
	public void rotate(double angle) {
		canvas.rotate(angle);
	}
	
	/**
	 * Changes the transformation matrix to apply a translation transformation 
	 * with the given characteristics.
	 */
	public void translate(double x, double y) {
		canvas.translate(x, y);
	}
	
	/**
	 * Changes the transformation matrix to apply the matrix given by the 
	 * arguments.
	 */
	public void transform(double m11, double m12, double m21, double m22, 
			double dx, double dy) {
		canvas.transform(m11, m12, m21, m22, dx, dy);
	}
	
	/**
	 * Resets the current transform to the identity matrix, and then invoke the 
	 * {@link #transform(double, double, double, double, double, double)} method 
	 * with the same arguments.
	 */
	public void setTransform(double m11, double m12, double m21, double m22, 
			double dx, double dy) {
		canvas.setTransform(m11, m12, m21, m22, dx, dy);
	}
	
	/**
	 * Draws the given image onto the canvas.
	 */
	public void drawImage(ImageElement image, double x, double y) {
		drawImage(image, 0, 0, image.getWidth(), image.getHeight(), 
				x, y, image.getWidth(), image.getHeight());
	}
	
	/**
	 * Draws the given image onto the canvas.
	 */
	public void drawImage(ImageElement image, double x, double y, 
			double width, double height) {
		drawImage(image, 0, 0, image.getWidth(), image.getHeight(),
				x, y, width, height);
	}
	
	/**
	 * Draws the given image onto the canvas.
	 */
	public void drawImage(ImageElement image, 
			double sourceX, double sourceY, 
			double sourceWidth, double sourceHeight, 
			double destinationX, double destinationY, 
			double destinationWidth, double destinationHeight) {
		canvas.drawImage(image, sourceX, sourceY, 
				sourceWidth, sourceHeight, 
				destinationX, destinationY, 
				destinationWidth, destinationHeight);
	}
	
	/**
	 * Instantiate new blank ImageData objects whose dimension is equal to
	 * width x height.
	 * 
	 * @param width
	 * @param height
	 * @return a new ImageData object.
	 */
	public ImageDataAdapter createImageData(int width, int height) {
		return new ImageDataAdapter(canvas.createImageData(width, height));
	}
	
	/**
	 * Instantiate new blank ImageData objects whose dimension is equal to
	 * the given imageData.
	 * 
	 * @param imageData
	 * @return a new ImageData object.
	 */
	public ImageDataAdapter createImageData(ImageDataAdapter imageData) {
		return new ImageDataAdapter(canvas.createImageData(imageData.getWidth(), 
				imageData.getHeight()));
	}

	/**
	 * Returns an ImageData object representing the underlying pixel data for the 
	 * area of the canvas denoted by the rectangle whose corners are the four 
	 * points (x, y), (x + width, y), (x + width, y + height), (x, y + height), 
	 * in canvas coordinate space units. Pixels outside the canvas must be 
	 * returned as transparent black. Pixels must be returned as 
	 * non-premultiplied alpha values.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public ImageDataAdapter getImageData(double x, double y, double width, double height) {
		return new ImageDataAdapter(canvas.getImageData(x, y, width, height));
	}

	/**
	 * <p>Paints the data from the given ImageData object onto the canvas. If a 
	 * dirty rectangle is provided, only the pixels from that rectangle are 
	 * painted.</p>
	 * <p>The globalAlpha and globalCompositeOperation attributes, as well as the 
	 * shadow attributes, are ignored for the purposes of this method call; 
	 * pixels in the canvas are replaced wholesale, with no composition, alpha 
	 * blending, no shadows, etc.</p>
	 * 
	 * @param imageData
	 * @param x
	 * @param y
	 * @param dirtyX
	 * @param dirtyY
	 * @param dirtyWidth
	 * @param dirtyHeight
	 */
	public void putImageData(ImageDataAdapter imageData, double x, double y, 
			double dirtyX, double dirtyY, double dirtyWidth, double dirtyHeight) {
		canvas.putImageData(imageData.getNativeImageData(), x, y, 
				dirtyX, dirtyY, dirtyWidth, dirtyHeight);
	}
	
	/**
	 * Sets the current alpha value applied to rendering operations.
	 */
	public void setGlobalAlpha(double globalAlpha) {
		canvas.setGlobalAlpha(globalAlpha);
	}
	
	/**
	 * Gets the current alpha value applied to rendering operations.
	 */
	public double getGlobalAlpha() {
		return canvas.getGlobalAlpha();
	}
	
	/**
	 * Sets the current composite operation.
	 */
	public void setGlobalCompositeOperation(String globalCompositeOperation) {
		canvas.setGlobalCompositeOperation(globalCompositeOperation);
	}
	
	/**
	 * Gets the current composition operation.
	 */
	public String getGlobalCompositeOperation() {
		return canvas.getGlobalCompositeOperation();
	}
	
	/**
	 * Sets the current style used for stroking shapes.
	 */
	public void setStrokeStyle(String color) {
		canvas.setStrokeStyle(color);
	}
	
	/**
	 * Sets the current style used for stroking shapes.
	 */
	public void setStrokeStyle(GradientAdapter gradient) {
		canvas.setStrokeStyle(gradient.getNativeGradient());
	}
	
	/**
	 * Sets the current style used for filling shapes
	 */
	public void setFillStyle(String color) {
		canvas.setFillStyle(color);
	}
	
	/**
	 * Sets the current style used for filling shapes.
	 */
	public void setFillStyle(GradientAdapter gradient) {
		canvas.setFillStyle(gradient.getNativeGradient());
	}	
	
	/**
	 * Gets the adapter for a linear gradient between two points.
	 */
	public GradientAdapter createLinearGradient(double x0, double y0, 
			double x1, double y1) {
		return new GradientAdapter(canvas.createLinearGradient(x0, y0, x1, y1));
	}
	
	/**
	 * Gets the adapter for a radial gradient.
	 */
	public GradientAdapter createRadialGradient(double x0, double y0, 
			double radius0, double x1, double y1, double radius1) {
		return new GradientAdapter(canvas.createRadialGradient(
				x0, y0, radius0, x1, y1, radius1));
	}
	
	/**
	 * Sets the current line width.
	 */
	public void setLineWidth(double lineWidth) {
		canvas.setLineWidth(lineWidth);		
	}
	
	/**
	 * Gets the current line width.
	 */
	public double getLineWidth() {
		return canvas.getLineWidth();
	}
	
	/**
	 * Sets the current line cap style.
	 */
	public void setLineCap(String lineCap) {
		canvas.setLineCap(lineCap);
	}
	
	/**
	 * Gets the current line cap style.
	 */
	public String getLineCap() {
		return canvas.getLineCap();
	}
	
	/**
	 * Sets the current line join style.
	 */
	public void setLineJoin(String lineJoin) {
		canvas.setLineJoin(lineJoin);
	}
	
	/**
	 * Gets the current line join style.
	 */
	public String getLineJoin() {
		return canvas.getLineJoin();
	}
	
	/**
	 * Sets the current miter limit ratio.
	 */
	public void setMiterLimit(double miterLimit) {
		canvas.setMiterLimit(miterLimit);
	}
	
	/**
	 * Gets the current miter limit ratio.
	 */
	public double getMiterLimit() {
		return canvas.getMiterLimit();
	}
	
	/**
	 * Sets the distance that the shadow will be offset in the positive 
	 * horizontal direction.
	 * 
	 * @param shadowOffsetX
	 */
	public void setShadowOffsetX(double shadowOffsetX) {
		canvas.setShadowOffsetX(shadowOffsetX);
	}
	
	/**
	 * Gets the distance that the shadow will be offset in the positive
	 * horizontal direction.
	 * 
	 * @return
	 */
	public double getShadowOffsetX() {
		return canvas.getShadowOffsetX();		
	}

	/**
	 * Sets the distance that the shadow will be offset in the positive 
	 * vertical direction.
	 * 
	 * @param shadowOffsetY
	 */
	public void setShadowOffsetY(double shadowOffsetY) {
		canvas.setShadowOffsetY(shadowOffsetY);
	}

	/**
	 * Gets the distance that the shadow will be offset in the positive
	 * vertical direction.
	 * 
	 * @return
	 */
	public double getShadowOffsetY() {
		return canvas.getShadowOffsetY();
	}
	
	/**
	 * Gets the size of the blurring effect.
	 * 
	 * @param shadowBlur
	 */
	public void setShadowBlur(double shadowBlur) {
		canvas.setShadowBlur(shadowBlur);
	}
	
	/**
	 * Gets the size of the blurring effect.
	 * 	
	 * @return
	 */
	public double getShadowBlur() {
		return canvas.getShadowBlur();
	}

	/**
	 * Sets the color of the shadow.
	 * 
	 * @param shadowColor
	 */
	public void setShadowColor(String shadowColor) {
		canvas.setShadowColor(shadowColor);
	}
	
	/**
	 * Gets the color of the shadow.
	 * 
	 * @return
	 */
	public String getShadowColor() {
		return canvas.getShadowColor();
	}
	
	/**
	 * Clears all pixels on the canvas to transparent black.
	 */
	public void clear() {
		canvas.clear();
	}
	
	/**
	 * Clears all pixels on the canvas in the given rectangle to transparent 
	 * black.
	 */
	public void clearRect(double x, double y, double width, double height) {
		canvas.clearRect(x, y, width, height);
	}
	
	/**
	 * Paints the given rectangle onto the canvas, using the current fill style.
	 */
	public void fillRect(double x, double y, double width, double height) {
		canvas.fillRect(x, y, width, height);
	}
	
	/**
	 * Paints the box that outlines the given rectangle onto the canvas, using 
	 * the current stroke style.
	 */
	public void strokeRect(double x, double y, double width, double height) {
		canvas.strokeRect(x, y, width, height);
	}
	
	/**
	 * Resets the current path.
	 */
	public void beginPath() {
		canvas.beginPath();
	}
	
	/**
	 * Marks the current subpath as closed, and starts a new subpath with a point 
	 * the same as the start and end of the newly closed subpath.
	 */
	public void closePath() {
		canvas.closePath();
	}
	
	/**
	 * Creates a new subpath with the given point.
	 */
	public void moveTo(double x, double y) {
		canvas.moveTo(x, y);
	}
	
	/**
	 * Adds the given point to the current subpath, connected to the previous one 
	 * by a straight line.
	 */
	public void lineTo(double x, double y) {
		canvas.lineTo(x, y);
	}
	
	/**
	 * Adds the given point to the current path, connected to the previous one by 
	 * a quadratic B�zier curve with the given control point.
	 */
	public void quadraticCurveTo(double cpx, double cpy, double x, double y) {
		canvas.quadraticCurveTo(cpx, cpy, x, y);
	}
	
	/**
	 * Adds the given point to the current path, connected to the previous one by 
	 * a cubic B�zier curve with the given control points.
	 */
	public void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, 
			double x, double y) {
		canvas.cubicCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
	}
	
	/**
	 * Adds points to the subpath such that the arc described by the 
	 * circumference of the circle described by the arguments, starting at the 
	 * given start angle and ending at the given end angle, going in the given 
	 * direction, is added to the path, connected to the previous point by a 
	 * straight line.
	 */
	public void arc(double x, double y, double radius, double startAngle, 
			double endAngle, boolean antiClockwise) {
		canvas.arc(x, y, radius, startAngle, endAngle, antiClockwise);
	}
	
	/**
	 * Adds a new closed subpath to the path, representing the given rectangle.
	 */
	public void rect(double x, double y, double w, double h) {
		canvas.rect(x, y, w, h);
	}
	
	/**
	 * Fills the subpaths with the current fill style.
	 */
	public void fill() {
		canvas.fill();
	}
	
	/**
	 * Strokes the subpaths with the current stroke style.
	 */
	public void stroke() {
		canvas.stroke();
	}
	
	/**
	 * Further constrains the clipping region to the given path.
	 */
	public void clip() {
		canvas.clip();
	}
	
	/**
	 * Returns true if the given point is in the current path.
	 */
	public boolean isPointInPath(double x, double y) {
		return canvas.isPointInPath(x, y);
	}
	
	/**
	 * Sets the font settings. The syntax is the same as for the CSS 'font' 
	 * property; values that cannot be parsed as CSS font values are ignored.
	 */
	public void setFont(String font) {
		canvas.setFont(font);
	}
	
	/**
	 * Gets the font settings.
	 */
	public String getFont() {
		return canvas.getFont();
	}
	
	/**
	 * Sets the text alignment settings.
	 */
	public void setTextAlign(String textAlign) {
		canvas.setTextAlign(textAlign);
	}
	
	/**
	 * Gets the text alignment settings.
	 */
	public String getTextAlign() {
		return canvas.getTextAlign();
	}
	
	/**
	 * Sets the text baseline alignment settings.
	 */
	public void setTextBaseline(String textBaseline) {
		canvas.setTextBaseline(textBaseline);
	}
	
	/**
	 * Gets the text baseline alignment settings.
	 */
	public String getTextBaseline() {
		return canvas.getTextBaseline();
	}
	
	/**
	 * Renders the given text at the given (x, y).
	 */
	public void fillText(String text, double x, double y) {
		canvas.fillText(text, x, y);
	}
	
	/**
	 * Renders the given text at the given (x, y), ensuring that the text is
	 * not wider than maxWidth.
	 */
	public void fillText(String text, double x, double y, double maxWidth) {
		canvas.fillText(text, x, y, maxWidth);
	}
	
	/**
	 * Renders the given text at the given (x, y).
	 */
	public void strokeText(String text, double x, double y) {
		canvas.strokeText(text, x, y);
	}
	
	/**
	 * Renders the given text at the given (x, y), ensuring that the text is
	 * not wider than maxWidth.
	 */
	public void strokeText(String text, double x, double y, double maxWidth) {
		canvas.strokeText(text, x, y, maxWidth);
	}
	
	/**
	 * Returns the advance width with the metrics of the given text in the 
	 * current font.
	 */
	public double measureText(String text) {
		return canvas.measureText(text);
	}
}
