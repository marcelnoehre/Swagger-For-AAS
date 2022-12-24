package templates;

import java.util.ArrayList;

import utils.Transform;

/**
 * 
 * Template for the swagger instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Swagger {
	private final String[] keys = new String[] {"swagger", "info", "host", "basePath", "tags", "schemes", "paths", "definitions", "externalDocs"};
	private String swagger;
	private Info info;
	private String host;
	private String basePath;
	private Tag[] tags;
	private String[] schemes;
	private Path[] paths;
	private Definition[] definitions;
	private ExternalDocs externalDocs;
	
	public Swagger(String swagger, Info info, String host, String basePath, Tag[] tags, String[] schemes, Path[] paths, Definition[] definitions, ExternalDocs externalDocs) {
		this.swagger = swagger;
		this.info = info; 
		this.host = host;
		this.basePath = basePath;
		this.tags = tags;
		this.schemes = schemes;
		this.paths = paths;
		this.definitions = definitions;
		this.externalDocs = externalDocs;
	}
	
	private String[] getValueArray() {
		ArrayList<String> tagList = new ArrayList<String>();
		for(Tag tag : tags) {
			tagList.add(tag.asJson());
		}
		ArrayList<String> definitionList = new ArrayList<String>();
		for(Definition definition : definitions) {
			definitionList.add(definition.asJson());
		}
		return new String[] {this.swagger, this.info.asJson(), this.host, this.basePath, Transform.arrayToJson((String[]) tagList.toArray()), Transform.arrayToJson(this.schemes), Transform.pathsToJson(this.paths), Transform.arrayToJson((String[]) definitionList.toArray()), this.externalDocs.asJson()};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
