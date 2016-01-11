package agent;

import sex.Sex;
import utility.SimObject;

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
	
	/* Independent Attributes */
	private int age; /* In months */
	protected Sex sex;
	
	/* Agent Attributes */
	AgentAttributeHolder attributes;
	
	Agent() {
		this.age = 0;
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sex.toString() + " aged ");
		sb.append(age);
		return sb.toString();
	}
}
