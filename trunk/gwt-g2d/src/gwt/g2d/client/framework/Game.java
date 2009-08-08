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
package gwt.g2d.client.framework;

import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.user.client.Timer;

/**
 * Abstract class for running and rendering a game.
 * 
 * @author hao1300@gmail.com
 */
public abstract class Game {	
	private Surface primarySurface;
	private Timer timer;
	private boolean paused;
	
	public Game(Surface surface) {
		primarySurface = surface;
	}
	
	public Game(int width, int height) {
		this(new Surface(width, height));
	}
	
	public Game(Vector2 size) {
		this(new Surface(size));
	}
	
	/**
	 * Gets the primary surface for the game.
	 */
	public Surface getPrimarySurface() {
		return primarySurface;
	}
	
	/**
	 * Gets whether the game is paused.
	 */
	public boolean isPaused() {
		return paused;
	}
	
	/**
	 * Sets whether the game is paused.
	 * Pausing the game will not take place immediately; instead, it will be
	 * paused before the next update loop. 
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	/**
	 * Exits the game.
	 * Exiting the game will not take place immediately; instead, it will 
	 * exit before the next update loop.
	 */
	public void exit() {
		timer.cancel();
	}
	
	/**
	 * Runs the game at the desired FPS.
	 * The game will try to call update and draw as many times as the given
	 * frames per second, but it may be called less often if there is a 
	 * performance hit in either method.
	 * 
	 * @param fps the number of frames per second to run the game at.
	 */
	public void run(int fps) {
		initialize();
		timer = new Timer() {
			@Override
			public void run() {
				if (isPaused()) {
					return;
				}
				update();
				draw();
			}
		};
		timer.scheduleRepeating(1000 / fps);
	}
	
	/**
	 * Performs initialization logics before the game right before the game
	 * is run.
	 */
	public abstract void initialize();
	
	/**
	 * Updates the game.
	 * Override this method to perform non-drawing specific logics.
	 */
	public abstract void update();
	
	/**
	 * Renders the game to the screen.
	 * Override this method to perform drawing specific logics.
	 */
	public abstract void draw();
}