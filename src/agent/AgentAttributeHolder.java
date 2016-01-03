package agent;

/**
 * This class holds all of the non-simple {@link AgentAttribute Attributes} for a given {@link Agent}.
 * @author Timothy
 */
final class AgentAttributeHolder {
	
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
	void setIntelligence(float baseIntelligence) {
		intelligence = new Intelligence(baseIntelligence);
	}
	
	void setPhysical(float basePhysical) {
		physical = new Physical(basePhysical);
	}
	
	/* Non-Public Getters called by Agent. One Liners. */
	float getCurrentIntelligence() { return intelligence.getCurrentIntelligence(); }
	float getCurrentPhysical() { return physical.getCurrentPhysical(); }
}
