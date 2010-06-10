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
import gwt.g2d.client.media.Source;
import gwt.g2d.client.media.Video;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the use of a Video and an Audio widget.
 * 
 * @author hao1300@gmail.com
 */
public class VideoAudioDemo extends AbstractDemo {
	
	public VideoAudioDemo(Panel parentContainer) {
		super("Video Audio Demo", parentContainer);
	}

	@Override
	public void initialize() {
		add(new Label("This demo will not work at all under IE or Opera."));
		add(new HTML("Video obtained from: <a href=\"" 
				+ "http://people.xiph.org/~maikmerten/demos/BigBuckBunny.ogv\">"
				+ "http://people.xiph.org/~maikmerten/demos/BigBuckBunny.ogv</a>"));
		Video video = new Video("http://people.xiph.org/~maikmerten/demos/BigBuckBunny.ogv");
		video.setControls(true);
		video.setAutobuffer(true);
		add(video);
		
		add(new HTML("Music obtained from: <a href=\"" 
				+ "http://www.vorbis.com/music/Epoq-Lepidoptera.ogg\">"
				+ "http://www.vorbis.com/music/Epoq-Lepidoptera.ogg</a>"));
		
		Audio audio = new Audio();
		Source source = new Source("http://www.vorbis.com/music/Epoq-Lepidoptera.ogg");
		source.setType("audio/ogg");
		audio.addSource(source);
		audio.setControls(true);
		audio.setAutobuffer(true);
		add(audio);
	}

	@Override
	public void update() {

	}
}
