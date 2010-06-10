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

import gwt.g2d.client.demo.tetris.TetrisDemo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

/**
 * Main entry point for the demo.
 * 
 * @author hao1300@gmail.com
 */
public class MainDemo implements EntryPoint {
	private final List<AbstractDemo> demos = new ArrayList<AbstractDemo>();
	private int selectedIndex = 0;
	private final TextArea sourceTextArea = new TextArea();
	
	public void onModuleLoad() {
		final Panel demoPanel = new FlowPanel();
		demos.add(new AnimationDemo(demoPanel));
		demos.add(new CanvasOnCanvasDemo(demoPanel));
		demos.add(new ColorDemo(demoPanel));
		demos.add(new ClippingDemo(demoPanel));
		demos.add(new DataUrlDemo(demoPanel));
		demos.add(new DrawingPadDemo(demoPanel));
		demos.add(new GradientDemo(demoPanel));
		demos.add(new ParticlesDemo(demoPanel));
		demos.add(new PatternDemo(demoPanel));
		demos.add(new PixelManipulatorDemo(demoPanel));
		demos.add(new ShadowDemo(demoPanel));
		demos.add(new ShapeDemo(demoPanel));
		demos.add(new TetrisDemo(demoPanel));
		demos.add(new TextDemo(demoPanel));
		demos.add(new VideoAudioDemo(demoPanel));
		demos.add(new VideoCanvasDemo(demoPanel));
		demos.add(new PropertyTest(demoPanel));
				
		final ListBox listBox = new ListBox();
		for (AbstractDemo demo : demos) {
			listBox.addItem(demo.getDemoName());
		}
		
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				demos.get(selectedIndex).cancel();
				
				demoPanel.clear();
				selectedIndex = listBox.getSelectedIndex();
				AbstractDemo selectedDemo = demos.get(selectedIndex);
				selectedDemo.initialize();
				selectedDemo.start();
			}
		});
		
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
	  
		Panel panel = new FlowPanel();
		panel.add(new HTML("This demo is created using " 
				+ "<a href=\"http://code.google.com/p/gwt-g2d/\">gwt-g2d</a>"
				+ " inspired by <a href=\"http://code.google.com/p/gwt-canvas/\">gwt-canvas</a>"));
		panel.add(listBox);
		
		sourceTextArea.setSize("100%", "100%");
		sourceTextArea.setReadOnly(true);
		sourceTextArea.removeStyleName("gwt-TextArea-readonly");
		Button showSourceBtn = new Button("Source");
		showSourceBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String demoName = demos.get(selectedIndex).getDemoName()
						.replace(" ", "").toLowerCase();
				ResourcePrototype resource = Resources.INSTANCE.getResource(demoName);
				if (resource == null) {
					sourceTextArea.setText("Source codes not included for this demo.\n"
							+ "See the trunk for the codes.");
				}
				loadExternalTextResource((ExternalTextResource) resource);
			}
		});
		panel.add(showSourceBtn);
	  panel.add(demoPanel);
	  splitPanel.setLeftWidget(panel);
	  splitPanel.setRightWidget(sourceTextArea);
	  
		RootPanel.get().add(splitPanel);
		
		AbstractDemo selectedDemo = demos.get(listBox.getSelectedIndex()); 
		selectedDemo.initialize();
		selectedDemo.start();
	}
	
	/**
	 * Loads the external text resource into the source text area.
	 */
	private void loadExternalTextResource(ExternalTextResource textResource) {
		try {
			textResource.getText(new ResourceCallback<TextResource>() {
				@Override
				public void onSuccess(TextResource resource) {
					sourceTextArea.setText(resource.getText().replaceAll("\t", "  "));
				}
				
				@Override
				public void onError(ResourceException e) {
					sourceTextArea.setText("Fails to load source file.");
				}
			});
		} catch (ResourceException e) {
			sourceTextArea.setText("Fails to load source file.");
		}
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source("AnimationDemo.java")
		ExternalTextResource animationdemo();

		@Source("CanvasOnCanvasDemo.java")
		ExternalTextResource canvasoncanvasdemo();

		@Source("ClippingDemo.java")
		ExternalTextResource clippingdemo();

		@Source("ColorDemo.java")
		ExternalTextResource colordemo();

		@Source("DataUrlDemo.java")
		ExternalTextResource dataurldemo();

		@Source("GradientDemo.java")
		ExternalTextResource gradientdemo();

		@Source("ParticlesDemo.java")
		ExternalTextResource particlesdemo();

		@Source("PatternDemo.java")
		ExternalTextResource patterndemo();

		@Source("PixelManipulatorDemo.java")
		ExternalTextResource pixelmanipulatordemo();

		@Source("PropertyTest.java")
		ExternalTextResource propertytest();

		@Source("ShadowDemo.java")
		ExternalTextResource shadowdemo();

		@Source("TextDemo.java")
		ExternalTextResource textdemo();

		@Source("VideoAudioDemo.java")
		ExternalTextResource videoaudiodemo();

		@Source("VideoCanvasDemo.java")
		ExternalTextResource videocanvasdemo();

		@Source("ShapeDemo.java")
		ExternalTextResource shapedemo();		
	}
}
