package deprecated;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import swagger2java.model.Asset;
import swagger2java.model.AssetAdministrationShell;
import swagger2java.model.ConceptDescription;
import swagger2java.model.ConceptDescriptionListItem;
import swagger2java.model.Submodel;
import swagger2java.model.SubmodelElement;
import swagger2java.model.SubmodelListItem;
import swagger2java.model.SubmodelelementListItem;
import utils.Transform;

public class Compare {
       
    public static boolean compareAASResponse(AssetAdministrationShell model, String rest) {
        String[] list = new String[] { model.toString(), rest };
        for(int i = 0; i < list.length; i++) {
            list[i] = Transform.removeSpecialChars(
                    list[i].replaceAll("\\s", "")
                    .replaceAll("=", ":").replaceAll("\"", "")
                    .replaceAll("0.0", "0").replaceAll(",Asset:", "asset:")
                    .replaceAll("classAssetAdministrationShell", ""));
        }
        return list[0].equals(list[1]);
    }
    
    public static boolean compareSubmodelListResponse(List<SubmodelListItem> model, String rest) {
        ArrayList<String> list = new ArrayList<String>();
        for(SubmodelListItem item : model) {
            list.add(item.toString().replaceAll("class SubmodelListItem ", "").replaceAll("\\s", "").replaceAll("=", ":"));
        }
        String[] array = new String[list.size()];
        int i = 0;
        for(String item : list) {
            array[i] = item;
            i++;
        }
        i = 0;
        for(String item : list) {
            array[i] = item;
            i++;
        }
        i = 0;
        for(Object item : new JSONArray(rest)) {
            JSONObject object = (JSONObject) item;
            String[] keys = new String[] {"idShort", "kind", "id"};
            String transformedRest = "{";
            for(String key : keys) {
                transformedRest += key + ":" + object.get(key);
            }
            if(!Transform.removeSpecialChars(array[i]).equals(Transform.removeSpecialChars(transformedRest.replaceAll("\"", "") + "}"))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean compareAssetsResponse(List<Asset> model, String rest) {
        String[] list = new String[] { model.toString(), rest };
        for(int i = 0; i < list.length; i++) {
            list[i] = Transform.removeSpecialChars(
                    list[i].replaceAll("\\s", "")
                    .replaceAll("=", ":").replaceAll("\"", "")
                    .replaceAll(",idShort:", "idShort:")
                    .replaceAll("classAsset", ""));
        }
        return list[0].equals(list[1]);
    }

    public static boolean compareSubmodelResponse(Submodel model, String rest) {
        String transformedModel = model.toString().replaceAll("class Submodel", "").replace("=", ":").replace("0.0", "0").replaceAll("\\s", "");
        JSONObject object = new JSONObject(rest);
        String transformedRest = "{";
        String[] keys = new String[] {"semanticId", "identification", "idShort", "hasDataSpecification", "administration", "kind", "qualifiers", "modelType", "category", "descriptions"};
        for(String key : keys) {
            if(key.equals("semanticId")) {
                transformedRest += key + ":{keys:[";
                JSONArray keysList = new JSONArray(new JSONObject(new JSONObject(object.get(key).toString()).toString()).get("keys").toString());
                int i = 1;
                for(Object keyObject : keysList) {
                    JSONObject json = (JSONObject) keyObject;
                    transformedRest += "{type:" + json.get("type") + ",local:" + json.get("local") + ",value:" + json.get("value") + ",index:" + json.get("index") + ",idType:" + json.get("idType");
                    transformedRest += i < keysList.length() ? "}," : "}";
                }
                transformedRest += "]}";
            } else {
                transformedRest += key + ":" + object.get(key);
            }
        }
        return Transform.removeSpecialChars(transformedModel).equals(Transform.removeSpecialChars((transformedRest+"}").replace("\"", "")).replaceAll("\\s", ""));
    }

    public static boolean compareElementListResponse(List<SubmodelelementListItem> model, String rest) {
        ArrayList<String> list = new ArrayList<String>();
        for(SubmodelelementListItem item : model) {
            list.add(item.toString().replaceAll("class SubmodelelementListItem ", "").replaceAll("\\s", "").replaceAll("=", ":"));
        }
        String[] array = new String[list.size()];
        int i = 0;
        for(String item : list) {
            array[i] = item;
            i++;
        }
        i = 0;
        for(Object item : new JSONArray(rest)) {
            JSONObject object = (JSONObject) item;
            String[] keys = new String[] {"semId", "unit", "semIdType", "idShorts", "typeName", "shortName", "value"};
            String transformedRest = "{";
            for(String key : keys) {
                transformedRest += key + ":" + object.get(key);
            }
            if(!Transform.removeSpecialChars(array[i]).equals(Transform.removeSpecialChars(transformedRest.replaceAll("\"", "").replaceAll("\\s", "") + "}"))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean compareElementResponse(SubmodelElement model, String rest) {
        String transformedModel = model.toString().replaceAll("\\s", "").replaceAll("classSubmodelElement", "").replaceAll("=", ":").replaceAll("0.0", "0");
        JSONObject object = new JSONObject(rest);
        String transformedRest = "{";
        String[] keys = new String[] {"elem", "parent"};
        String[][] innerKeys = new String[][] {
                new String[] {"value", "valueId", "semanticId", "constraints", "hasDataSpecification", "idShort", "category", "modelType", "valueType", "kind", "descriptions"},
                new String[] {"semanticId", "qualifiers", "hasDataSpecification", "identification", "administration", "idShort", "category", "modelType", "kind", "descriptions"}
        };
        JSONObject[] jsonObjects = new JSONObject[keys.length];
        for(int i = 0; i < keys.length; i++) {
            jsonObjects[i] = new JSONObject(object.get(keys[i]).toString());
            transformedRest += keys[i] + ":{";
            for(String key : innerKeys[i]) {
                if(key.equals("semanticId")) {
                    transformedRest += key + ":{keys:[";
                    JSONArray keysList = new JSONArray(new JSONObject(new JSONObject(jsonObjects[i].get(key).toString()).toString()).get("keys").toString());
                    int j = 1;
                    for(Object keyObject : keysList) {
                        JSONObject json = (JSONObject) keyObject;
                        transformedRest += "{type:" + json.get("type") + ",local:" + json.get("local") + ",value:" + json.get("value") + ",index:" + json.get("index") + ",idType:" + json.get("idType");
                        transformedRest += j < keysList.length() ? "}," : "}";
                    }
                    transformedRest += "]},";
                } else {
                    transformedRest += key + ":" + jsonObjects[i].get(key) + ",";
                }
            }
            transformedRest = transformedRest.substring(0, transformedRest.length() - 1) + "}";
        }
        transformedRest += "wrapper:{submodelElement:{";
        JSONObject wrapper = object.getJSONObject("wrapper").getJSONObject("submodelElement");
        String[] wrapperKeys = new String[] {"value", "valueId", "semanticId", "constraints", "hasDataSpecification", "idShort", "category", "modelType", "valueType", "kind", "descriptions"};
        for(String key : wrapperKeys) {
            if(key.equals("semanticId")) {
                transformedRest += key + ":{keys:[";
                JSONArray keysList = new JSONArray(new JSONObject(new JSONObject(wrapper.get(key).toString()).toString()).get("keys").toString());
                int j = 1;
                for(Object keyObject : keysList) {
                    JSONObject json = (JSONObject) keyObject;
                    transformedRest += "{type:" + json.get("type") + ",local:" + json.get("local") + ",value:" + json.get("value") + ",index:" + json.get("index") + ",idType:" + json.get("idType");
                    transformedRest += j < keysList.length() ? "}," : "}";
                }
                transformedRest += "]},";
            } else {
                transformedRest += key + ":" + wrapper.get(key) + ",";
            }
        }
        return Transform.removeSpecialChars(transformedModel).equals(Transform.removeSpecialChars((transformedRest.substring(0, transformedRest.length() - 1)+"}}}").replace("\"", "").replaceAll("\\s", "")));
    }

    public static boolean compareCDListResponse(List<ConceptDescriptionListItem> model, String rest) {
        ArrayList<String> list = new ArrayList<String>();
        for(ConceptDescriptionListItem item : model) {
            list.add(item.toString().replaceAll("class ConceptDescriptionListItem ", "").replaceAll("\\s", "").replaceAll("=", ":"));
        }
        String[] array = new String[list.size()];
        int i = 0;
        for(String item : list) {
            array[i] = item;
            i++;
        }
        i = 0;
        for(Object item : new JSONArray(rest)) {
            JSONObject json = (JSONObject) item;
            String transformedModel = Transform.removeSpecialChars(array[i].replaceAll(",", "").replace("0.0", "0"));
            String[] keys = new String[] {"identification", "idShort", "isCaseOf", "shortName"};
            String transformedRest = "{";
            for(String key : keys) {
                if(key.equals("isCaseOf") && !json.get(key).equals(null)) {
                    transformedRest += "isCaseOf:[";
                    String[] innerKeys = new String[] {"type", "local", "value", "index", "idType"};
                    JSONArray caseOf = json.getJSONArray(key);
                    for(Object caseOfObject : caseOf) {
                        transformedRest += "{keys:[";
                        boolean filled = ((JSONObject) caseOfObject).getJSONArray("keys").length() > 0;
                        transformedRest += filled ? "{" : "";
                        for(Object keysObject : ((JSONObject) caseOfObject).getJSONArray("keys")) {
                            for(String innerKey: innerKeys) {
                                transformedRest += innerKey + ":" + ((JSONObject) keysObject).get(innerKey);  
                            }
                        }
                        transformedRest += filled ? "}" : "";
                    }
                    transformedRest += "]}]";
                } else {
                    transformedRest += key + ":" + json.get(key);   
                }
            }
            if(!transformedModel.equals(Transform.removeSpecialChars(transformedRest.replaceAll("\"", "").replaceAll(",", "").replaceAll("\\s","") + "}"))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean compareCDResponse(ConceptDescription model, String rest) {
        String transformedModel = Transform.removeSpecialChars(model.toString().replaceAll("\\s", "").replace("classConceptDescription", "").replaceAll("0.0", "0").replaceAll("=", ":"));
        String transformedRest = "{";
        String[] keys = new String[] {"identification", "idShort", "administration", "isCaseOf", "modelType", "category", "descriptions"};
        JSONObject object = new JSONObject(rest);
        for(String key : keys) {
            transformedRest += key + ":" + object.get(key);
        }
        JSONArray array = object.getJSONArray("embeddedDataSpecifications");
        transformedRest += "embeddedDataSpecifications:[";
        for(Object element : array) {
            JSONObject json = (JSONObject) element;
            if(!json.get("dataSpecification").equals("{}")) {
                transformedRest += "{dataSpecification:";
                String[] innerKeys = new String[] {"type", "local", "value", "index", "idType"};
                JSONObject dataSpecification = json.getJSONObject("dataSpecification");
                transformedRest += "{keys:[{";
                for(Object keysObject : dataSpecification.getJSONArray("keys")) {
                    for(String innerKey: innerKeys) {
                        transformedRest += innerKey + ":" + ((JSONObject) keysObject).get(innerKey) + ",";
                    }
                }
                transformedRest += "]}";
            } else {
                transformedRest += "{dataSpecification:" + json.get("dataSpecification");
            }
            transformedRest += ",dataSpecificationContent:{";
            json = json.getJSONObject("dataSpecificationContent");
            keys = new String[] {"preferredName", "shortName", "unit", "unitId", "valueFormat", "sourceOfDefinition", "symbol", "dataType", "definition"};
            for(String key : keys) {
                transformedRest += key + ":" + json.get(key) + ",";
            }
            transformedRest = transformedRest.substring(0, transformedRest.length() - 1) + "}}]";            
        }
        transformedRest += "}";
        return transformedModel.equals(Transform.removeSpecialChars(transformedRest.replaceAll("\"", "").replaceAll("\\s", "")));
    }
}
