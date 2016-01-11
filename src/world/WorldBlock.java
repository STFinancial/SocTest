package world;

import utility.SimObject;

/**
 * Represents a Minecraft-like block that exists somewhere in the {@link World}.
 * This class currently extends {@link SimObject} to allow for a single layer world,
 * and to make it easy to have blocks move and interact with other objects.
 * @author Timothy
 *
 */
final class WorldBlock extends SimObject {
	private WorldBlockType type;
	private PositionVector position;
	
	WorldBlock(WorldBlockType type, PositionVector position) {
		this.type = type;
		this.position = position;
	}
	
	
	@Override
	public String toString() {
		return new String("Tile of Type: " + type.toString() + " @ " + position.toString());
	}
}
