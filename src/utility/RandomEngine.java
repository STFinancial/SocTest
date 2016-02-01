package utility;

import java.util.Random;

public final class RandomEngine {
	private static final Random random = new Random(System.nanoTime());
	
	
	/**
	 * Works int he same way as Random.nextBoolean()
	 * @return - A randomly distributed boolean value.
	 * @see Random#nextBoolean()
	 */
	public static boolean getNextBoolean() {
		return random.nextBoolean();
	}
	
	/** Works in the same way as Random.nextInt(int bound)
	 * @param bound - The exclusive upper bound below which to draw a random integer.
	 * @return Returns an integer from 0 (inclusive) to bound (exclusive) randomly, uniformly distributed
	 * @see Random#nextInt(int)
	 */
	public static int getNextInt(int bound) {
		return random.nextInt(bound);
	}
	
	
}
