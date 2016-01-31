package simobject;

import java.awt.Color;

import world.PositionVector;

public abstract class SimObject {
	/** The current position of this object */
	private PositionVector position;
	/** The object type of this sim object. This is useful for now so we know how to render it. */
	protected SimObjectType objectType;
	/** The color which this object will render as when painting */
	protected Color renderColor;
	
	public abstract void update();
	
	
	/** Clears the {@link PositionVector position} to which this object resides in the {@link World}. */
	public void clearPosition() { position = null; }
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
	/**
	 * When rendering the {@link World}, this should be called on each object to get its respective {@link Color}.
	 * For now, these values will be set by the object constructors.
	 * @return The color that will be used to render this {@link SimObject}.
	 */
	public Color getRenderColor() { return renderColor; }
}
