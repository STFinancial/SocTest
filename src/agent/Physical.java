package agent;

class Physical extends AgentAttribute {
	//TODO: Split this up into attractiveness and fitness?
	
	private double basePhysical;
	private double currentPhysical;
	private double physicalDecayRate;
	
	Physical(double basePhysical) {
		this.basePhysical = basePhysical;
		this.currentPhysical = basePhysical;
	}
	
	double getBasePhysical() {
		return basePhysical;
	}
	
	double getCurrentPhysical() {
		return currentPhysical;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Base Physical: " + basePhysical + "\t CurrentPhysical: " + currentPhysical);
		return sb.toString();
	}
}
