package simobject;

import world.PositionVector;

public abstract class SimObject {
	/** The current position of this object */
	private PositionVector position;
	/** The object type of this sim object. This is useful for now so we know how to render it. */
	protected SimObjectType objectType;
	
	
	public abstract void update();
	
	/**
	 * Gives the {@link PositionVector position} on which this {@link SimObject} currently resides.
	 * @return The position in the {@link World} where this SimObject resides.
	 */
	public PositionVector getPosition() { return position; }
	/**
	 * Sets the position of this {@link SimObject}.
	 * @param newPosition - The {@link PositionVector} corresponding to where in the world we want this object to be.
	 */
	public void setPosition(PositionVector newPosition) { this.position = newPosition; }
	/**
	 * Returns the {@link SimObjectType} of this {@link SimObject}. The type is set on instantiation
	 * and cannot be changed (for now).
	 * @return The object type of this object.
	 */
	public SimObjectType getObjectType() { return objectType; }
}
