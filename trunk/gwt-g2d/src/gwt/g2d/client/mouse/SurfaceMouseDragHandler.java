package gwt.g2d.client.mouse;

import gwt.g2d.shared.math.Vector2;

public interface SurfaceMouseDragHandler {
	public void onMouseDrag(Vector2 start, Vector2 stop, Long startId, Long stopId);
}
