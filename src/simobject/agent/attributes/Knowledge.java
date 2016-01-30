package simobject.agent.attributes;

class Knowledge extends AgentAttribute {

	
	private double knowledge;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Knowledge: " + knowledge);
		return sb.toString();
	}
}
