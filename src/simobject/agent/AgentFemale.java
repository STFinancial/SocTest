package simobject.agent;

import java.awt.Color;

import sex.Sex;
import world.World;

public final class AgentFemale extends Agent {
	
	AgentFemale(World world) {
		super(world, Sex.FEMALE);
		this.renderColor = Color.BLUE;
	}
}
