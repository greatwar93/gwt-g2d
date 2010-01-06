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
package gwt.g2d.client.graphics.shapes;

import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.canvas.Context;
import gwt.g2d.client.graphics.visitor.ArcVisitor;
import gwt.g2d.client.graphics.visitor.CircleVisitor;
import gwt.g2d.client.graphics.visitor.CubicCurveToVisitor;
import gwt.g2d.client.graphics.visitor.CubicCurveVisitor;
import gwt.g2d.client.graphics.visitor.LineSegmentVisitor;
import gwt.g2d.client.graphics.visitor.LineToVisitor;
import gwt.g2d.client.graphics.visitor.MoveToVisitor;
import gwt.g2d.client.graphics.visitor.QuadraticCurveToVisitor;
import gwt.g2d.client.graphics.visitor.QuadraticCurveVisitor;
import gwt.g2d.client.graphics.visitor.RotateVisitor;
import gwt.g2d.client.graphics.visitor.ScaleVisitor;
import gwt.g2d.client.graphics.visitor.SetTransformVisitor;
import gwt.g2d.client.graphics.visitor.ShapeVisitor;
import gwt.g2d.client.graphics.visitor.TransformVisitor;
import gwt.g2d.client.graphics.visitor.TranslateVisitor;
import gwt.g2d.client.math.Arc;
import gwt.g2d.client.math.Circle;
import gwt.g2d.client.math.Matrix;
import gwt.g2d.client.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder for drawing custom shapes.
 * 
 * @author hao1300@gmail.com
 */
public class ShapeBuilder {
	private List<ShapeVisitor> shapes = new ArrayList<ShapeVisitor>();
	
	/**
	 * Appends the given shape visitor to the builder.
	 * 
	 * @param shapeVisitor the shape visitor to be added.
	 * @return self to support chaining.
	 */
	public final ShapeBuilder append(ShapeVisitor shapeVisitor) {
		shapes.add(shapeVisitor);
		return this;
	}
	
	/**
	 * @see MoveToVisitor#MoveToVisitor(double, double)
	 */
	public final ShapeBuilder moveTo(double x, double y) {
		return append(new MoveToVisitor(x, y));
	}
	
	/**
	 * @see MoveToVisitor#MoveToVisitor(Vector2)
	 */
	public final ShapeBuilder moveTo(Vector2 position) {
		return append(new MoveToVisitor(position));
	}
	
	/**
	 * @see LineToVisitor#LineToVisitor(double, double)
	 */
	public final ShapeBuilder drawLineTo(double x, double y) {
		return append(new LineToVisitor(x, y));
	}
	
	/**
	 * @see LineToVisitor#LineToVisitor(Vector2)
	 */
	public final ShapeBuilder drawLineTo(Vector2 position) {
		return append(new LineToVisitor(position));
	}
	
	/**
	 * @see LineSegmentVisitor#LineSegmentVisitor(double, double, double, double)
	 */
	public final ShapeBuilder drawLineSegment(double fromX, double fromY, double toX, 
			double toY) {
		return append(new LineSegmentVisitor(fromX, fromY, toX, toY));
	}
	
	/**
	 * @see LineSegmentVisitor#LineSegmentVisitor(Vector2, Vector2)
	 */
	public final ShapeBuilder drawLineSegment(Vector2 fromPosition, Vector2 toPosition) {
		return append(new LineSegmentVisitor(fromPosition, toPosition));
	}
	
	/**
	 * @see ArcVisitor#ArcVisitor(double, double, double, double, double, boolean)
	 */
	public final ShapeBuilder drawArc(double x, double y, double radius, 
			double startAngle, double endAngle, boolean antiClockwise) {
		return append(new ArcVisitor(x, y, radius, startAngle, endAngle, antiClockwise));
	}
	
	/**
	 * @see ArcVisitor#ArcVisitor(Vector2, double, double, double, boolean)
	 */
	public final ShapeBuilder drawArc(Vector2 position, double radius, double startAngle,
			double endAngle, boolean antiClockwise) {
		return append(new ArcVisitor(position, radius, startAngle, endAngle, 
				antiClockwise));
	}
	
	/**
	 * @see ArcVisitor#ArcVisitor(Arc)
	 */
	public final ShapeBuilder drawArc(Arc arc) {
		return append(new ArcVisitor(arc));
	}
	
	/**
	 * @see CircleVisitor#CircleVisitor(double, double, double)
	 */
	public final ShapeBuilder drawCircle(double x, double y, double radius) {
		return append(new CircleVisitor(x, y, radius));
	}
	
	/**
	 * @see CircleVisitor#CircleVisitor(Vector2, double)
	 */
	public final ShapeBuilder drawCircle(Vector2 center, double radius) {
		return append(new CircleVisitor(center, radius));
	}
	
	/**
	 * @see CircleVisitor#CircleVisitor(Circle)
	 */
	public final ShapeBuilder drawCircle(Circle circle) {
		return append(new CircleVisitor(circle));
	}
	
	/**
	 * @see CubicCurveToVisitor#CubicCurveToVisitor(double, double, double, 
	 * double, double, double)
	 */
	public final ShapeBuilder drawCubicCurveTo(double controlPoint1X, double controlPoint1Y, 
			double controlPoint2X, double controlPoint2Y, double endPointX, double endPointY) {
		return append(new CubicCurveToVisitor(controlPoint1X, controlPoint1Y, 
				controlPoint2X, controlPoint2Y, 
				endPointX, endPointY));
	}
	
	/**
	 * @see CubicCurveToVisitor#CubicCurveToVisitor(Vector2, Vector2, Vector2)
	 */
	public final ShapeBuilder drawCubicCurveTo(Vector2 controlPoint1, Vector2 controlPoint2, 
			Vector2 endPoint) {
		return append(new CubicCurveToVisitor(controlPoint1, controlPoint2, endPoint));
	}
	
	/**
	 * @see CubicCurveVisitor#CubicCurveVisitor(Vector2, Vector2, Vector2, 
	 * Vector2)
	 */
	public final ShapeBuilder drawCubicCurve(double startPointX, double startPointY,
			double controlPoint1X, double controlPoint1Y, 
			double controlPoint2X, double controlPoint2Y, 
			double endPointX, double endPointY) {
		return append(new CubicCurveVisitor(startPointX, startPointY, 
				controlPoint1X, controlPoint1Y, 
				controlPoint2X, controlPoint2Y, 
				endPointX, endPointY));
	}
	
	/**
	 * @see CubicCurveVisitor#CubicCurveVisitor(double, double, double, double, 
	 * double, double, double, double)
	 */
	public final ShapeBuilder drawCubicCurve(Vector2 startPoint, Vector2 controlPoint1, 
			Vector2 controlPoint2, Vector2 endpoint) {
		return append(new CubicCurveVisitor(startPoint, controlPoint1, 
				controlPoint2, endpoint));
	}
	
	/**
	 * @see QuadraticCurveToVisitor#QuadraticCurveToVisitor(double, double, 
	 * double, double)
	 */
	public final ShapeBuilder drawQuadraticCurveTo(double controlPointX, 
			double controlPointY, double endPointX, double endPointY) {
		return append(new QuadraticCurveToVisitor(controlPointX, controlPointY, 
				endPointX, endPointY));
	}
	
	/**
	 * @see QuadraticCurveToVisitor#QuadraticCurveToVisitor(Vector2, Vector2)
	 */
	public final ShapeBuilder drawQuadraticCurveTo(Vector2 controlPoint, Vector2 endPoint) {
		return append(new QuadraticCurveToVisitor(controlPoint, endPoint));

	}
	
	/**
	 * @see QuadraticCurveVisitor#QuadraticCurveVisitor(double, double, double, 
	 * double, double, double)
	 */
	public final ShapeBuilder drawQuadraticCurveTo(double startPointX, double startPointY,
			double controlPointX, double controlPointY, double endPointX, double endPointY) {
		return append(new QuadraticCurveVisitor(startPointX, startPointY, 
				controlPointX, controlPointY, endPointX, endPointY));
	}
	
	/**
	 * @see QuadraticCurveVisitor#QuadraticCurveVisitor(Vector2, Vector2, Vector2)
	 */
	public final ShapeBuilder drawQuadraticCurve(Vector2 startPoint, Vector2 controlPoint, 
			Vector2 endPoint) {
		return append(new QuadraticCurveVisitor(startPoint, controlPoint, endPoint));
	}
	
	/**
	 * @see ScaleVisitor#ScaleVisitor(double, double)
	 */
	public final ShapeBuilder scale(double x, double y) {
		return append(new ScaleVisitor(x, y));
	}
	
	/**
	 * @see ScaleVisitor#ScaleVisitor(Vector2)
	 */
	public final ShapeBuilder scale(Vector2 scales) {
		return append(new ScaleVisitor(scales));
	}
	
	/**
	 * @see ScaleVisitor#ScaleVisitor(double)
	 */
	public final ShapeBuilder scale(double scale) {
		return append(new ScaleVisitor(scale));
	}
	
	/**
	 * Clockwise rotation.
	 * @see RotateVisitor#RotateVisitor(double)
	 */
	public final ShapeBuilder rotate(double angle) {
		return append(new RotateVisitor(angle));
	}
	
	/**
	 * Counter-clockwise rotation.
	 * @see RotateVisitor#RotateVisitor(double)
	 */
	public final ShapeBuilder rotateCcw(double angle) {
		return append(new RotateVisitor(-angle));
	}
	
	/**
	 * @see TranslateVisitor#TranslateVisitor(double, double)
	 */
	public final ShapeBuilder translate(double x, double y) {
		return append(new TranslateVisitor(x, y));
	}
	
	/**
	 * @see TranslateVisitor#TranslateVisitor(Vector2)
	 */
	public final ShapeBuilder translate(Vector2 translation) {
		return append(new TranslateVisitor(translation));
	}
	
	/**
	 * @see TransformVisitor#TransformVisitor(double, double, double, double, 
	 * 			double, double)
	 */
	public final ShapeBuilder transform(double m11, double m12, double m21, double m22,
      double dx, double dy) {
		return append(new TransformVisitor(m11, m12, m21, m22, dx, dy));
	}
	
	/**
	 * @see TransformVisitor#TransformVisitor(Matrix)
	 */
	public final ShapeBuilder transform(Matrix matrix) {
		return append(new TransformVisitor(matrix));
	}
	
	/**
	 * @see SetTransformVisitor#SetTransformVisitor(double, double, double, 
	 * 			double, double, double)
	 */
	public final ShapeBuilder setTransform(double m11, double m12, double m21, 
			double m22, double dx, double dy) {
		return append(new SetTransformVisitor(m11, m12, m21, m22, dx, dy));
	}
	
	/**
	 * @see SetTransformVisitor#SetTransformVisitor(Matrix)
	 */
	public final ShapeBuilder setTransform(Matrix matrix) {
		return append(new SetTransformVisitor(matrix));
	}
	
	/**
	 * Builds the customized shape.
	 */
	public final Shape build() {
		return new CustomShape();
	}
	
	/**
	 * Represents a custom shape.
	 */
	public final class CustomShape extends Shape {
		@Override
		public final void draw(Surface surface) {
			Context context = surface.getContext();
			context.beginPath();
			for (ShapeVisitor shape : shapes) {
				shape.visit(surface);
			}
			context.closePath();
		}
	}
}