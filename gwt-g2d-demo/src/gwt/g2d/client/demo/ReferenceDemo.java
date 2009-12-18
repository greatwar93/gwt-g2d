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

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;

/**
 * Represents a demo that is referenced from some other online source.
 * 
 * @author hao1300@gmail.com
 */
public class ReferenceDemo extends AbstractDemo {
	private String referenceSource;
	
	public ReferenceDemo(String demoName, Panel parentContainer, String referenceSource) {
		super(demoName, parentContainer);
		this.referenceSource = referenceSource;
	}
	
	@Override
	public void initialize() {
		getParentContainer().clear();
		Panel panel = new FlowPanel();
		panel.add(new InlineLabel("Reference: "));
		panel.add(new Anchor(referenceSource, referenceSource));
		getParentContainer().add(panel);
		getParentContainer().add(getPrimarySurface());
		getPrimarySurface().clear();
	}
	
	@Override
	public void update() {
	}
}
