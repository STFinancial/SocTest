package world;

/**
 * Contains information common to many classes in the world package.
 * This class also defines {@link PositionVector position vectors}, which are
 * immutable objects that are pass
 * @author Timothy
 * @see PositionVector
 * @see World
 */
final class WorldConstants {
	private static PositionVector[][][] positionVectors;
	
	static final int WORLD_X = 100;
	static final int WORLD_Y = 100;
	static final int WORLD_Z = 100;
	
	static {
		positionVectors = new PositionVector[WORLD_Z][WORLD_Y][WORLD_X];
		for (int z = 0; z < WORLD_Z; z++) {
			for (int y = 0; y < WORLD_Y; y++) {
				for (int x = 0; x < WORLD_X; x++) {
					WorldConstants.positionVectors[z][y][x] = new PositionVector(z, y, x);
				}
			}
		}
	}
	
	/**
	 * Returns the {@link PositionVector} that corresponds to the map position that is provided.
	 * This method assumes the validity of the coordinates that are supplied.
	 * @param z - The z value of the desired position vector.
	 * @param y - The y value of the desired position vector.
	 * @param x - The x value of the desired position vector.
	 * @return The position vector that corresponds with the supplied coordinates.
	 */
	static PositionVector getPositionVector(int z, int y, int x) {
		return positionVectors[z][y][x];
	}
	
	
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
}
