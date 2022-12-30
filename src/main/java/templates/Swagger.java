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
			String tagCheck;
			try {
				tagCheck = tag.asJson();
			} catch(NullPointerException tagNull) {
				tagCheck = null;
			}
			tagList.add(tagCheck);
		}
		String[] tagArr = new String[tagList.size()];
		int i = 0;
		for(String tag : tagList) {
			tagArr[i] = tag;
			i++;
		}
		ArrayList<String> definitionList = new ArrayList<String>();
		for(Definition definition : definitions) {
			String definitionCheck;
			try {
				definitionCheck = definition.asJson();
			} catch(NullPointerException tagNull) {
				definitionCheck = null;
			}
			definitionList.add(definitionCheck);
		}
		String[] definitionArr = new String[definitionList.size()];
		i = 0;
		for(String definition : definitionList) {
			definitionArr[i] = definition;
			i++;
		}
		String info;
		try {
			info = this.info.asJson();
		} catch(NullPointerException infoNull) {
			info = null;
		}
		String externalDocs;
		try {
			externalDocs = this.externalDocs.asJson();
		} catch(NullPointerException externalDocsNull) {
			externalDocs = null;
		}
		return new String[] {this.swagger, info, this.host, this.basePath, Transform.arrayToJson(tagArr), Transform.arrayToJson(this.schemes), Transform.pathsToJson(this.paths), Transform.arrayToJson(definitionArr), externalDocs};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
