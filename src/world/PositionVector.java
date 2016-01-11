package world;

/**
 * A class that represents a coordinate within the {@link World}.
 * Position Vectors are immutable.
 * @author Timothy
 * @see World
 */
final class PositionVector {
	private int z;
	private int y;
	private int x;
	
	private PositionVector(int z, int y, int x) {
		this.z = z;
		this.y = y;
		this.x = x;
	}
	
	int getX() { return x; }
	int getY() { return y; }
	int getZ() { return z; }
}