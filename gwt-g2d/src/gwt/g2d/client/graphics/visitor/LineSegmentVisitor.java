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

import gwt.g2d.client.math.Vector2;

/**
 * Adds a line to the current subpath.
 * The new position will be set to the end point of the line.
 * 
 * @author hao1300@gmail.com
 */
public class LineSegmentVisitor extends CompositeShapeVisitor {
	
	/**
	 * Adds a straight line from fromPosition to toPosition.
	 */
	public LineSegmentVisitor(Vector2 fromPosition, Vector2 toPosition) {
		this(fromPosition.getX(), fromPosition.getY(), toPosition.getX(), toPosition.getY());		
	}
	
	/**
	 * Adds a straight line from (fromX, fromY) to (toX, toY).
	 */
	public LineSegmentVisitor(double fromX, double fromY, double toX, double toY) {
		super(2);
		addAll(new MoveToVisitor(fromX, fromY), new LineToVisitor(toX, toY));
	}
}
