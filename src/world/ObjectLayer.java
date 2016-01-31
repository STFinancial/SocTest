package world;

import simobject.SimObject;
import simobject.block.WorldBlock;
import simobject.block.WorldBlockType;

/**
 * This class is responsible for holding all of the {@link SimObject objects}
 * within the world and making sure that they are updated. This is separated from
 * {@link World} to create an additional layer of abstraction and to avoidd
 * World becoming too bloated.
 * @author Timothy
 *
 */
class ObjectLayer {
	private SimObject[][][] objectLayer;
	
	private final int zSize = WorldConstants.WORLD_Z;
	private final int ySize = WorldConstants.WORLD_Y;
	private final int xSize = WorldConstants.WORLD_X;
	
	ObjectLayer() {
		objectLayer = new SimObject[zSize][ySize][xSize];
	}
	
	/**
	 * Assigns the {@link SimObject object} to the specified {@link PositionVector position}
	 * in both the world and object state.
	 * @param obj - The object which we want to assign the position of.
	 * @param pos - The position to assign the object.
	 */
	void setObjectPosition(SimObject obj, PositionVector pos) {
		objectLayer[pos.getZ()][pos.getY()][pos.getX()] = obj;
		obj.setPosition(pos);
	}
	
	/**
	 * Clears the {@link SimObject object} from its specified {@link PositionVector position}.
	 * Position is specified to make this operation constant time. Clears its position in both
	 * the world and object state so the object may fall out of scope after this is called.
	 * @param obj - The object we are looking to clear the position of.
	 * @param pos - The position to which the object is currently assigned.
	 */
	void clearObjectPosition(SimObject obj, PositionVector pos) {
		objectLayer[pos.getZ()][pos.getY()][pos.getX()] = new WorldBlock(WorldBlockType.EMPTY);
		obj.clearPosition();
	}
	
	/**
	 * Returns the {@link SimObject} at the specified {@link PositionVector}
	 * if one exists.
	 * @param pos - The position for which we are trying to fetch the object.
	 * @return The object at the specified position. If such an object does not exist, we return null.
	 */
	SimObject getObject(PositionVector pos) {
		return objectLayer[pos.getZ()][pos.getY()][pos.getX()];
	}
	
	public void update() {
		for (int z = 0; z < zSize; z++) {
			for (int y = 0; y < ySize; y++) {
				for (int x = 0; x < xSize; x++) {
					objectLayer[z][y][x].update();
				}
			}
		}
	}
}
