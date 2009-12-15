package gwt.g2d.client.util;

/**
 * A simple count-down that counts the number of times that tick() is called.
 * 
 * @author hao1300@gmail.com
 */
public class CountDown {
	private int maxCount;
	private int count;
	
	public CountDown(int count) {
		this.maxCount = count;
	}
	
	/**
	 * Decrement the count down.
	 * 
	 * @return true if tick() has been called for at least as many times
	 * 				 the number of count down specified. 
	 */
	public final boolean tick() {
		++count;
		return isCompleted();
	}
	
	/**
	 * Checks whether the count down has been completed.
	 * 
	 * @return true if the count down is completed.
	 */
	public final boolean isCompleted() {
		return count >= maxCount;
	}
	
	/**
	 * Gets the percentage of down down that this has completed.
	 */
	public final double getPercent() {
		return count / (double) maxCount;
	}
	
	/**
	 * Resets the count.
	 */
	public final void reset() {
		count = 0;
	}
}
