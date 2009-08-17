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

import gwt.g2d.client.graphics.canvas.CanvasAdapter;
import gwt.g2d.client.graphics.shapes.Shape;
import gwt.g2d.client.math.Rectangle;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.FocusWidget;

/**
 * The surface that an application uses to render to the screen.
 * 
 * @see <a href="http://dev.w3.org/html5/spec/Overview.html#the-canvas-element">
 * http://dev.w3.org/html5/spec/Overview.html#the-canvas-element</a>
 * 
 * @author hao1300@gmail.com
 */
public class Surface extends FocusWidget {	
	private final CanvasAdapter canvas;
	
	/**
	 * Initialize a surface of the given size.
	 * 
	 * @param width width of the surface.
	 * @param height height of the surface.
	 */
	public Surface(int width, int height) {
		canvas = new CanvasAdapter(width, height);
		setSize(width, height);
		setElement(canvas.getElement());
	}
	
	/**
	 * Initialize a surface of the given size.
	 * 
	 * @param size the size of the surface to initialize.
	 */
	public Surface(Vector2 size) {
		this(size.getIntX(), size.getIntY());
	}
	
	/**
	 * Gets the size of the surface.
	 */
	public Vector2 getSize() {
		return new Vector2(getWidth(), getHeight());
	}

	/**
	 * Gets the width of the surface.
	 */
	public int getWidth() {
		return canvas.getWidth();
	}
	
	/**
	 * Gets the height of the surface.
	 */
	public int getHeight() {
		return canvas.getHeight();
	}
	
	/**
	 * Resizes the surface.
	 * 
	 * @param width the new width of the surface.
	 * @param height the new height of the surface.
	 */
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Sets the width of the surface.
	 */
	public void setWidth(int width) {
		canvas.setWidth(width);
	}
	
	/**
	 * Sets the height of the surface.
	 */
	public void setHeight(int height) {
		canvas.setHeight(height);
	}
	
	/**
	 * Gets the rectangle that encloses this surface.
	 */
	public Rectangle getViewRectangle() {
		return new Rectangle(0, 0, getWidth(), getHeight());
	}

	/**
	 * Gets the underlying canvas implementation.
	 * Use with caution!
	 * 
	 * @return the underlying canvas implementation.
	 */
	public CanvasAdapter getCanvas() {
		return canvas;
	}
	
	/**
	 * Pushes the current state onto the stack.
	 * 
	 * Drawing states consist of:
	 * <ul>
	 * <li>The current transformation matrix.</li>
	 * <li>The current clipping region.</li>
	 * <li>The current values of the following attributes: strokeStyle, fillStyle, 
	 * globalAlpha, lineWidth, lineCap, lineJoin, miterLimit, shadowOffsetX, 
	 * shadowOffsetY, shadowBlur, shadowColor, globalCompositeOperation, font, 
	 * textAlign, textBaseline.</li>
	 * </ul>
	 * 
	 * Note: The current path and the current bitmap are not part of the drawing 
	 * state.
	 * 
	 * @return self to support chaining.
	 */
	public Surface save() {
		canvas.save();
		return this;
	}
	
	/**
	 * Pops the top state on the stack, restoring the context to that state.
	 * Sees {@link #save()} method on what can being saved and restored.
	 * 
	 * @return self to support chaining.
	 */
	public Surface restore() {
		canvas.restore();
		return this;
	}
	
	/**
	 * Scales by (x, y) units.
	 * 
	 * @param x
	 * @param y
	 * @return self to support chaining.
	 */
	public Surface scale(double x, double y) {
		canvas.scale(x, y);
		return this;
	}
	
	/**
	 * Scales uniformly by scale units.
	 * 
	 * @param scale
	 * @return self to support chaining.
	 */
	public Surface scale(double scale) {
		return scale(scale, scale);
	}
	
	/**
	 * Rotates clockwise by the given angle about the origin.
	 * 
	 * @param angle
	 * @return self to support chaining.
	 */
	public Surface rotate(double angle) {
		canvas.rotate(angle);
		return this;
	}
	
	/**
	 * Rotates anti-clockwise by the given angle about the origin.
	 * 
	 * @param angle
	 * @return self to support chaining.
	 */
	public Surface rotateAnticlockwise(double angle) {
		return rotate(Math.PI - angle);
	}
	
	/**
	 * Translates the origin of the surface by (x, y) units.
	 * 
	 * @param x
	 * @param y
	 * @return self to support chaining.
	 */
	public Surface translate(double x, double y) {
		canvas.translate(x, y);
		return this;
	}
	
	/**
	 * Multiply the current transformation by the given transformation matrix.
	 * 
	 * The matrix has the following structure:
	 * <pre> 
   * m11 m21 dx
   * m12 m22 dy
   *  0   0   1 
   * </pre>
   * 
	 * @param m11 
	 * @param m12
	 * @param m21
	 * @param m22
	 * @param dx
	 * @param dy
	 * @return self to support chaining.
	 */
	public Surface transform(double m11, double m12, double m21, double m22,
      double dx, double dy) {
		canvas.transform(m11, m12, m21, m22, dx, dy);
		return this;
	}
	
	/**
	 * Sets the current transformation to be the given transformation matrix.
	 * 
	 * The matrix has the following structure:
	 * <pre> 
   * m11 m21 dx
   * m12 m22 dy
   *  0   0   1 
   * </pre>
   * 
	 * @param m11 
	 * @param m12
	 * @param m21
	 * @param m22
	 * @param dx
	 * @param dy
	 * @return self to support chaining.
	 */
	public Surface setTransform(double m11, double m12, double m21, double m22,
      double dx, double dy) {
		canvas.setTransform(m11, m12, m21, m22, dx, dy);
		return this;
	}
	
	/**
	 * Sets the current alpha value applied to rendering operations.
	 * Default: 1.0.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setGlobalAlpha(double alpha) {
		canvas.setGlobalAlpha(alpha);
		return this;
	}
	
	/**
	 * Gets the current alpha value applied to rendering operations.
	 */
	public double getGlobalAlpha() {
		return canvas.getGlobalAlpha();
	}
	
	/**
	 * Sets the current composition operation.
	 * Default: {@link Composition#SOURCE_OVER}.
	 * 
	 * @param compositeOperation
	 * @return self to support chaining.
	 */
	public Surface setGlobalCompositeOperation(Composition compositeOperation) {
		canvas.setGlobalCompositeOperation(compositeOperation.toString());
		return this;
	}
	
	/**
	 * Gets the current composition operation.
	 */
	public Composition getGlobalCompositeOperation() {
		return Composition.valueOf(canvas.getGlobalCompositeOperation());
	}
	
	/**
	 * Sets the fill style.
	 * 
	 * @param color the color for the fill style.
	 * @return self to support chaining.
	 */
	public Surface setFillStyle(Color color) {
		canvas.setFillStyle(color.getColorCode());
		return this;
	}
	
	/**
	 * Sets the fill style.
	 * 
	 * @param gradient the gradient for the fill style.
	 * @return self to support chaining.
	 */
	public Surface setFillStyle(Gradient gradient) {
		canvas.setFillStyle(gradient.getGradientAdapter(canvas));
		return this;
	}
	
	/**
	 * Sets the stroke style.
	 */
	public Surface setStrokeStyle(Color color) {
		canvas.setStrokeStyle(color.getColorCode());
		return this;
	}
	
	/**
	 * Sets the stroke style.
	 */
	public Surface setStrokeStyle(Gradient gradient) {
		canvas.setStrokeStyle(gradient.getGradientAdapter(canvas));
		return this;
	}
	
	/**
	 * Sets the width of lines, in coordinate space units. On setting, zero, 
	 * negative, infinite, and NaN values must be ignored, leaving the value 
	 * unchanged.
	 * 
	 * Default: 1.0.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setLineWidth(double lineWidth) {
		canvas.setLineWidth(lineWidth);		
		return this;
	}
	
	/**
	 * Gets the width of lines, in coordinate space units.
	 */
	public double getLineWidth() {
		return canvas.getLineWidth();
	}
	
	/**
	 * Sets the type of endings that UAs will place on the end of lines.
	 * 
	 * Default: {@link LineCap#BUTT}.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setLineCap(LineCap lineCap) {
		canvas.setLineCap(lineCap.toString());
		return this;
	}
	
	/**
	 * Gets the type of endings that UAs will place on the end of lines.
	 */
	public LineCap getLineCap() {
		return LineCap.valueOf(canvas.getLineCap());
	}
	
	/**
	 * Sets the type of corners that UAs will place where two lines meet.
	 * 
	 * Default: {@link LineJoin#MITER}.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setLineJoin(LineJoin lineJoin) {
		canvas.setLineJoin(lineJoin.toString());
		return this;
	}
	
	/**
	 * Gets the type of corners that UAs will place where two lines meet.
	 */
	public LineJoin getLineJoin() {
		return LineJoin.valueOf(canvas.getLineJoin());
	}
	
	/**
	 * Sets the current miter limit ratio.
	 * 
	 * The miter length is the distance from the point where the join occurs to 
	 * the intersection of the line edges on the outside of the join. The miter 
	 * limit ratio is the maximum allowed ratio of the miter length to half the 
	 * line width. If the miter length would cause the miter limit ratio to be 
	 * exceeded, this second triangle must not be rendered.
	 * 
	 * The miter limit ratio can be explicitly set using the miterLimit attribute. 
	 * On setting, zero, negative, infinite, and NaN values must be ignored, 
	 * leaving the value unchanged.
	 * 
	 * Default: 10.0.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setMiterLimit(double miterLimit) {
		canvas.setMiterLimit(miterLimit);
		return this;
	}
	
	/**
	 * Gets the current miter limit ratio.
	 */
	public double getMiterLimit() {
		return canvas.getMiterLimit();
	}
	
	/**
	 * Clears all pixels on the surface in the given rectangle to transparent 
	 * black.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return self to support chaining.
	 */
	public Surface clearRectangle(double x, double y, double width, double height) {
		canvas.clearRect(x, y, width, height);
		return this;
	}
	
	/**
	 * Clears all pixels on the surface in the given rectangle to transparent 
	 * black.
	 * 
	 * @param rectangle
	 * @return self to support chaining.
	 */
	public Surface clearRectangle(Rectangle rectangle) {
		return clearRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
				rectangle.getHeight());
	}
	
	/**
	 * Paints the specified rectangular area using the fillStyle.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return self to support chaining.
	 */
	public Surface fillRectangle(double x, double y, double width, double height) {
		canvas.fillRect(x, y, width, height);
		return this;
	}
	
	/**
	 * Paints the specified rectangular area using the fillStyle.
	 * 
	 * @param rectangle
	 * @return self to support chaining.
	 */
	public Surface fillRectangle(Rectangle rectangle) {
		return fillRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
				rectangle.getHeight());
	}
	
	/**
	 * Strokes the specified rectangle's path using the strokeStyle, lineWidth, 
	 * lineJoin, and (if appropriate) miterLimit attributes
	 */
	public Surface strokeRectangle(double x, double y, double width, double height) {
		canvas.strokeRect(x, y, width, height);
		return this;
	}
	
	/**
	 * Strokes the specified rectangle's path using the strokeStyle, lineWidth, 
	 * lineJoin, and (if appropriate) miterLimit attributes
	 */
	public Surface strokeRectangle(Rectangle rectangle) {
		return strokeRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
				rectangle.getHeight());
	}
	
	/**
	 * Fills the specified shape using the fillStyle.
	 */
	public Surface fillShape(Shape shape) {
		shape.draw(this);
		return fill();
	}
	
	/**
	 * Strokes the specified shape path using the strokeStyle, lineWidth, 
	 * lineJoin, and (if appropriate) miterLimit attributes
	 */
	public Surface strokeShape(Shape shape) {
		shape.draw(this);
		return stroke();
	}
	
	/**
	 * Fills the background with the given color.
	 */
	public Surface fillBackground(Color color) {
		return setFillStyle(color).fillRectangle(getViewRectangle());
	}
	
	/**
	 * Fills the background with the given gradient.
	 */
	public Surface fillBackground(Gradient gradient) {
		return setFillStyle(gradient).fillRectangle(getViewRectangle());
	}
	
	/**
	 * Clears all pixels to the surface to transparent black.
	 * 
	 * @return self to support chaining.
	 */
	public Surface clear() {
		canvas.clear();
		return this;
	}
	
	/**
	 * Paints the area within the current path.
	 * 
	 * @return self to support chaining.
	 */
	public Surface fill() {
		canvas.fill();
		return this;
	}
	
	/**
	 * Paints a line along the current path.
	 * 
	 * @return self to support chaining.
	 */
	public Surface stroke() {
		canvas.stroke();
		return this;
	}
	
	/**
	 * Draws the image at the given position.
	 * 
	 * @param image the image to draw.
	 * @param x the x-coordinate to draw the image.
	 * @param y the y-coordinate to draw the image.
	 * @return self to support chaining.
	 */
	public Surface drawImage(ImageElement image, double x, double y) {
		canvas.drawImage(image, x, y);
		return this;
	}
	
	/**
	 * Draws the image at the given position.
	 * 
	 * @param image the image to draw.
	 * @param position the position to draw the image.
	 * @return self to support chaining.
	 */
	public Surface drawImage(ImageElement image, Vector2 position) {
		return drawImage(image, position.getX(), position.getY());
	}
	
	/**
	 * Draws the image in the specified rectangle.
	 * 
	 * @param image the image to draw.
	 * @param x the x-coordinate to draw the image.
	 * @param y the y-coordinate to draw the image.
	 * @param width
	 * @param height
	 * @return self to support chaining.
	 */
	public Surface drawImage(ImageElement image, double x, double y, double width, 
			double height) {
		canvas.drawImage(image, x, y, width, height);
		return this;
	}
	
	/**
	 * Draws the image in the specified rectangle.
	 * 
	 * @param image the image to draw.
	 * @param rectangle the rectangle inside which the image is to be drawn.
	 * @return self to support chaining.
	 */
	public Surface drawImage(ImageElement image, Rectangle rectangle) {
		return drawImage(image, rectangle.getX(), rectangle.getY(), 
				rectangle.getWidth(), rectangle.getHeight());
	}
	
	/**
	 * Draws the portion of the image in the source rectangle into the 
	 * destination rectangle.
	 * 
	 * @param image image the image to draw.
	 * @param sourceX
	 * @param sourceY
	 * @param sourceWidth
	 * @param sourceHeight
	 * @param destinationX
	 * @param destinationY
	 * @param destinationWidth
	 * @param destinationHeight
	 * @return self to support chaining.
	 */
	public Surface drawImage(ImageElement image, double sourceX, double sourceY, 
			double sourceWidth, double sourceHeight, double destinationX, 
			double destinationY, double destinationWidth, double destinationHeight) {
		canvas.drawImage(image, sourceX, sourceY, sourceWidth, sourceHeight,
				destinationX, destinationY, destinationWidth, destinationHeight);
		return this;
	}
	
	/**
	 * /**
	 * Draws the portion of the image in the source rectangle into the 
	 * destination rectangle.
	 * 
	 * @param image
	 * @param sourceRectangle
	 * @param destinationRectangle
	 * @return self to support chaining.
	 */
	public Surface drawImage(ImageElement image, Rectangle sourceRectangle, 
			Rectangle destinationRectangle) {
		return drawImage(image, sourceRectangle.getX(), sourceRectangle.getY(),
				sourceRectangle.getWidth(), sourceRectangle.getHeight(),
				destinationRectangle.getX(), destinationRectangle.getY(), 
				destinationRectangle.getWidth(), destinationRectangle.getHeight());
	}
	
	/**
	 * Sets the font settings. The syntax is the same as for the CSS 'font' 
	 * property; values that cannot be parsed as CSS font values are ignored.
	 */
	public Surface setFont(String font) {
		canvas.setFont(font);
		return this;
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
	public Surface setTextAlign(TextAlign textAlign) {
		canvas.setTextAlign(textAlign.toString());
		return this;
	}
	
	/**
	 * Gets the text alignment settings.
	 */
	public TextAlign getTextAlign() {
		return TextAlign.valueOf(canvas.getTextAlign());
	}
	
	/**
	 * Sets the text baseline alignment settings.
	 */
	public Surface setTextBaseline(TextBaseline textBaseline) {
		canvas.setTextBaseline(textBaseline.toString());
		return this;
	}
	
	/**
	 * Gets the text baseline alignment settings.
	 */
	public TextBaseline getTextBaseline() {
		return TextBaseline.valueOf(canvas.getTextBaseline());
	}
	
	/**
	 * Renders the given text at the given (x, y).
	 */
	public Surface fillText(String text, double x, double y) {
		canvas.fillText(text, x, y);
		return this;
	}
	
	/**
	 * Renders the given text at the given position.
	 */
	public Surface fillText(String text, Vector2 position) {
		return fillText(text, position.getX(), position.getY());
	}
	
	/**
	 * Renders the given text at the given (x, y), ensuring that the text is
	 * not wider than maxWidth.
	 */
	public Surface fillText(String text, double x, double y, double maxWidth) {
		canvas.fillText(text, x, y, maxWidth);
		return this;
	}
	
	/**
	 * Renders the given text at the given position, ensuring that the text is
	 * not wider than maxWidth.
	 */
	public Surface fillText(String text, Vector2 position, double maxWidth) {
		return fillText(text, position.getX(), position.getY(), maxWidth);
	}
	
	/**
	 * Renders the given text at the given (x, y).
	 */
	public Surface strokeText(String text, double x, double y) {
		canvas.strokeText(text, x, y);
		return this;
	}
	
	/**
	 * Renders the given text at the given position.
	 */
	public Surface strokeText(String text, Vector2 position) {
		canvas.strokeText(text, position.getX(), position.getY());
		return this;
	}
	
	/**
	 * Renders the given text at the given position, ensuring that the text is
	 * not wider than maxWidth.
	 */
	public Surface strokeText(String text, Vector2 position, double maxWidth) {
		canvas.strokeText(text, position.getX(), position.getY(), maxWidth);
		return this;
	}
	
	/**
	 * Returns the advance width with the metrics of the given text in the 
	 * current font.
	 */
	public double measureText(String text) {
		return canvas.measureText(text);
	}
}
