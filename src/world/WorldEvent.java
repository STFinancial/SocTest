package world;

import simobject.SimObject;

public class WorldEvent {
	/* TODO: Consider making this an abstract class and having subtypes for the events */
	private WorldEventType type;
	private SimObject object;
	private DisplacementVector displacement;
	private PositionVector position;
	
	public WorldEvent(WorldEventType type, SimObject object, DisplacementVector d) {
		this.type = type;
		this.object = object;
		this.displacement = d;
	}
	
	public WorldEvent(WorldEventType type, SimObject object, PositionVector p) {
		this.type = type;
		this.object = object;
		this.position = p;
	}
	
	DisplacementVector getDisplacement() { return displacement; }
	SimObject getObject() { return object; }
	PositionVector getPosition() { return position; }
	WorldEventType getType() { return type; }
	
}
