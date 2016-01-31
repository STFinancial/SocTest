package simobject.agent;

import java.awt.Color;

import sex.Sex;
import world.World;

public final class AgentMale extends Agent {
	
	
	AgentMale(World world) {
		super(world, Sex.MALE);
		this.renderColor = Color.RED;
	}
}
