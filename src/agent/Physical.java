package agent;

class Physical extends AgentAttribute {
	//TODO: Split this up into attractiveness and fitness?
	
	private float basePhysical;
	private float currentPhysical;
	private float physicalDecayRate;
	
	Physical(float basePhysical) {
		this.basePhysical = basePhysical;
		this.currentPhysical = basePhysical;
	}
	
	float getBasePhysical() {
		return basePhysical;
	}
	
	float getCurrentPhysical() {
		return currentPhysical;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Base Physical: " + basePhysical + "\t CurrentPhysical: " + currentPhysical);
		return sb.toString();
	}
}
