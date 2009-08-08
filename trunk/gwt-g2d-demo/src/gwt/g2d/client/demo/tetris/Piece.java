package gwt.g2d.client.demo.tetris;

import com.google.gwt.user.client.Random;

/**
 * A tetris piece.
 * 
 * @author hao1300@gmail.com
 */
public class Piece {
	public static final int PIECE_SIZE = PieceDefinition.PIECE_SIZE;
	private final PieceDefinition pieceDefinition;
	private int rotation;
	
	/**
	 * Creates a random tetris piece.
	 */
	public Piece() {
		pieceDefinition = PieceDefinition.randomPieceDefinition();
		rotation = Random.nextInt(PIECE_SIZE);
	}
	
	/**
	 * Gets the type of block at the given cell for the current rotation.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public BlockType getBlock(int row, int col) {
		return pieceDefinition.getBlock(rotation, row, col);
	}
	
	/**
	 * Rotates the piece to the left.
	 */
	public void rotateLeft() {
		if (--rotation < 0) {
			rotation = PieceDefinition.TYPES_PER_PIECE - 1;
		}
	}
	
	/**
	 * Rotates the piece to the right.
	 */
	public void rotateRight() {
		if (++rotation >= PieceDefinition.TYPES_PER_PIECE) {
			rotation = 0;
		}
	}
}
