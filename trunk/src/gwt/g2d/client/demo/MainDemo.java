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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
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
	
	public void onModuleLoad() {
		demos.add(new ParticlesDemo("Particles Demo"));
		demos.add(new ColorDemo("Color Demo"));
				
		final ListBox listBox = new ListBox();
		for (AbstractDemo demo : demos) {
			listBox.addItem(demo.getDemoName());
		}
		
		final Panel demoPanel = new FlowPanel();
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				demoPanel.clear();
				AbstractDemo selectedDemo = demos.get(listBox.getSelectedIndex()); 
				demoPanel.add(selectedDemo.getPrimarySurface());
				selectedDemo.run(60);				
			}
		});
		
		Panel panel = new FlowPanel();
		panel.add(listBox);
	  panel.add(demoPanel);
		RootPanel.get().add(panel);
		
		AbstractDemo selectedDemo = demos.get(listBox.getSelectedIndex()); 
		demoPanel.add(selectedDemo.getPrimarySurface());
		selectedDemo.run(60);
	}
}
