package agent;

class Knowledge extends AgentAttribute {

	
	private float knowledge;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Knowledge: " + knowledge);
		return sb.toString();
	}
}
