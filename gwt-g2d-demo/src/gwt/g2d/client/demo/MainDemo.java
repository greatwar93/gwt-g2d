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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Main entry point for the demo.
 * 
 * @author hao1300@gmail.com
 */
public class MainDemo implements EntryPoint {
	private final List<AbstractDemo> demos = new ArrayList<AbstractDemo>();
	private int selectedIndex = 0;
	
	public void onModuleLoad() {
		final Panel demoPanel = new FlowPanel();
		demos.add(new AnimationDemo("Animation Demo", demoPanel));
		demos.add(new CanvasOnCanvasDemo("Canvas on Canvas Demo", demoPanel));
		demos.add(new ColorDemo("Color Demo", demoPanel));
		demos.add(new ClippingDemo("Clipping Demo", demoPanel));
		demos.add(new DataUrlDemo("Data URL Demo", demoPanel));
		demos.add(new GradientDemo("Gradient Demo", demoPanel));
		demos.add(new ParticlesDemo("Particles Demo", demoPanel));
		demos.add(new PatternDemo("Pattern Demo", demoPanel));
		demos.add(new PixelManipulatorDemo("Pixel Manipulator Demo", demoPanel));
		demos.add(new ShadowDemo("Shadow Demo", demoPanel));
		demos.add(new ShapeDemo("Shape Demo", demoPanel));
		demos.add(new TetrisDemo("Tetris", demoPanel));
		demos.add(new TextDemo("Text Demo", demoPanel));
		demos.add(new VideoAudioDemo("Video and Audio Demo", demoPanel));
		demos.add(new VideoCanvasDemo("Video on Canvas Demo", demoPanel));
		demos.add(new PropertyTest("Property Tests", demoPanel));
				
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
		
		Panel panel = new FlowPanel();
		panel.add(new HTML("This demo is created using " 
				+ "<a href=\"http://code.google.com/p/gwt-g2d/\">gwt-g2d</a>"
				+ " inspired by <a href=\"http://code.google.com/p/gwt-canvas/\">gwt-canvas</a>"));
		panel.add(listBox);
	  panel.add(demoPanel);
		RootPanel.get().add(panel);
		
		AbstractDemo selectedDemo = demos.get(listBox.getSelectedIndex()); 
		selectedDemo.initialize();
		selectedDemo.start();
	}
}
