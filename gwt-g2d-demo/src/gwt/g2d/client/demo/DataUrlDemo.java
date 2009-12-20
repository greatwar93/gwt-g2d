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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the use of toDataURL() from canvas to create a new static image.
 * 
 * @author hao1300@gmail.com
 */
public class DataUrlDemo extends AbstractDemo {
	private final Button saveImageButton = new Button("Snapshot");
	private final Panel imagePanel = new FlowPanel();
	private final Panel animationPanel = new FlowPanel();
	private final Panel horizontalPanel = new HorizontalPanel();
	private AnimationDemo animationDemo;
	
	public DataUrlDemo(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
		animationDemo = new AnimationDemo("Animation", animationPanel);
	}

	@Override
	public void initialize() {
		add(new Label("This demo does not work under IE"));
		add(saveImageButton);
		horizontalPanel.clear();
		horizontalPanel.add(animationPanel);
		horizontalPanel.add(imagePanel);
		add(horizontalPanel);
		animationDemo.initialize();
		saveImageButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				imagePanel.clear();
				imagePanel.add(new Image(animationDemo.getPrimarySurface().getCanvas()
						.toDataURL()));
			}
		});
	}

	@Override
	public void update() {
		animationDemo.update();
	}
}
