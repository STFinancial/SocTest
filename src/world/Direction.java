package world;

public enum Direction {
	/* Basic Directions */
	NONE(0,0,0),
	UP(1,0,0),
	DOWN(-1,0,0),
	NORTH(0,1,0),
	SOUTH(0,-1,0),
	EAST(0,0,1),
	WEST(0,0,-1),
	
	/* Non-basic directions that involve a positive z movement */
	UP_NORTH(1,1,0),
	UP_SOUTH(1,-1,0),
	UP_EAST(1,0,1),
	UP_WEST(1,0,-1),
	UP_NORTH_EAST(1,1,1),
	UP_NORTH_WEST(1,1,-1),
	UP_SOUTH_EAST(1,-1,1),
	UP_SOUTH_WEST(1,-1,-1),
	
	/* Non-basic directions that involve a negative z movement*/
	DOWN_NORTH(-1,1,0),
	DOWN_SOUTH(-1,-1,0),
	DOWN_EAST(-1,0,1),
	DOWN_WEST(-1,0,-1),
	DOWN_NORTH_EAST(-1,1,1),
	DOWN_NORTH_WEST(-1,1,-1),
	DOWN_SOUTH_EAST(-1,-1,1),
	DOWN_SOUTH_WEST(-1,-1,-1),
	
	/* Non-basic directions with no z movement */
	NORTH_EAST(0,1,1),
	NORTH_WEST(0,1,-1),
	SOUTH_EAST(0,-1,1),
	SOUTH_WEST(0,-1,-1);
	
	private int z;
	private int y;
	private int x;
	
	private Direction(int z, int y, int x) {
		this.z = z;
		this.y = y;
		this.x = x;
	}
	
	/** @return The change in x position caused by movement in this {@link Direction}. */
	public int getX() {
		return x;
	}
	
	/** @return The change in y position caused by movement in this {@link Direction}. */
	public int getY() {
		return y;
	}
	
	/** @return The change in z position caused by movement in this {@link Direction}. */
	public int getZ() {
		return z;
	}
}
