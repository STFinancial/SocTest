package world;

import java.util.Random;

import sex.Sex;
import simobject.SimObject;
import simobject.agent.Mater;
import simobject.block.WorldBlock;
import simobject.block.WorldBlockType;


public final class WorldGenerator {
	private static Random random = new Random();
	
	public static World generateRandomWorld() {
		SimObject[][][] objectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		WorldBlock w;
		for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					/* TODO: Rather than having to manually set the position, a layer of abstraction where we call objectLayer.setPosition(PositionVector, object) and it sets both */
					w = new WorldBlock(WorldBlockType.values()[random.nextInt(2)]);
					w.setPosition(PositionVector.getPositionVector(z, y, x));
					objectLayer[z][y][x] = w;
				}
			}
		}
		World world = new World(objectLayer);
		Mater.setWorld(world);
		world.setObject(Mater.generateRandomAgent(Sex.MALE), PositionVector.getPositionVector(0, 0, 0));
		return world;
	}
}
