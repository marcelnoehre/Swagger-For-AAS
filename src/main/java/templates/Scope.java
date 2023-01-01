package templates;

public class Scope {
	private String id;
	private String info;

	public Scope(String id, String info) {
		this.id = id;
		this.info = info;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getInfo() {
		return this.info;
	}
}
