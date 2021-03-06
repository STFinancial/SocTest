package simobject.agent.attributes;

import simobject.agent.Agent;

/**
 * This class holds all of the non-simple {@link AgentAttribute Attributes} for a given {@link Agent}.
 * @author Timothy
 */
public final class AgentAttributeHolder {
	
	/* Attributes */
	Intelligence intelligence;
	Knowledge knowledge;
	Physical physical;
	Satisfaction satisfaction;
	
	AgentAttributeHolder() {
		knowledge = new Knowledge();
		satisfaction = new Satisfaction();
	}
	
	/* Non-Public Setters called by Builder */
	void setIntelligence(double baseIntelligence) {
		intelligence = new Intelligence(baseIntelligence);
	}
	
	void setPhysical(double basePhysical) {
		physical = new Physical(basePhysical);
	}
	
	/* Non-Public Getters called by Agent. One Liners. */
	public double getCurrentIntelligence() { return intelligence.getCurrentIntelligence(); }
	public double getCurrentPhysical() { return physical.getCurrentPhysical(); }
}
