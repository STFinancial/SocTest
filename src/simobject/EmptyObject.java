package simobject;

import java.awt.Color;

/**
 * Rather than use a null value, we create an "empty object"
 * singleton that will be used in all places in the world where there
 * is no object. This is useful for getting the render color, being able
 * to call getObject.getType to see if a space is occupied, and other useful
 * things. If this becomes problematic then we can change it in the future.
 * It also has the added benefit of reducing the amount of objects we need to create
 * and therefore freeing up heap space.
 * @author Timothy
 *
 */
public final class EmptyObject extends SimObject {
	private static final EmptyObject INSTANCE = new EmptyObject();
	
	private EmptyObject() {
		this.renderColor = Color.WHITE;
		this.objectType = SimObjectType.EMPTY;
	}
	
	/**
	 * Returns the singleton instance of the empty {@link SimObject}.
	 * @return A Singleton SimObject that has a {@link SimObjectType type}
	 * of EMPTY.
	 */
	public static EmptyObject getInstance() {
		return INSTANCE;
	}
	
	@Override
	public void update() {
		return;
	}
	
	@Override
	public String toString() {
		return new String("Empty Object");
	}

}
