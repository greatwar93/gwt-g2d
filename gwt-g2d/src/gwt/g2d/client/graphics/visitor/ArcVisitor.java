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
package gwt.g2d.client.graphics.visitor;

import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.math.Arc;
import gwt.g2d.client.math.Vector2;

/**
 * Adds points to the subpath such that the arc described by the circumference 
 * of the circle described by the arguments, starting at the given start angle 
 * and ending at the given end angle, going in the given direction, is added to
 * the path, connected to the previous point by a straight line.
 * The new position will be set to the location on the arc at the end angle.
 * 
 * @author hao1300@gmail.com
 */
public class ArcVisitor implements ShapeVisitor {
	private double x, y, radius, startAngle, endAngle;
	private boolean antiClockwise;
	
	/**
	 * Adds an arc on a circle has its origin at (x, y) with the given radius.
	 * The arc starts at startAngle and ends at endAngle along the circle, 
	 * measured in radians.
	 */
	public ArcVisitor(double x, double y, double radius, double startAngle, 
			double endAngle, boolean antiClockwise) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.antiClockwise = antiClockwise;
	}
	
	/**
	 * Adds an arc on a circle has its origin at the given position with the 
	 * given radius. The arc starts at startAngle and ends at endAngle along the 
	 * circle, measured in radians.
	 */
	public ArcVisitor(Vector2 position, double radius, double startAngle,
			double endAngle, boolean antiClockwise) {
		this(position.getX(), position.getY(), radius, startAngle, 
				endAngle, antiClockwise);
	}
	
	/**
	 * Adds an arc to the current subpath.
	 */
	public ArcVisitor(Arc arc) {
		this(arc.getCenter(), arc.getRadius(), arc.getStartAngle(), 
				arc.getEndAngle(), arc.isAnticlockwise());
	}
	
	@Override
	public void visit(Surface surface) {
		surface.getContext().arc(x, y, radius, startAngle, endAngle, antiClockwise);
	}
}
