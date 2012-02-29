package gwt.g2d.client.mouse;

import gwt.g2d.shared.math.Vector2;

public interface SurfaceMouseUpHandler {
	public void onMouseUp(Vector2 pos, Long id);
	public void onMouseUpNothing(Vector2 pos);
}
