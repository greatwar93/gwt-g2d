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

import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.canvas.CanvasPixelArray;
import gwt.g2d.client.graphics.canvas.ImageDataAdapter;
import gwt.g2d.client.media.Video;
import gwt.g2d.client.media.event.PlayEvent;
import gwt.g2d.client.media.event.PlayHandler;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the use of an Video widget.
 * 
 * @author hao1300@gmail.com
 */
public class VideoCanvasDemo extends ReferenceDemo {
	private Video video;
	
	public VideoCanvasDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer, 
				"https://developer.mozilla.org/En/Manipulating_video_using_canvas");
	}

	@Override
	public void initialize() {
		add(new Label("This demo will not work at all under IE or Opera."));
		video = new Video("http://gwt-g2d.appspot.com/media/video.ogg");
		video.setControls(true);
		add(video);
		video.addPlayHandler(new PlayHandler() {
			@Override
			public void onPlay(PlayEvent event) {
				video.setWidth(video.getVideoWidth());
				video.setHeight(video.getVideoHeight());
				getPrimarySurface().setWidth(video.getVideoWidth());
				getPrimarySurface().setHeight(video.getVideoHeight());
			}
		});
		video.setAutobuffer(true);
		super.initialize();
	}

	@Override
	public void update() {
		if (video.isEnded() || video.isPaused()) {
			return;
		}
		int width = video.getVideoWidth();
		int height = video.getVideoHeight();
		Surface surface = getPrimarySurface();
		surface.drawImage(video.getVideoElement(), 0, 0, width, height,
				0, 0, width, height);  
    ImageDataAdapter frame = surface.getImageData(0, 0, width, height);
    CanvasPixelArray pixelData = frame.getPixelData();
    int l = pixelData.getLength();  
  
    for (int i = 0; i < l; i += 4) {  
      int r = pixelData.getData(i + 0);  
      int g = pixelData.getData(i + 1);  
      int b = pixelData.getData(i + 2);  
      if (g > 100 && r > 100 && b < 43) {
      	pixelData.setData(i + 3, 0);  
      }
    }  
    surface.putImageData(frame, 0, 0);
	}
}
