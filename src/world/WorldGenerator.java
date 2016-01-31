package world;

import java.util.Random;

import sex.Sex;
import simobject.agent.Mater;
import simobject.block.WorldBlock;
import simobject.block.WorldBlockType;


public final class WorldGenerator {
	private static Random random = new Random();
	
	public static World generateRandomWorld() {
		ObjectLayer ol = new ObjectLayer();
		for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					ol.setObjectPosition(new WorldBlock(WorldBlockType.values()[random.nextInt(2)]), PositionVector.getPositionVector(z, y, x));
				}
			}
		}
		World world = new World(ol);
		Mater.setWorld(world);
		ol.setObjectPosition(Mater.generateRandomAgent(Sex.MALE), PositionVector.getPositionVector(0, 0, 0));
		return world;
	}
}
