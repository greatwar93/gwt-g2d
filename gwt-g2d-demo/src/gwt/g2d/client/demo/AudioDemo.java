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

import gwt.g2d.client.media.Audio;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the use of an Audio widget.
 * 
 * @author hao1300@gmail.com
 */
public class AudioDemo extends AbstractDemo {
	
	public AudioDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}

	@Override
	public void initialize() {
		add(new Label("This demo does not work at all under IE or Opera."));
		add(new Label("Music obtained from: http://www.pacdv.com/sounds/free-music-01.html"));
		Audio audio = new Audio("demo/daydreaming.mp3");
		audio.setAutoplay(true);
		audio.setControls(true);
		add(audio);
	}

	@Override
	public void update() {

	}
}
