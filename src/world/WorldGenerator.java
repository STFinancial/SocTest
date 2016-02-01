package world;

import sex.Sex;
import simobject.EmptyObject;
import simobject.agent.AgentFemale;
import simobject.agent.AgentMale;
import simobject.agent.Mater;
import simobject.block.WorldBlock;
import simobject.block.WorldBlockType;
import utility.RandomEngine;


public final class WorldGenerator {
	/* TODO: One random number generator for the entire program rather than separate instances */
	private static final EmptyObject emptyObject = EmptyObject.getInstance();
	
	public static World generateRandomWorld() {
		ObjectLayer ol = new ObjectLayer();
		for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					if (RandomEngine.getNextBoolean()) {
						ol.setObjectPosition(new WorldBlock(WorldBlockType.NON_EMPTY), PositionVector.getPositionVector(z, y, x));
					} else {
						ol.setObjectPosition(emptyObject, PositionVector.getPositionVector(z, y, x));
					}
				}
			}
		}
		World world = new World(ol);
		Mater.setWorld(world);
		
		AgentFemale female;
		AgentMale male;
		PositionVector pos;
		for (int i = 0; i < 5; i++) {
			male = (AgentMale) Mater.generateRandomAgent(Sex.MALE);
			do {
				pos = PositionVector.getRandomPosition();
			} while (ol.isOccupied(pos));
			ol.setObjectPosition(male, pos);
			female = (AgentFemale) Mater.generateRandomAgent(Sex.FEMALE);
			do {
				pos = PositionVector.getRandomPosition();
			} while (ol.isOccupied(pos));
			ol.setObjectPosition(female, pos);
		}
		
		return world;
	}
}
