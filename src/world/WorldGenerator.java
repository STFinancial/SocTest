package world;

import java.util.Random;

import utility.SimObject;

public final class WorldGenerator {
	private static Random random = new Random();
	
	public static World generateRandomWorld() {
		SimObject[][][] objectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					objectLayer[z][y][x] = new WorldBlock(WorldBlockType.values()[random.nextInt(2)], WorldConstants.getPositionVector(z, y, x))
				}
			}
		}
	}
}
