package gwt.g2d.client.graphics.visitor;

import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.math.Vector2;

/**
 * Creates a new subpath with the given point.
 * The new position will be set to the given position.
 * 
 * @author hao1300@gmail.com
 */
public class MoveToVisitor implements ShapeVisitor {
	private double x, y;
	
	/**
	 * Creates a new subpath at the given position.
	 */
	public MoveToVisitor(Vector2 position) {
		this(position.getX(), position.getY());
	}

	/**
	 * Creates a new subpath at (x, y).
	 */
	public MoveToVisitor(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void visit(Surface surface) {
		surface.getCanvas().moveTo(x, y);
	}
}
