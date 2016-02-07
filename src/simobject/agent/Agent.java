package simobject.agent;

import java.util.Random;

import sex.Sex;
import simobject.SimObject;
import simobject.SimObjectType;
import simobject.agent.attributes.AgentAttributeHolder;
import world.DisplacementVector;
import world.WorldEvent;
import world.WorldEventType;
import world.World;


/**
 * The main unit of this fake society.
 * Agents have {@link AgentAttribute attributes}
 * that are contained in an {@link AgentAttributeHolder encapsulating class}.
 * Agents reproduce with one another by way of the
 * {@link Mater agent sex machine}.
 * @author Timothy
 * @see AgentAttribute
 * @see Mater
 */
public abstract class Agent extends SimObject {
	private static Random rand = new Random(System.nanoTime());
	
	/* Independent Attributes */
	private World world;
	private int age; /* In months */
	private Sex sex;
	
	/* Agent Attributes */
	AgentAttributeHolder attributes;
	
	Agent(World world, Sex sex) {
		this.objectType = SimObjectType.AGENT;
		this.world = world;
		this.age = 0;
		this.sex = sex;
	}
	
	void setAttributeHolder(AgentAttributeHolder newHolder) {
		attributes = newHolder;
	}
	
	
	/* ***** Public Getters ***** */
	
	/** @return The current intelligence value of this {@link Agent}. 
	 * @see Intelligence */
	public double getIntelligence() { return attributes.getCurrentIntelligence(); }
	
	/** @return The current physical value of this {@link Agent}.
	 * @see Physical */
	public double getPhysical() { return attributes.getCurrentPhysical(); }
	
	/** @return The "current" sex value of this {@link Agent}.
	 * @see Sex */
	public Sex getSex() { return sex; }
	
	/* ***** End Public Getters ***** */
	
	
	/* ***** Abstract Methods ***** */
	/* ***** End Abstract Methods ***** */
	
	
	@Override
	public void update() {
		/* Check if we're adjacent to someone of the opposite sex */
		world.queueEvent(new WorldEvent(WorldEventType.ATTEMPT_MATE, this, getPosition()));
		
		/* Pick a z=0 random direction to move in */
		DisplacementVector dis = new DisplacementVector(0, rand.nextInt(3) - 1, rand.nextInt(3) - 1);
		world.queueEvent(new WorldEvent(WorldEventType.OBJECT_MOVE, this, dis));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sex.toString() + " aged ");
		sb.append(age);
		return sb.toString();
	}
}
