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
import gwt.g2d.client.graphics.canvas.CanvasAdapter;
import gwt.g2d.client.graphics.visitor.ArcVisitor;
import gwt.g2d.client.graphics.visitor.CircleVisitor;
import gwt.g2d.client.graphics.visitor.CubicCurveToVisitor;
import gwt.g2d.client.graphics.visitor.CubicCurveVisitor;
import gwt.g2d.client.graphics.visitor.LineSegmentVisitor;
import gwt.g2d.client.graphics.visitor.LineToVisitor;
import gwt.g2d.client.graphics.visitor.MoveToVisitor;
import gwt.g2d.client.graphics.visitor.QuadraticCurveToVisitor;
import gwt.g2d.client.graphics.visitor.QuadraticCurveVisitor;
import gwt.g2d.client.graphics.visitor.ShapeVisitor;
import gwt.g2d.client.math.Arc;
import gwt.g2d.client.math.Circle;
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
	public ShapeBuilder append(ShapeVisitor shapeVisitor) {
		shapes.add(shapeVisitor);
		return this;
	}
	
	/**
	 * @see {@link MoveToVisitor#MoveToVisitor(double, double)}.
	 */
	public ShapeBuilder moveTo(double x, double y) {
		return append(new MoveToVisitor(x, y));
	}
	
	/**
	 * @see {@link MoveToVisitor#MoveToVisitor(Vector2)}.
	 */
	public ShapeBuilder moveTo(Vector2 position) {
		return append(new MoveToVisitor(position));
	}
	
	/**
	 * @see {@link LineToVisitor#LineToVisitor(double, double)}.
	 */
	public ShapeBuilder drawLineTo(double x, double y) {
		return append(new LineToVisitor(x, y));
	}
	
	/**
	 * @see {@link LineToVisitor#LineToVisitor(Vector2)}.
	 */
	public ShapeBuilder drawLineTo(Vector2 position) {
		return append(new LineToVisitor(position));
	}
	
	/**
	 * @see {@link LineSegmentVisitor#LineSegmentVisitor(double, double, double, 
	 * double)}.
	 */
	public ShapeBuilder drawLineSegment(double fromX, double fromY, double toX, 
			double toY) {
		return append(new LineSegmentVisitor(fromX, fromY, toX, toY));
	}
	
	/**
	 * @see {@link LineSegmentVisitor#LineSegmentVisitor(Vector2, Vector2)}.
	 */
	public ShapeBuilder drawLineSegment(Vector2 fromPosition, Vector2 toPosition) {
		return append(new LineSegmentVisitor(fromPosition, toPosition));
	}
	
	/**
	 * @see {@link ArcVisitor#ArcVisitor(double, double, double, double, double, 
	 * boolean)}.
	 */
	public ShapeBuilder drawArc(double x, double y, double radius, 
			double startAngle, double endAngle, boolean antiClockwise) {
		return append(new ArcVisitor(x, y, radius, startAngle, endAngle, antiClockwise));
	}
	
	/**
	 * @see {@link ArcVisitor#ArcVisitor(Vector2, double, double, double, 
	 * boolean)}.
	 */
	public ShapeBuilder drawArc(Vector2 position, double radius, double startAngle,
			double endAngle, boolean antiClockwise) {
		return append(new ArcVisitor(position, radius, startAngle, endAngle, 
				antiClockwise));
	}
	
	/**
	 * @see {@link ArcVisitor#ArcVisitor(Arc)}.
	 */
	public ShapeBuilder drawArc(Arc arc) {
		return append(new ArcVisitor(arc));
	}
	
	/**
	 * @see {@link CircleVisitor#CircleVisitor(double, double, double)}.
	 */
	public ShapeBuilder drawCircle(double x, double y, double radius) {
		return append(new CircleVisitor(x, y, radius));
	}
	
	/**
	 * @see {@link CircleVisitor#CircleVisitor(Vector2, double)}.
	 */
	public ShapeBuilder drawCircle(Vector2 center, double radius) {
		return append(new CircleVisitor(center, radius));
	}
	
	/**
	 * @see {@link CircleVisitor#CircleVisitor(Circle)}.
	 */
	public ShapeBuilder drawCircle(Circle circle) {
		return append(new CircleVisitor(circle));
	}
	
	/**
	 * @see {@link CubicCurveToVisitor#CubicCurveToVisitor(double, double, 
	 * double, double, double, double)}.
	 */
	public ShapeBuilder drawCubicCurveTo(double controlPoint1X, double controlPoint1Y, 
			double controlPoint2X, double controlPoint2Y, double endPointX, double endPointY) {
		return append(new CubicCurveToVisitor(controlPoint1X, controlPoint1Y, 
				controlPoint2X, controlPoint2Y, 
				endPointX, endPointY));
	}
	
	/**
	 * @see {@link CubicCurveToVisitor#CubicCurveToVisitor(Vector2, Vector2, 
	 * Vector2)}.
	 */
	public ShapeBuilder drawCubicCurveTo(Vector2 controlPoint1, Vector2 controlPoint2, 
			Vector2 endPoint) {
		return append(new CubicCurveToVisitor(controlPoint1, controlPoint2, endPoint));
	}
	
	/**
	 * @see {@link CubicCurveVisitor#CubicCurveVisitor(Vector2, Vector2, Vector2, 
	 * Vector2)}.
	 */
	public ShapeBuilder drawCubicCurve(double startPointX, double startPointY,
			double controlPoint1X, double controlPoint1Y, 
			double controlPoint2X, double controlPoint2Y, 
			double endPointX, double endPointY) {
		return append(new CubicCurveVisitor(startPointX, startPointY, 
				controlPoint1X, controlPoint1Y, 
				controlPoint2X, controlPoint2Y, 
				endPointX, endPointY));
	}
	
	/**
	 * @see {@link CubicCurveVisitor#CubicCurveVisitor(double, double, double, 
	 * double, double, double, double, double)}.
	 */
	public ShapeBuilder drawCubicCurve(Vector2 startPoint, Vector2 controlPoint1, 
			Vector2 controlPoint2, Vector2 endpoint) {
		return append(new CubicCurveVisitor(startPoint, controlPoint1, 
				controlPoint2, endpoint));
	}
	
	/**
	 * @see {@link QuadraticCurveToVisitor#QuadraticCurveToVisitor(double, double, 
	 * double, double)}.
	 */
	public ShapeBuilder drawQuadraticCurveTo(double controlPointX, 
			double controlPointY, double endPointX, double endPointY) {
		return append(new QuadraticCurveToVisitor(controlPointX, controlPointY, 
				endPointX, endPointY));
	}
	
	/**
	 * @see {@link QuadraticCurveToVisitor#QuadraticCurveToVisitor(Vector2, 
	 * Vector2)}.
	 */
	public ShapeBuilder drawQuadraticCurveTo(Vector2 controlPoint, Vector2 endPoint) {
		return append(new QuadraticCurveToVisitor(controlPoint, endPoint));

	}
	
	/**
	 * @see {@link QuadraticCurveVisitor#QuadraticCurveVisitor(double, double, 
	 * double, double, double, double)}.
	 */
	public ShapeBuilder drawQuadraticCurveTo(double startPointX, double startPointY,
			double controlPointX, double controlPointY, double endPointX, double endPointY) {
		return append(new QuadraticCurveVisitor(startPointX, startPointY, 
				controlPointX, controlPointY, endPointX, endPointY));
	}
	
	/**
	 * @see {@link QuadraticCurveVisitor#QuadraticCurveVisitor(Vector2, Vector2, 
	 * Vector2)}.
	 */
	public ShapeBuilder drawQuadraticCurve(Vector2 startPoint, Vector2 controlPoint, 
			Vector2 endPoint) {
		return append(new QuadraticCurveVisitor(startPoint, controlPoint, endPoint));
	}
	
	/**
	 * Builds the customized shape.
	 */
	public Shape build() {
		return new CustomShape();
	}
	
	/**
	 * Represents a custom shape.
	 */
	public class CustomShape extends Shape {
		@Override
		public void draw(Surface surface) {
			CanvasAdapter canvas = surface.getCanvas();
			canvas.beginPath();
			for (ShapeVisitor shape : shapes) {
				shape.visit(surface);
			}
			canvas.closePath();
		}
	}
}
