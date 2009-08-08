package gwt.g2d.client.demo.tetris;

import com.google.gwt.user.client.ui.Panel;

import gwt.g2d.client.demo.AbstractDemo;

public class TetrisDemo extends AbstractDemo {
	private Tetris tetris;
	
	public TetrisDemo(String demoName, Panel demoPanel) {
		super(demoName, demoPanel);
	}

	@Override
	public void initialize() {
		tetris = new Tetris(1, getParentContainer());
		tetris.initialize();
	}

	@Override
	public void update() {
		tetris.update();
	}
	
	@Override
	public void draw() {
		tetris.draw();
	}
}
