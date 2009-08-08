package gwt.g2d.client.demo.tetris;

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.graphics.KnownColor;

/**
 * The type of block stored in a cell in the tetris matrix. 
 * Color scheme is based on Atari/Arcade
 * @see http://en.wikipedia.org/wiki/Tetris
 * 
 * @author hao1300@gmail.com
 */
public enum BlockType {
	SHAPE_I(KnownColor.RED),
	SHAPE_J(KnownColor.YELLOW),
	SHAPE_L(KnownColor.MAGENTA),
	SHAPE_O(KnownColor.BLUE),
	SHAPE_S(KnownColor.CYAN),
	SHAPE_T(KnownColor.GREEN),
	SHAPE_Z(KnownColor.ORANGE);	
	
	private Color color;
	private BlockType(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
