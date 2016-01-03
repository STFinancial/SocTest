package agent;

import sex.Sex;

/**
 * The main unit of this fake society.
 * Agents have {@link AgentAttribute attributes}
 * that are contained in an {@link AgentAttributeHolder encapsulating class}.
 * Agents reproduce with one another by way of the
 * {@link AgentSexMachine agent sex machine}.
 * @author Timothy
 * @see AgentAttribute
 * @see AgentSexMachine
 */
public class Agent {
	
	/* Independent Attributes */
	private Sex sex;
	private int age; /* In months */
	
	/* Agent Attributes */
	AgentAttributeHolder attributes;
	
	Agent(Sex sex) {
		this.sex = sex;
		this.age = 0;
	}
	
	
	/* ***** Public Getters ***** */
	
	/** @return The current intelligence value of this {@link Agent}. 
	 * @see Intelligence */
	public float getIntelligence() { return attributes.getCurrentIntelligence(); }
	
	/** @return The current physical value of this {@link Agent}.
	 * @see Physical */
	public float getPhysical() { return attributes.getCurrentPhysical(); }
	
	/** @return The "current" sex value of this {@link Agent}.
	 * @see Sex */
	public Sex getSex() { return sex; }
	
	/* ***** End Public Getters ***** */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sex.toString());
		return sb.toString();
	}
}
