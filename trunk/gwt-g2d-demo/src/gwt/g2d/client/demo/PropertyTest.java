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

import gwt.g2d.client.graphics.Composition;
import gwt.g2d.client.graphics.LineCap;
import gwt.g2d.client.graphics.LineJoin;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.TextAlign;
import gwt.g2d.client.graphics.TextBaseline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Tests the Surface's getter and setter methods.
 * 
 * @author hao1300@gmail.com
 */
public class PropertyTest extends AbstractDemo {
	private static final double EPSILON = .0005;
	private static final int NUM_RANDOM_TESTS = 3;
	
	/**
	 * Creates a new surface for testing.
	 */
	private static Surface createSurface() {
		return new Surface(1, 1);
	}

	private final Map<String, List<TestResult>> allTestResults = 
			new HashMap<String, List<TestResult>>();
	
	private final FlexTable table = new FlexTable();
	
	public PropertyTest(String demoName, Panel parentContainer) {
		super(demoName, parentContainer);
	}

	@Override
	public void initialize() {
		add(table);
		table.setSize("400px", "auto");
		table.setHTML(0, 0, "<b>Property</b>");
		table.setHTML(0, 1, "<b>Expected</b>");
		table.setHTML(0, 2, "<b>Actual</b>");
		table.setHTML(0, 3, "<b>Result*</b>");
		
		runTests();
		
		for (Entry<String, List<TestResult>> testResults : allTestResults.entrySet()) {
			int currRow = table.getRowCount();
			table.setHTML(currRow, 0, "<b>" + testResults.getKey() + "</b>");
			int i = currRow;
			for (TestResult result : testResults.getValue()) {
				table.setHTML(i, 1, result.getExpected().toString());
				table.setHTML(i, 2, result.getActual() == null ? "undefined" :
					result.getActual().toString());
				table.setHTML(i, 3, result.isPassed() 
						? "<span class=\"pass\">PASS</span>"
					  : "<span class=\"fail\">FAIL</span>");
				i++;
			}
		}
		add(new Label("* A numerical test is considered " 
				+ "passing if it is within " + EPSILON + " of the expected result."));
	}

	/**
	 * Performs all tests.
	 */
	private void runTests() {
		testGlobalAlpha();
		testGlobalComposition();
		testLineCap();
		testLineJoin();
		testLineWidth();
		testMiterLimit();
		testFont();
		testTextAlign();
		testTextBaseline();
		testShadowOffset();
		testShadowBlur();
		testSaveRestore();		
	}
	
	private void testSaveRestore() {
		Surface surface = createSurface();
		surface.setGlobalAlpha(.5)
				.setGlobalCompositeOperation(Composition.SOURCE_OVER)
				.setLineCap(LineCap.BUTT)
				.setLineJoin(LineJoin.ROUND)
				.setLineWidth(5.0)
				.setMiterLimit(5.0)
				.setFont("20px sans-serif")
				.setTextAlign(TextAlign.CENTER)
				.setTextBaseline(TextBaseline.TOP)
				.setShadowOffsetX(1.0)
				.setShadowOffsetY(1.0)
				.setShadowBlur(1.0);		
		surface.save();
		surface.setGlobalAlpha(1.0)
				.setGlobalCompositeOperation(Composition.SOURCE_IN)
				.setLineCap(LineCap.ROUND)
				.setLineJoin(LineJoin.MITER)
				.setLineWidth(1.0)
				.setMiterLimit(1.0)
				.setFont("10px sans-serif")
				.setTextAlign(TextAlign.END)
				.setTextBaseline(TextBaseline.BOTTOM)
				.setShadowOffsetX(5.0)
				.setShadowOffsetY(5.0)
				.setShadowBlur(5.0);
		
		surface.restore();
		testResult("Restore Global Alpha", .5, surface.getGlobalAlpha());
		testResult("Restore Global Composition", Composition.SOURCE_OVER, 
				surface.getGlobalCompositeOperation());
		testResult("Restore Line Cap", LineCap.BUTT, surface.getLineCap());
		testResult("Restore Line Join", LineJoin.ROUND, surface.getLineJoin());
		testResult("Restore Line Width", 5.0, surface.getLineWidth());
		testResult("Restore Miter Limit", 5.0, surface.getMiterLimit());
		testResult("Restore Font", "20px sans-serif", surface.getFont());
		testResult("Restore Text Align", TextAlign.CENTER, surface.getTextAlign());
		testResult("Restore Text Baseline", TextBaseline.TOP, surface.getTextBaseline());
		testResult("Restore Shadow Offset X", 1.0, surface.getShadowOffsetX());
		testResult("Restore Shadow Offset Y", 1.0, surface.getShadowOffsetY());
		testResult("Restore Shadow Blur", 1.0, surface.getShadowBlur());		
	}

	private void testFont() {
		Surface surface = createSurface();
		testResult("Font", "10px sans-serif", surface.getFont());
		testResult("Font", "20pt Arial", 
				surface.setFont("20pt Arial").getFont());
		testResult("Font", "italic 400 12px/2 Unknown Font, sans-serif", 
				surface.setFont("italic 400 12px/2 Unknown Font, sans-serif").getFont());
	}

	private void testGlobalAlpha() {
		Surface surface = createSurface();
		testResult("Global Alpha", 1.0, surface.getGlobalAlpha());
		for (int i = 0; i < NUM_RANDOM_TESTS; i++) {
			double randNumber = Random.nextDouble();
			testResult("Global Alpha", randNumber, 
					surface.setGlobalAlpha(randNumber).getGlobalAlpha());
		}
	}

	private void testGlobalComposition() {
		Surface surface = createSurface();
		testResult("Global Composition", Composition.SOURCE_OVER, 
				surface.getGlobalCompositeOperation());
		for (Composition v : Composition.values()) {
			testResult("Global Composition", v, surface
					.setGlobalCompositeOperation(v).getGlobalCompositeOperation());
		}
	}

	private void testLineCap() {
		Surface surface = createSurface();
		testResult("Line Cap", LineCap.BUTT, surface.getLineCap());
		for (LineCap v : LineCap.values()) {
			testResult("Line Cap", v, surface.setLineCap(v).getLineCap());
		}
	}

	private void testLineJoin() {
		Surface surface = createSurface();
		testResult("Line Join", LineJoin.MITER, surface.getLineJoin());
		for (LineJoin v : LineJoin.values()) {
			testResult("Line Join", v, surface.setLineJoin(v).getLineJoin());
		}
	}

	private void testLineWidth() {
		Surface surface = createSurface();
		testResult("Line Width", 1.0, surface.getLineWidth());
		for (int i = 0; i < NUM_RANDOM_TESTS; i++) {
			double randNumber = Random.nextDouble() * 50;
			testResult("Line Width", randNumber, 
					surface.setLineWidth(randNumber).getLineWidth());
		}
	}

	private void testMiterLimit() {
		Surface surface = createSurface();
		testResult("Miter Limit", 10.0, surface.getMiterLimit());
		for (int i = 0; i < NUM_RANDOM_TESTS; i++) {
			double randNumber = Random.nextDouble() * 50;
			testResult("Miter Limit", randNumber, 
					surface.setMiterLimit(randNumber).getMiterLimit());
		}
	}
	
	private void testShadowBlur() {
		Surface surface = createSurface();
		testResult("Shadow Blur", 0.0, surface.getShadowBlur());
		for (int i = 0; i < NUM_RANDOM_TESTS; i++) {
			double randNumber = Random.nextDouble() * 50;
			testResult("Shadow Blur", randNumber, 
					surface.setShadowBlur(randNumber).getShadowBlur());
		}
	}
	
	private void testShadowOffset() {
		Surface surface = createSurface();
		testResult("Shadow Offser X", 0.0, surface.getShadowOffsetX());
		testResult("Shadow Offser Y", 0.0, surface.getShadowOffsetY());
		for (int i = 0; i < NUM_RANDOM_TESTS; i++) {
			double randNumber = Random.nextDouble() * 50;
			testResult("Shadow Offser X", randNumber, 
					surface.setShadowOffsetX(randNumber).getShadowOffsetX());
			randNumber = Random.nextDouble() * 50;
			testResult("Shadow Offser Y", randNumber, 
					surface.setShadowOffsetY(randNumber).getShadowOffsetY());
		}
	}
	
	private void testTextAlign() {
		Surface surface = createSurface();
		testResult("Text Align", TextAlign.START, surface.getTextAlign());
		for (TextAlign v : TextAlign.values()) {
			testResult("Text Align", v, surface.setTextAlign(v).getTextAlign());
		}
	}
	
	private void testTextBaseline() {
		Surface surface = createSurface();
		testResult("Text Baseline", TextBaseline.ALPHABETIC, surface.getTextBaseline());
		for (TextBaseline v : TextBaseline.values()) {
			testResult("Text Baseline", v, surface.setTextBaseline(v).getTextBaseline());
		}
	}
	
	/**
	 * Writes the test result to the table.
	 * 
	 * @param expected
	 * @param actual
	 * @param property
	 */
	private void testResult(String property, Object expected, Object actual) {
		List<TestResult> testResults = allTestResults.get(property);
		if (testResults == null) {
			testResults = new ArrayList<TestResult>();
		}
		testResults.add(new TestResult(expected, actual));
		allTestResults.put(property, testResults);
	}
	
	@Override
	public void update() {
	}
	
	/**
	 * A test result.
	 */
	private static class TestResult {
		private Object expected, actual;
		
		public TestResult(Object expected, Object actual) {
			this.expected = expected;
			this.actual = actual;
		}

		/**
		 * Gets the actual value.
		 */
		public Object getActual() {
			return actual;
		}
		
		/**
		 * Gets the expected value.
		 */
		public Object getExpected() {
			return expected;
		}
		
		/**
		 * Gets whether the expected value matches the expected value.
		 */
		public boolean isPassed() {
			if (expected == null && actual == null) {
				return true;
			}
			if (expected != null && actual != null) {
				if (expected.equals(actual)) {
					return true;
				}
				if (expected instanceof Number && actual instanceof Number) {
					if (Math.abs(((Double) expected) - (Double) actual) < EPSILON) {
						return true;
					}
				}
			}
			return false;
		}
	}
}
