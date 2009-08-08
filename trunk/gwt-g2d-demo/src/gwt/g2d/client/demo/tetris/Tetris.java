package gwt.g2d.client.demo.tetris;

import gwt.g2d.client.framework.Game;
import gwt.g2d.client.graphics.KnownColor;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.math.Rectangle;
import gwt.g2d.client.math.Vector2;
import gwt.g2d.client.util.Cycle;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A game of tetris.
 * 
 * @author hao1300@gmail.com
 */
public class Tetris extends Game {
	private static final int DEFAULT_NUM_ROWS = 20, DEFAULT_NUM_COLS = 10;
	private static final int BLOCK_PIXEL_SIZE = 24;
	private static final int DEFAULT_START_ROW = 0, 
			DEFAULT_START_COL = DEFAULT_NUM_COLS / 2 - Piece.PIECE_SIZE / 2;
	private static final int ROWS_CLEARED_PER_LEVEL = 30;
	
	private final TetrisRenderer renderer = new TetrisRenderer();
	private final Surface nextPieceSurface = new Surface(Piece.PIECE_SIZE * BLOCK_PIXEL_SIZE,
			Piece.PIECE_SIZE * BLOCK_PIXEL_SIZE);
	private final Label levelLabel = new Label();
	private final Label rowsClearedLabel = new Label();
	private final Panel parentContainer;
	
	private int currRow, currCol;
	private Piece currPiece, nextPiece;
	private TetrisMatrix matrix;
	private Cycle cycle; 
	private int level;
	private int totalRowsCleared;
	private int levelOffset = 0;
	private boolean needRedraw = true, needRedrawNextPiece = true;
	
	public Tetris(int startingLevel, Panel parentContainer) {
		super(DEFAULT_NUM_COLS * BLOCK_PIXEL_SIZE, 
				DEFAULT_NUM_ROWS * BLOCK_PIXEL_SIZE);
		this.parentContainer = parentContainer;
		matrix = new TetrisMatrix(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLS);
		this.levelOffset = startingLevel;
		setLevel(startingLevel);
		setTotalRowsCleared(0);
		nextPiece = new Piece();
	}
	
	@Override
	public void initialize() {
		Surface surface = getPrimarySurface();
		
		DockPanel panel = new DockPanel();
		surface.setStyleName("mainPanel");
		panel.add(surface, DockPanel.LINE_START);
		final Button resetButton = new Button("Reset", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				currPiece = null;
				nextPiece = new Piece();
				matrix = new TetrisMatrix(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLS);
				setLevel(getLevelFromRowsCleared());
				setTotalRowsCleared(0);
				getPrimarySurface().setFocus(true);
			}
		});
		Button previousLevelButton = new Button("Previous level", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				levelOffset = Math.max(1, levelOffset - 1);
				resetButton.click();
			}
		});
		Button nextLevelButton = new Button("Next level", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				levelOffset++;
				resetButton.click();
			}
		});
		
		Panel nextPiecePanel = createPanel(
				createPanel(createHeaderLabel("Next Piece"), nextPieceSurface),
				createPanel(createHeaderLabel("Lines Cleared"), rowsClearedLabel),
				createPanel(createHeaderLabel("Level"), levelLabel),
				createPanel(resetButton),
				createPanel(previousLevelButton),
				createPanel(nextLevelButton));
		nextPiecePanel.setStyleName("sidePanel");
		nextPiecePanel.setHeight(surface.getHeight() + "px");
		panel.add(nextPiecePanel, DockPanel.LINE_END);
		parentContainer.add(panel);
		
		nextPieceSurface.setBackgroundColor(KnownColor.BLACK);
		
		surface.setFocus(true);
		surface.setBackgroundColor(KnownColor.BLACK);
		
		initializeKeyHandlers();
	}

	@Override
	public void update() {
		if (currPiece == null) {
			currPiece = nextPiece;
			nextPiece = new Piece();
			drawNextPiece();
			currRow = DEFAULT_START_ROW;
			currCol = DEFAULT_START_COL;
			cycle = new Cycle(getCountDownTick());
			matrix.setPiece(currRow, currCol, currPiece);
			needRedrawNextPiece = true;
			needRedraw = true;
		}
		
		if (cycle.cycleTick()) {
			matrix.removePiece(currRow, currCol, currPiece);
			currRow++;
			if (matrix.isValidPiece(currRow, currCol, currPiece)) {
				matrix.setPiece(currRow, currCol, currPiece);
				needRedraw = true;
			} else {
				// Hits the ground, stopping.
				currRow--;
				matrix.setPiece(currRow, currCol, currPiece);
				int rowsCleared = matrix.checkAndClear(currRow + Piece.PIECE_SIZE);
				if (rowsCleared > 0) {
					setTotalRowsCleared(totalRowsCleared + rowsCleared);
					if (level < getLevelFromRowsCleared()) {
						setLevel(getLevelFromRowsCleared());
					}
				}
				currPiece = null;
			}
		}	
	}

	@Override
	public void draw() {
		if (!needRedraw) {
			return;
		}
		Surface surface = getPrimarySurface();
		surface.clearSurface()
				.setBackgroundColor(KnownColor.BLACK);
		
		// Draw the blocks.
		for (int r = 0; r < matrix.getNumRows(); r++) {
			for (int c = 0; c < matrix.getNumCols(); c++) {
				renderer.drawBlock(surface, r, c, matrix.getBlock(r, c));				
			}
		}
		needRedraw = false;
	}
	
	/**
	 * Creates a panel that contains the given array of widgets in it.
	 * 
	 * @param widgets the widgets to add to the panel.
	 * @return a new panel that contains the given widgets.
	 */
	private Panel createPanel(Widget... widgets) {
		Panel panel = new FlowPanel();
		for (Widget w : widgets) {
			panel.add(w);
		}
		return panel;
	}
	
	/**
	 * Creates a header label.
	 * 
	 * @param text
	 * @return the label wrapped in a h2 tag.
	 */
	private Label createHeaderLabel(String text) {
		Label label = Label.wrap(DOM.createElement("h2"));
		label.setText(text);
		return label;
	}
	
	/**
	 * Draws the next piece.
	 */
	private void drawNextPiece() {
		if (!needRedrawNextPiece) {
			return;
		}
		nextPieceSurface.clearSurface();
		for (int r = 0; r < Piece.PIECE_SIZE; r++) {
			for (int c = 0; c < Piece.PIECE_SIZE; c++) {
				renderer.drawBlock(nextPieceSurface, r, c, nextPiece.getBlock(r, c));
			}
		}
		needRedrawNextPiece = false;
	}

	/**
	 * Gets the tick for the count down cycle.
	 */
	private int getCountDownTick() {
		return Math.max(1, 60 - level * 3);
	}
	
	/**
	 * Gets the level as calculated from the number of rows cleared.
	 */
	private int getLevelFromRowsCleared() {
		return totalRowsCleared / ROWS_CLEARED_PER_LEVEL + levelOffset;
	}
	
	/**
	 * Initializes the keyboard handlers for the game.
	 */
	private void initializeKeyHandlers() {
		getPrimarySurface().addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if ((currPiece == null) || (event.getCharCode() != 32)) {
					return;
				}
				// Rotates the piece.
				matrix.removePiece(currRow, currCol, currPiece);
				currPiece.rotateRight();
				if (!matrix.isValidPiece(currRow, currCol, currPiece)) {
					currPiece.rotateLeft();
				} else {
					needRedraw = true;
				}
				matrix.setPiece(currRow, currCol, currPiece);
			}
		});
		
		getPrimarySurface().addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (currPiece == null) {
					return;
				}
				// Moves left or right.
				int newCol = currCol;
				int newRow = currRow;
				if (event.isLeftArrow()) {
					newCol--;
				} else if (event.isRightArrow()) {
					newCol++;
				} else if (event.isDownArrow()) {
					newRow++;
				} else {
					return;
				}
				matrix.removePiece(currRow, currCol, currPiece);
				if (matrix.isValidPiece(newRow, newCol, currPiece)) {
					currRow = newRow;
					currCol = newCol;
					needRedraw = true;
				}
				matrix.setPiece(currRow, currCol, currPiece);
			}
		});
	}
	
	/**
	 * Sets the current level.
	 */
	private void setLevel(int level) {
		this.level  = level;
		levelLabel.setText(((Integer) level).toString());
	}
	
	/**
	 * Sets the total number of rows cleared.
	 */
	private void setTotalRowsCleared(int totalRowsCleared) {
		this.totalRowsCleared = totalRowsCleared;
		rowsClearedLabel.setText(((Integer) totalRowsCleared).toString());
	}
	
	/**
	 * Helper class for rendering a block.
	 */
	private static class TetrisRenderer {
		private static final Vector2 STROKE_OFFSET = new Vector2(1);
		private static final Vector2 FILL_OFFSET = new Vector2(.5);
		private static final Vector2 GRADIENT_POINT1_OFFSET = new Vector2(0, BLOCK_PIXEL_SIZE);
		private static final Vector2 GRADIENT_POINT2_OFFSET = new Vector2(BLOCK_PIXEL_SIZE, 0);
		
		// Temporary variables that are promoted to class variables to avoid 
		// reconstruction.
		private Rectangle strokeRectangle = new Rectangle(0, 0, 
				BLOCK_PIXEL_SIZE - 2, BLOCK_PIXEL_SIZE - 2);
		private Rectangle fillRectangle = new Rectangle(0, 0, 
				BLOCK_PIXEL_SIZE - 2, BLOCK_PIXEL_SIZE - 2);
		private Vector2 position = new Vector2();
		
		public void drawBlock(Surface surface, int row, int col, BlockType type) {
			if (type == null) {
				return;
			}
			position.set(col * BLOCK_PIXEL_SIZE, row * BLOCK_PIXEL_SIZE);
			strokeRectangle.move(position.add(STROKE_OFFSET));
			surface.setStrokeStyle(surface.createLinearGradient(
							position.add(GRADIENT_POINT1_OFFSET), 
							position.add(GRADIENT_POINT2_OFFSET))
									.addColorStop(0, KnownColor.WHITE)
									.addColorStop(1, KnownColor.GRAY))
					.strokeRectangle(strokeRectangle);
			
			fillRectangle.move(position.add(FILL_OFFSET));
			surface.setFillStyle(surface.createLinearGradient(
							position.add(GRADIENT_POINT1_OFFSET), 
							position.add(GRADIENT_POINT2_OFFSET))
									.addColorStop(0, type.getColor())
									.addColorStop(1, KnownColor.WHITE))
					.fillRectangle(fillRectangle);
		}
	}
}