package templates;

import java.util.ArrayList;

import utils.Transform;

/**
 * 
 * Template for a path instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Request {
	private final String[] keys = new String[] {"tags", "summary", "description", "operationID", "consumes", "produces", "parameters", "responses", "deprecated"};
	private String requestType;
	private String[] tags;
	private String summary;
	private String description;
	private String operationID;
	private String[] consumes;
	private String[] produces;
	private Parameter[] parameters;
	private Response[] responses;
	private String deprecated;
	
	public Request(String requestType, String[] tags, String summary, String description, String operationID, String[] consumes, String[] produces, Parameter[] parameters, Response[] responses, String deprecated) {
		this.requestType = requestType;
		this.tags = tags;
		this.summary = summary;
		this.description = description;
		this.operationID = operationID;
		this.consumes = consumes;
		this.produces = produces;
		this.parameters = parameters;
		this.responses = responses;
		this.deprecated = deprecated == "true" ? "true" : null;
	}
	
	private String[] getValueArray() {
		String[] parameterArr;
		String[] responseArr;
		try {
			ArrayList<String> parameterList = new ArrayList<String>();
			for(Parameter parameter: parameters) {
				parameterList.add(parameter.asJson());
			}
			parameterArr = new String[parameterList.size()];
			int i = 0;
			for(String parameter : parameterList) {
				parameterArr[i] = parameter;
				i++;
			}
		} catch(NullPointerException parameterNull) {
			parameterArr = null;
		}
		try {
			ArrayList<String> responseList = new ArrayList<String>();
			for(Response response: responses) {
				responseList.add(response.asJson());
			}
			responseArr = new String[responseList.size()];
			int i = 0;
			for(String response : responseList) {
				responseArr[i] = response;
				i++;
			}
		} catch(NullPointerException responseNull) {
			responseArr = null;
		}
		return new String[] {Transform.arrayToJson(this.tags), this.summary, this.description, this.operationID, Transform.arrayToJson(this.consumes), Transform.arrayToJson(this.produces), Transform.arrayToJson(parameterArr), Transform.arrayToJson(responseArr), this.deprecated};
	}
	
	public String getRequestType() {
		return this.requestType;
	}
    
	public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}