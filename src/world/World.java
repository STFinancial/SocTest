package world;

import utility.SimObject;

public final class World {
	/* Arrays in the form Z, Y, X */
	private SimObject[][][] objectLayer;
	
	public World() {
		objectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	World(SimObject[][][] objectLayer) {
		this.objectLayer = objectLayer;
	}
}
