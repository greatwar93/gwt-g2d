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

import gwt.canvas.client.Canvas;
import gwt.g2d.client.math.Arc;
import gwt.g2d.client.math.Circle;
import gwt.g2d.client.math.MathHelper;
import gwt.g2d.client.math.Rectangle;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.FocusWidget;

/**
 * The surface that a game uses to render to the screen.
 * 
 * @author hao1300@gmail.com
 */
public class Surface extends FocusWidget {
	/**
	 * Composition operations.
	 */
	public enum Composition {
		SOURCE_OVER(Canvas.SOURCE_OVER),
		DESTINATION_OVER(Canvas.DESTINATION_OVER);
		
		private final String compositionName;
		
		private Composition(String compositionName) {
			this.compositionName = compositionName;
		}
		
		@Override
		public String toString() {
			return compositionName;
		}
	}
	
	/**
	 * Style to use when joining two lines.
	 */
	public enum LineJoin {
		MITER(Canvas.MITER),
		BEVEL(Canvas.BEVEL),
		ROUND(Canvas.ROUND);
		
		private final String lineJoinName;
		
		private LineJoin(String lineJoinName) {
			this.lineJoinName = lineJoinName;
		}
		
		@Override
		public String toString() {
			return lineJoinName;
		}
	}
	
	/**
	 * Style to use at the end of a line.
	 */
	public enum LineCap {
		BUTT(Canvas.BUTT),
		ROUND(Canvas.ROUND),
		SQUARE(Canvas.SQUARE);
		
		private final String lineCapName;
		
		private LineCap(String lineCapName) {
			this.lineCapName = lineCapName;
		}
		
		@Override
		public String toString() {
			return lineCapName;
		}
	}
	
	private final Canvas canvas;
	private int width, height;
	private Color backgroundColor = KnownColor.TRANSPARENT;
	private double globalAlpha = 1.0;
	private Composition compositionOperation = Composition.SOURCE_OVER;
	private LineCap lineCap = LineCap.BUTT;
	private LineJoin lineJoin = LineJoin.MITER;
	private double lineWidth, miterLimit;
	
	/**
	 * Initialize a surface of the given size.
	 * 
	 * @param width width of the surface.
	 * @param height height of the surface.
	 */
	public Surface(int width, int height) {
		canvas = new Canvas(width, height);
		setSize(width, height);
		setElement(canvas.getElement());
		lineWidth = canvas.getLineWidth();
		miterLimit = canvas.getMiterLimit();
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
		return width;
	}
	
	/**
	 * Gets the height of the surface.
	 */
	public int getHeight() {
		return height;
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
		this.width = width;
	}
	
	/**
	 * Sets the height of the surface.
	 */
	public void setHeight(int height) {
		canvas.setHeight(height);
		this.height = height;
	}
	
	/**
	 * Creates a new empty path.
	 * 
	 * @return self to support chaining.
	 */
	public Surface beginPath() {
		canvas.beginPath();
		return this;
	}
	
	/**
	 * Closes and terminates an open subpath.
	 * 
	 * @return self to support chaining.
	 */
	public Surface closePath() {
		canvas.closePath();
		return this;
	}
	
	/**
	 * Moves the current position to (x, y).
	 * 
	 * @param x
	 * @param y
	 * @return self to support chaining.
	 */
	public Surface moveTo(double x, double y) {
		canvas.moveTo(x, y);
		return this;
	}
	
	/**
	 * Moves the current position to the given position.
	 * 
	 * @param position
	 * @return self to support chaining.
	 */
	public Surface moveTo(Vector2 position) {
		return moveTo(position.getX(), position.getY());
	}
	
	/**
	 * Draw a line from the current position to position (x, y).
	 * The new position is at (x, y).
	 * 
	 * @param x
	 * @param y
	 * @return self to support chaining.
	 */
	public Surface drawLineTo(double x, double y) {
		canvas.lineTo(x, y);
		return this;
	}
	
	/**
	 * Makes a line from the current position to the given position.
	 * The new position is at the given position.
	 * 
	 * @param position
	 * @return self to support chaining.
	 */
	public Surface drawLineTo(Vector2 position) {
		return drawLineTo(position.getX(), position.getY());
	}
	
	/**
	 * Draws a line segment from the starting point to the end point.
	 * The new position is at (endPointX, endPointY).
	 * 
	 * @param startPointX starting point x-coordinate.
	 * @param startPointY starting point y-coordinate.
	 * @param endPointX end point x-coordinate.
	 * @param endPointY end point y-coordinate.
	 * @return self to support chaining.
	 */
	public Surface drawLineSegment(double startPointX, double startPointY,
			double endPointX, double endPointY) {
		return moveTo(startPointX, startPointY).drawLineTo(endPointX, endPointY);
	}
	
	/**
	 * Draws a line segment from the starting point to the end point.
	 * The new position is at the given endPoint.
	 * 
	 * @param startPoint
	 * @param endPoint
	 * @return self to support chaining.
	 */
	public Surface drawLineSegment(Vector2 startPoint, Vector2 endPoint) {
		return moveTo(startPoint).drawLineTo(endPoint);
	}
	
	/**
	 * Adds an arc of a circle to the current subpath.
	 * The new position will be set to the location on the arc at the end angle.
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param startAngle
	 * @param endAngle
	 * @param antiClockwise
	 * @return self to support chaining.
	 */
	public Surface drawArc(double x, double y, double radius , double startAngle,
			double endAngle, boolean antiClockwise) {
		canvas.arc(x, y, radius, startAngle, endAngle, antiClockwise);
		return this;
	}
	
	/**
	 * Adds an arc of a circle to the current subpath.
	 * 
	 * @param position
	 * @param radius
	 * @param startAngle
	 * @param endAngle
	 * @param antiClockwise
	 * @return self to support chaining.
	 */
	public Surface drawArc(Vector2 position, double radius, double startAngle,
			double endAngle, boolean antiClockwise) {
		return drawArc(position.getX(), position.getY(), radius, startAngle, endAngle, 
				antiClockwise);
	}
	
	/**
	 * Adds an arc of a circle to the current subpath.
	 * 
	 * @param arc
	 * @return self to support chaining.
	 */
	public Surface drawArc(Arc arc) {
		return drawArc(arc.getCenter(), arc.getRadius(), arc.getStartAngle(), 
				arc.getEndAngle(), arc.isAnticlockwise());
	}
	
	/**
	 * Adds a circle at (x, y) with the given radius to the subpath.
	 * The new position will be set to the location on the arc at 0 degree.
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @return self to support chaining.
	 */
	public Surface drawCircle(double x, double y, double radius) {
		return drawArc(x, y, radius, 0, MathHelper.TWO_PI, true);
	}
	
	/**
	 * Adds a circle at the given center with the given radius to the subpath.
	 * 
	 * @param center
	 * @param radius
	 * @return self to support chaining.
	 */
	public Surface drawCircle(Vector2 center, double radius) {
		return drawCircle(center.getX(), center.getY(), radius);
	}
	
	/**
	 * Adds a circle to the subpath.
	 * 
	 * @param circle
	 * @return self to support chaining.
	 */
	public Surface drawCircle(Circle circle) {
		return drawCircle(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
	}
	
	/**
	 * Appends a Bezier curve to the current path with the current position
	 * as the start point.
	 * The new position is at (x, y).
	 * 
	 * @param controlPoint1X control point 1 x-coordinate.
	 * @param controlPoint1Y control point 1 y-coordinate.
	 * @param controlPoint2X control point 2 x-coordinate.
	 * @param controlPoint2Y control point 2 y-coordinate.
	 * @param endPointX new end point x-coordinate.
	 * @param endPointY new end point y-coordinate.
	 * @return self to support chaining.
	 */
	public Surface drawCubicCurveTo(double controlPoint1X, double controlPoint1Y, 
			double controlPoint2X, double controlPoint2Y, double endPointX, double endPointY) {
		canvas.cubicCurveTo(controlPoint1X, controlPoint1Y, controlPoint2X, 
				controlPoint2Y, endPointX, endPointY);
		return this;
	}
	
	/**
	 * Appends a Bezier curve to the current path.
	 * The new position is at the given endPoint.
	 * 
	 * @param controlPoint1 control point 1.
	 * @param controlPoint2 control point 2.
	 * @param endPoint new end point.
	 * @return self to support chaining.
	 */
	public Surface drawCubicCurveTo(Vector2 controlPoint1, Vector2 controlPoint2, 
			Vector2 endPoint) {
		return drawCubicCurveTo(controlPoint1.getX(), controlPoint1.getY(), 
				controlPoint2.getX(), controlPoint2.getY(), endPoint.getX(), endPoint.getY());
	}
	
	/**
	 * Appends a Bezier curve to the given starting point.
	 * The new position is at the given endPoint.
	 * 
	 * @param startPoint the starting point of the curve.
	 * @param controlPoint1 control point 1.
	 * @param controlPoint2 control point 2.
	 * @param endpoint new end point.
	 * @return self to support chaining.
	 */
	public Surface drawCubicCurve(Vector2 startPoint, Vector2 controlPoint1, 
			Vector2 controlPoint2, Vector2 endpoint) {
		return moveTo(startPoint).drawCubicCurveTo(controlPoint1, controlPoint2, endpoint);
	}
	
	/**
	 * Appends a quadratic Bezier curve to the current path.
	 * The new position is at (x, y).
	 * 
	 * @param controlPointX control point x-coordinate.
	 * @param controlPointY control point y-coordinate.
	 * @param endPointX new end point x-coordinate.
	 * @param endPointY new end point x-coordinate.
	 * @return self to support chaining.
	 */
	public Surface drawQuadraticCurveTo(double controlPointX, double controlPointY, 
			double endPointX, double endPointY) {
		canvas.quadraticCurveTo(controlPointX, controlPointY, endPointX, endPointY);
		return this;
	}
	
	/**
	 * Appends a quadratic Bezier curve to the current path.
	 * The new position is at the given endPoint.
	 * 
	 * @param controlPoint
	 * @param endPoint
	 * @return self to support chaining.
	 */
	public Surface drawQuadraticCurveTo(Vector2 controlPoint, Vector2 endPoint) {
		return drawQuadraticCurveTo(controlPoint.getX(), controlPoint.getY(), 
				endPoint.getX(), endPoint.getY());
	}
	
	/**
	 * Appends a quadratic Bezier curve from the given starting point.
	 * The new position is at the given endPoint.
	 * 
	 * @param startPoint
	 * @param controlPoint
	 * @param endPoint
	 * @return self to support chaining.
	 */
	public Surface drawQuadraticCurve(Vector2 startPoint, Vector2 controlPoint, 
			Vector2 endPoint) {
		return moveTo(startPoint).drawQuadraticCurveTo(controlPoint, endPoint);
	}
	
	/**
	 * Appends a rectangle to the path.
	 * The position will be moved to (0, 0) after the operation.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return self to support chaining.
	 */
	public Surface drawRectangle(double x, double y, double width, double height) {
		canvas.rect(x, y, width, height);
		return this;
	}
	
	/**
	 * Appends a rectangle to the path.
	 * The position will be moved to (0, 0) after the operation.
	 * 
	 * @param rectangle
	 * @return self to support chaining.
	 */
	public Surface drawRectangle(Rectangle rectangle) {
		return drawRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), 
				rectangle.getHeight());
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
		return drawImage(image, x, y, image.getWidth(), image.getHeight());
	}
	
	/**
	 * Draws the image at the given position.
	 * 
	 * @param image the image to draw.
	 * @param pos the position to draw the image.
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
		canvas.drawImage(image, 0, 0, width, height, x, y, width, height);
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
	 * Fills in the given rectangle.
	 * The position will be moved to (0, 0) after the operation.
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
	 * Fills in the given rectangle.
	 * The position will be moved to (0, 0) after the operation.
	 * 
	 * @param rectangle
	 * @return self to support chaining.
	 */
	public Surface fillRectangle(Rectangle rectangle) {
		return fillRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
				rectangle.getHeight());
	}
	
	/**
	 * Stroke the given rectangle.
	 * The position will be moved to (0, 0) after the operation.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return self to support chaining.
	 */
	public Surface strokeRectangle(double x, double y, double width, double height) {
		canvas.strokeRect(x, y, width, height);
		return this;
	}
	
	/**
	 * Stroke the given rectangle.
	 * The position will be moved to (0, 0) after the operation.
	 * 
	 * @param rectangle
	 * @return self to support chaining.
	 */
	public Surface strokeRectangle(Rectangle rectangle) {
		return strokeRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
				rectangle.getHeight());
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
		canvas.setFillStyle(gradient.createNativeGradient());
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
	 * Paints the area within the current path using the given color.
	 * 
	 * @return self to support chaining.
	 */
	public Surface fill(Color color) {
		return setFillStyle(color).fill();
	}
	
	/**
	 * Paints the area within the current path using the given gradient.
	 * 
	 * @return self to support chaining.
	 */
	public Surface fill(Gradient gradient) {
		return setFillStyle(gradient).fill();
	}
	
	/**
	 * Sets the line cap style.
	 * Default: LineCap.BUTT.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setLineCap(LineCap lineCap) {
		this.lineCap = lineCap;
		canvas.setLineCap(lineCap.toString());
		return this;
	}
	
	/**
	 * Sets the line join style.
	 * Default: LineJoin.MITER.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setLineJoin(LineJoin lineJoin) {
		this.lineJoin = lineJoin;
		canvas.setLineJoin(lineJoin.toString());
		return this;
	}
	
	/**
	 * Sets the width of the line in a stroke operation.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
		canvas.setLineWidth(lineWidth);		
		return this;
	}
	
	/**
	 * Sets the miter limit.
	 * The miterLimit property determines how far the outside connection point 
	 * can be placed from the inside connection point. If two lines exceed this 
	 * value, a bevel join will be drawn.
	 * 
	 * @return self to support chaining.
	 */
	public Surface setMiterLimit(double miterLimit) {
		this.miterLimit = miterLimit;
		canvas.setMiterLimit(miterLimit);
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
		canvas.setStrokeStyle(gradient.createNativeGradient());
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
	 * Paints a line along the current path with the given style.
	 * 
	 * @return self to support chaining.
	 */
	public Surface stroke(Color color) {
		return setStrokeStyle(color).stroke();
	}
	
	/**
	 * Paints a line along the current path with the given style.
	 * 
	 * @return self to support chaining.
	 */
	public Surface stroke(Gradient gradient) {
		return setStrokeStyle(gradient).stroke();
	}
	
	/**
	 * Sets the background color of the surface.
	 * 
	 * @param color background color for the surface.
	 */
	public Surface setBackgroundColor(Color color) {
		this.backgroundColor = color;
		canvas.setBackgroundColor(color.getColorCode());	
		return this;
	}	
	
	/**
	 * Sets the global alpha.
	 * Default: 1.0.
	 * 
	 * @param alpha
	 * @return self to support chaining.
	 */
	public Surface setGlobalAlpha(double alpha) {
		this.globalAlpha = alpha;
		canvas.setGlobalAlpha(alpha);
		return this;
	}
	
	/**
	 * Sets the global composition operation.
	 * Default: SOURCE_OVER.
	 * 
	 * @param compositionOperation
	 * @return self to support chaining.
	 */
	public Surface setGlobalCompositionOperation(Composition compositionOperation) {
		this.compositionOperation = compositionOperation;
		canvas.setGlobalCompositeOperation(compositionOperation.toString());
		return this;
	}
	
	/**
	 * Saves the current surface state to the top of a stack.
	 * Call restore() will restore the current surface state to the previously
	 * saved state.
	 * 
	 * @return self to support chaining.
	 */
	public Surface save() {
		canvas.save();
		return this;
	}
	
	/**
	 * Restore the surface state to the previously saved state by popping from
	 * the top of the graphics state stack.
	 * save() must be called before calling restore().
	 * 
	 * @return self to support chaining.
	 */
	public Surface restore() {
		canvas.restore();
		return this;
	}
	
	/**
	 * Clears the surface.
	 * 
	 * @return self to support chaining.
	 */
	public Surface clear() {
		canvas.clear();
		return this;
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
	 * Transform by the given transformation matrix.
	 * 
	 * The matrix has the following structure:
	 * <pre> 
   * m11   m21   dx
   * m12   m22   dy
   * 0      0     1 
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
	 * Gets the background color.
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	/**
	 * Gets the global alpha value.
	 */
	public double getGlobalAlpha() {
		return globalAlpha;
	}
	
	/**
	 * Gets the global composition operation.
	 */
	public Composition getGlobalCompositionOperation() {
		return compositionOperation;
	}
	
	/**
	 * Gets the line cap for the stroke.
	 */
	public LineCap getLineCap() {
		return lineCap;
	}
	
	/**
	 * Gets the line join for the strok.
	 */
	public LineJoin getLineJoin() {
		return lineJoin;
	}
	
	/**
	 * Gets the line width.
	 */
	public double getLineWidth() {
		return lineWidth;
	}
	
	/**
	 * Gets the miter limit.
	 */
	public double getMiterLimit() {
		return miterLimit;
	}
	
	/**
	 * Gets the linear gradient between two points.
	 */
	public Gradient createLinearGradient(double x0, double y0, 
			double x1, double y1) {
		return new Gradient(canvas.createLinearGradient(x0, y0, x1, y1));
	}
	
	/**
	 * Gets the linear gradient between two points.
	 */
	public Gradient createLinearGradient(Vector2 point0, Vector2 point1) {
		return createLinearGradient(point0.getX(), point0.getY(), point1.getX(), 
				point1.getY());
	}
	
	/**
	 * Gets the underlying radial gradient implementation.
	 */
	public Gradient createRadialGradient(double x0, double y0, 
			double radius0, double x1, double y1, double radius1) {
		return new Gradient(canvas.createRadialGradient(x0, y0, radius0, x1, y1, radius1));
	}
	
	/**
	 * Gets the underlying radial gradient implementation.
	 */
	public Gradient createRadialGradient(Vector2 point0, double radius0, 
			Vector2 point1, double radius1) {
		return createRadialGradient(point0.getX(), point0.getY(), radius0, 
				point1.getX(), point1.getY(), radius1);
	}
}
