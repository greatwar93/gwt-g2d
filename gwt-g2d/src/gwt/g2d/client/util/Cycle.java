package gwt.g2d.client.util;

/**
 * A simple cycle tracker that counts for a given number of ticks per cycle.
 * 
 * @author hao1300@gmail.com
 */
public class Cycle {
	private CountDown countDown;
	private int numCycle;
	
	/**
	 * Creates an infinite cycle that counts for the given number of ticks per 
	 * cycle.
	 */
	public Cycle(int count) {
		this(count, -1);
	}
	
	/**
	 * Creates a cycle tracker that runs for the given number of cycles.
	 * 
	 * @param count
	 */
	public Cycle(int count, int numCycle) {
		this.countDown = new CountDown(count);
		this.numCycle = numCycle;
	}
	
	/**
	 * Perform a tick in the cycle.
	 * 
	 * @return true if the cycle has completed.
	 */
	public final boolean cycleTick() {
		if (countDown.isCompleted() && (numCycle != 0)) {
			countDown.reset();
			if (numCycle > 0) {
				numCycle--;
			}
		}
		return countDown.tick();
	}
	
	/**
	 * Resets the count down in this cycle.
	 */
	public final void resetTick() {
		countDown.reset();
	}
}
