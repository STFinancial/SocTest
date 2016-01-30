package world;

import simobject.SimObject;

public class WorldEvent {
	private WorldEventType type;
	private SimObject object;
	private DisplacementVector displacement;
	
	public WorldEvent(WorldEventType type, SimObject object, DisplacementVector d) {
		this.type = type;
		this.object = object;
		this.displacement = d;
	}
	
	DisplacementVector getDisplacement() { return displacement; }
	SimObject getObject() { return object; }
	WorldEventType getType() { return type; }
	
}
