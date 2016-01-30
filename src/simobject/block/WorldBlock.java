package simobject.block;

import simobject.SimObject;
import simobject.SimObjectType;
import world.World;

/**
 * Represents a Minecraft-like block that exists somewhere in the {@link World}.
 * This class currently extends {@link SimObject} to allow for a single layer world,
 * and to make it easy to have blocks move and interact with other objects.
 * @author Timothy
 *
 */
public final class WorldBlock extends SimObject {
	private WorldBlockType type;
	
	public WorldBlock(WorldBlockType type) {
		this.objectType = SimObjectType.BLOCK;
		this.type = type;
	}
	
	
	public WorldBlockType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return new String("Tile of Type: " + type.toString() + " @ " + getPosition().toString());
	}


	@Override
	public void update() {
		return;
	}
}
