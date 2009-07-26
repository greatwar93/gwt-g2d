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
package gwt.g2d.client.demo;

import gwt.g2d.client.framework.Game;

/**
 * Base class for all demo.
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractDemo extends Game {
	protected static final int WIDTH = 600, HEIGHT = 600;
	private final String demoName;
	
	public AbstractDemo(String demoName) {
		super(WIDTH, HEIGHT);
		this.demoName = demoName;
	}
	
	/**
	 * Gets the name of the demo.
	 */
	public String getDemoName() {
		return demoName;
	}
}
