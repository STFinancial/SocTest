package world;

/**
 * A class that represents a coordinate within the {@link World}.
 * Position Vectors are immutable. This should be statically imported.
 * @author Timothy
 * @see World
 */
public final class PositionVector {
	private static final PositionVector[][][] positionVectors = new PositionVector[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	
	private int z;
	private int y;
	private int x;
	
	private PositionVector(int z, int y, int x) {
		this.z = z;
		this.y = y;
		this.x = x;
	}
	
	/**
	 * Returns the {@link PositionVector} that corresponds to the map position that is provided.
	 * This method assumes the validity of the coordinates that are supplied.
	 * @param x - The x value of the desired position vector.
	 * @param y - The y value of the desired position vector.
	 * @param z - The z value of the desired position vector.
	 * @return The position vector that corresponds with the supplied coordinates.
	 */
	public static PositionVector getPositionVector(int x, int y, int z) {
		if (positionVectors[z][y][x] != null) {
			return positionVectors[z][y][x];
		} else {
			return (positionVectors[z][y][x] = new PositionVector(z, y, x));
		}
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getZ() { return z; }
}
