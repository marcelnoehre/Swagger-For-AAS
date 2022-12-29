package templates;

public class Route {
	private String tag;
	private String summary;
	private String type;
	private String route;
	private String[] extraParameter;
	
	public Route(String tag, String summary, String type, String route, String[] extraParameter) {
		this.tag = tag;
		this.summary = summary;
		this.type = type;
		this.route = route;
		this.extraParameter = extraParameter;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public String getSummary() {
		return this.summary;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getRoute() {
		return this.route;
	}
	
	public String[] getExtraParameter() {
		return this.extraParameter;
	}
}
