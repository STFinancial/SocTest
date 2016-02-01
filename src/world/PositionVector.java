package world;

import utility.RandomEngine;

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
	 * This method is used to encapsulate calculations done on movement. It takes in a position and displacement
	 * and returns the resulting position. Furthermore, this method assumes that the edges of the world are essentially
	 * "walls" and that you cannot venture from one side to the other. That is, the world is flat, and not spherical. No Kappa.
	 * Additionally, this method is all or nothing in the sense that either the entire movement can be executed or
	 * none of it will be executed. So we don't walk until hitting a "wall" and then stop.
	 * @param initialPosition - The {@link PositionVector position} of the object before the displacement takes place.
	 * @param displacement - The {@link DisplacementVector vector} representing the movement.
	 * @return A position vector corresponding to the position of the object from the initial position and
	 * after applying the displacement.
	 */
	public static PositionVector getDestination(PositionVector initialPosition, DisplacementVector displacement) {
		/* Calculate positions after displacement */
		int newX = initialPosition.x + displacement.getX();
		int newY = initialPosition.y + displacement.getY();
		int newZ = initialPosition.z + displacement.getZ();
		
		/* Check to make sure they're in bounds */
		if (newX < 0 || newX >= WorldConstants.WORLD_X || 
			newY < 0 || newY >= WorldConstants.WORLD_Y || 
			newZ < 0 || newZ >= WorldConstants.WORLD_Z) {
			return initialPosition;
		} else {
			return positionVectors[newZ][newY][newX];
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
	public static PositionVector getPositionVector(int z, int y, int x) {
		if (positionVectors[z][y][x] != null) {
			return positionVectors[z][y][x];
		} else {
			return (positionVectors[z][y][x] = new PositionVector(z, y, x));
		}
	}
	
	/**
	 * Returns a random {@link PositionVector} randomly chosen from the world
	 * using a uniform distribution.
	 * @return A random position vector.
	 */
	public static PositionVector getRandomPosition() {
		return positionVectors[RandomEngine.getNextInt(WorldConstants.WORLD_Z)][RandomEngine.getNextInt(WorldConstants.WORLD_Y)][RandomEngine.getNextInt(WorldConstants.WORLD_X)];
	}
	
	
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getZ() { return z; }
	
	@Override
	public String toString() {
		return new String("Position X: " + x + "  Y: " + y + " Z: " + z);
	}
}
