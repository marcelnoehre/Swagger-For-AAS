package templates;

import utils.Transform;

public class SecurityDefinition {
	private String[] keys = new String[] {"type", "name", "in", "authorizationUrl", "flow", "scopes"};
	private String id;
	private String type;
	private String name;
	private String in;
	private String authorizationUrl;
	private String flow;
	private Scope[] scopes;
	
	private String[] getValueArray() {
		return new String[] {this.type, this.name, this.in, this.authorizationUrl, this.flow, Transform.scopesToJson(scopes)};
	}
	
	public String getId() {
		return this.id;
	}
	
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
