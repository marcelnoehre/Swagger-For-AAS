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
	private final String[] keys = new String[] {
	        "tags",
	        "summary",
	        "description",
	        "operationID",
	        "consumes",
	        "produces",
	        "parameters",
	        "responses",
	        "deprecated"};
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

	/**
	 * Create a request instance.
	 *
	 * @param requestType the type of the request
	 * @param tags the tags that categorise the request
	 * @param summary the summary of the request
	 * @param description the description of the request
	 * @param operationID the unique operationID of the request
	 * @param consumes the data type consumed by the request
	 * @param produces the data type produced by the request
	 * @param parameters the parameters for sending the request
	 * @param responses the possible responses of the request
	 * @param deprecated whether the corresponding endpoint is deprecated
	 */
	public Request(
	        final String requestType,
	        final String[] tags,
	        final String summary,
	        final String description,
	        final String operationID,
	        final String[] consumes,
	        final String[] produces,
	        final Parameter[] parameters,
	        final Response[] responses,
	        final String deprecated) {
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

	/**
	 * Get the type of the request.
	 *
	 * @return the type of the request
	 */
	public String getRequestType() {
		return this.requestType;
	}

	/**
	 * Get a array of all template values.
	 *
	 * @return array of all template values
	 */
	private String[] getValueArray() {
		String[] parameterArr;
		try {
			ArrayList<String> parameterList =
			        new ArrayList<String>();
			for (Parameter parameter: parameters) {
				parameterList.add(parameter.asJson());
			}
			parameterArr = new String[parameterList.size()];
			int i = 0;
			for (String parameter : parameterList) {
				parameterArr[i] = parameter;
				i++;
			}
		} catch (NullPointerException parameterNull) {
			parameterArr = null;
		}
		return new String[] {
		        Transform.arrayToJson(this.tags),
		        this.summary,
		        this.description,
		        this.operationID,
		        Transform.arrayToJson(this.consumes),
		        Transform.arrayToJson(this.produces),
		        Transform.arrayToJson(parameterArr),
		        Transform.responsesToJson(responses),
		        this.deprecated};
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
