package world;

/**
 * This class is a vector that represents a movement within the {@link World}.
 * It is differentiated from {@link PositionVector} in that position vectors
 * are immutable and represent positions in the world rather than displacements.
 * @author Timothy
 *
 */
public class DisplacementVector {
	private int x;
	private int y;
	private int z;
	
	public DisplacementVector(int z, int y, int x) {
		this.z = z;
		this.y = y;
		this.x = x;
	}
	
	/**
	 * Sets the value for the x displacement of this {@link DisplacementVector vector}.
	 * @param x - The displacement in the x direction.
	 */
	public void setX(int x) { this.x = x; }
	/**
	 * Sets the value for the y displacement of this {@link DisplacementVector vector}.
	 * @param y - The displacement in the y direction.
	 */
	public void setY(int y) { this.y = y; }
	/**
	 * Sets the value for the z displacement of this {@link DisplacementVector vector}.
	 * @param z - The displacement in the z direction.
	 */
	public void setZ(int z) { this.z = z; }
	
	/** @return The current x displacement corresponding to this {@link DisplacementVector vector}. */
	public int getX() { return x; }
	/** @return The current y displacement corresponding to this {@link DisplacementVector vector}. */
	public int getY() { return y; }
	/** @return The current z displacement corresponding to this {@link DisplacementVector vector}. */
	public int getZ() { return z; }
 }
