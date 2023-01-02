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
	private final String[] keys = new String[] {"swagger", "info", "host", "basePath", "tags", "schemes", "paths", "securityDefinitions", "definitions", "externalDocs"};
	private String swagger;
	private Info info;
	private String host;
	private String basePath;
	private Tag[] tags;
	private String[] schemes;
	private Path[] paths;
	private SecurityDefinition[] securityDefinitions;
	private Definition[] definitions;
	private ExternalDocs externalDocs;
	
	/**
	 * Create a swagger instace.
	 * 
	 * @param swagger the swagger version
	 * @param info the information about the aas
	 * @param host the hostname of the path
	 * @param basePath the basepath for all requests
	 * @param tags the list of tags
	 * @param schemes the list of defined schemes
	 * @param paths the list of paths to endpoints
	 * @param securityDefinitions the list of security definitions
	 * @param definitions the list of definitions
	 * @param externalDocs the list of external documents
	 */
	public Swagger(String swagger, Info info, String host, String basePath, Tag[] tags, String[] schemes, Path[] paths, SecurityDefinition[] securityDefinitions, Definition[] definitions, ExternalDocs externalDocs) {
		this.swagger = swagger;
		this.info = info; 
		this.host = host;
		this.basePath = basePath;
		this.tags = tags;
		this.schemes = schemes;
		this.paths = paths;
		this.securityDefinitions = securityDefinitions;
		this.definitions = definitions;
		this.externalDocs = externalDocs;
	}
		
	/**
	 * Get a array of all template values.
	 * 
	 * @return array of all template values
	 */
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
		return new String[] {this.swagger, info, this.host, this.basePath, Transform.arrayToJson(tagArr), Transform.arrayToJson(this.schemes), Transform.pathsToJson(this.paths), Transform.securityDefinitionsToJson(securityDefinitions), Transform.definitionsToJson(this.definitions), externalDocs};
	}
        
	/**
	 * Get the instance as JSON string.
	 * 
	 * @return json string of the instance
	 */
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
