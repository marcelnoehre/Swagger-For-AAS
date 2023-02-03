package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.internal.LinkedTreeMap;

import swagger2java.model.Asset;
import swagger2java.model.AssetAdministrationShell;
import swagger2java.model.ConceptDescription;
import swagger2java.model.ConceptDescriptionListItem;
import swagger2java.model.Submodel;
import swagger2java.model.SubmodelElement;
import swagger2java.model.SubmodelListItem;
import swagger2java.model.SubmodelelementListItem;

public class Compare {
    
    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean compareJSONObject(
            LinkedTreeMap model,
            JSONObject rest) {
        boolean check = true;
        for(Iterator iterator = model.keySet().iterator();
                iterator.hasNext();) {
            String key = (String) iterator.next();
            try {
                if(model.get(key).getClass().equals(LinkedTreeMap.class)) {
                    if(!compareJSONObject((LinkedTreeMap) model.get(key), rest.getJSONObject(key))) {
                        check = false;
                    }
                } else if(model.get(key).getClass().equals(ArrayList.class)) {
                    if(!compareJSONArray(convertObjectToList(model.get(key)), new JSONArray(rest.get(key).toString()))) {
                        check = false;
                    }
                } else if(model.get(key).getClass().equals(String.class)) {
                    if(!model.get(key).equals(rest.get(key))) {
                        check = false;
                    }
                } else {
                    if(model.get(key) != (rest.get(key))) {
                        check = false;
                    }
                }
            } catch (NullPointerException nulPointer) {
                if(model.get(key) != (rest.get(key))) {
                    check = false;
                }
            }
        }
        return check;
    }
    
    public static boolean compareJSONArray(List<?> list, JSONArray rest) {
        boolean check = true;
        Object[] restArray = new Object[rest.length()];
        int i = 0;
        for(Object object : rest) {
            restArray[i] = object;
            i++;
        }
        i = 0;
        for(Object object : list) {
            try {
                if(!compareJSONObject((LinkedTreeMap) object, (JSONObject) restArray[i])) {
                    check = false;
                }
            } catch(NullPointerException nullPointer) {
                if(object != restArray[i]) {
                    check = false;
                }
            }
            i++;
        }
        return check;
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean compareAASResponse(AssetAdministrationShell model, String rest) {
        boolean check = true;
        JSONObject restObject = new JSONObject(rest);
        compareJSONObject((LinkedTreeMap) model.getAAS(), (JSONObject) restObject.get("AAS"));
        compareJSONObject((LinkedTreeMap) model.getAsset(), (JSONObject) restObject.get("Asset"));
        new JSONObject(rest).getJSONObject("AAS");
        return check;
    }
    
    public static boolean compareSubmodelListResponse(List<SubmodelListItem> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        boolean check = true;
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = (JSONObject) object;
            i++;
        }
        i = 0;
        for(SubmodelListItem submodelListItem: model) {
            if(compareJSONObject((LinkedTreeMap) submodelListItem.getId(), new JSONObject(restArray[i].get("id")))) {
                check = false;
            }
            if(!submodelListItem.getIdShort().equals(restArray[i].get("idShort"))) {
                check = false;
            }
            if(!submodelListItem.getKind().equals(restArray[i].get("kind"))) {
                check = false;
            }
            i++;
        }
        return check;
    }

    public static boolean compareAssetsResponse(List<Asset> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        boolean check = true;
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = (JSONObject) object;
            i++;
        }
        i = 0;
        for(Asset asset : model) {
            if(!compareJSONObject((LinkedTreeMap) asset.getIdentification(), new JSONObject(restArray[i].get("identification")))) {
                check = false;
            }
            if(!asset.getIdShort().equals(restArray[i].get("idShort"))) {
                check = false;
            }
        }
        return check;
    }

    public static boolean compareSubmodelResponse(Submodel model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        boolean check = false;
        if(compareJSONObject((LinkedTreeMap) model.getAdministration(), (JSONObject) restObject.get("administration"))) {
            check = false;
        }
        if(!model.getCategory().equals(restObject.get("category"))) {
            check = false;
        }
        if(!compareJSONArray(model.getDescriptions(), new JSONArray(restObject.get("descriptions").toString()))) {
            check = false;
        }
        if(compareJSONObject((LinkedTreeMap) model.getHasDataSpecification(), (JSONObject) restObject.get("hasDataSpecification"))) {
            check = false;
        }
        if(compareJSONObject((LinkedTreeMap) model.getIdentification(), (JSONObject) restObject.get("identification"))) {
            check = false;
        }
        if(!model.getIdShort().equals(restObject.get("idShort"))) {
            check = false;
        }
        if(!model.getKind().equals(restObject.get("kind"))) {
            check = false;
        }
        if(compareJSONObject((LinkedTreeMap) model.getModelType(), (JSONObject) restObject.get("modelType"))) {
            check = false;
        }
        if(!compareJSONArray(model.getQualifiers(), new JSONArray(restObject.get("qualifiers").toString()))) {
            check = false;
        }
        if(compareJSONObject((LinkedTreeMap) model.getSemanticId(), (JSONObject) restObject.get("semanticId"))) {
            check = false;
        }
        return check;
    }

    public static boolean compareElementListResponse(List<SubmodelelementListItem> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        boolean check = true;
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = (JSONObject) object;
            i++;
        }
        i = 0;
        for(SubmodelelementListItem submodelElementListItem : model) {
            if(!submodelElementListItem.getIdShorts().equals(restArray[i].get("idShort"))) {
                check = false;
            }        
            if(!submodelElementListItem.getSemId().equals(restArray[i].get("semId"))) {
                check = false;
            }  
            if(!submodelElementListItem.getSemIdType().equals(restArray[i].get("semIdType"))) {
                check = false;
            }  
            if(!submodelElementListItem.getShortName().equals(restArray[i].get("shortName"))) {
                check = false;
            }  
            if(!submodelElementListItem.getTypeName().equals(restArray[i].get("typeName"))) {
                check = false;
            }  
            if(!submodelElementListItem.getUnit().equals(restArray[i].get("unit"))) {
                check = false;
            }  
            if(!submodelElementListItem.getValue().equals(restArray[i].get("value"))) {
                check = false;
            }
        }
        return check;
    }

    public static boolean compareElementResponse(SubmodelElement model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        boolean check = true;
        if(!compareJSONObject((LinkedTreeMap) model.getElem(), (JSONObject) restObject.get("elem"))) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getParent(), (JSONObject) restObject.get("parent"))) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getWrapper(), (JSONObject) restObject.get("wrapper"))) {
            check = false;
        }
        return check;
    }

    public static boolean compareCDListResponse(List<ConceptDescriptionListItem> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        boolean check = true;
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = (JSONObject) object;
            i++;
        }
        i = 0;
        for(ConceptDescriptionListItem cdListItem : model) {
            if(!compareJSONObject((LinkedTreeMap) cdListItem.getIdentification(), (JSONObject) restArray[i].get("identification"))) {
                check = false;
            }
            if(!cdListItem.getIdShort().equals(restArray[i].get("idShort"))) {
                check = false;
            }        
            if(!compareJSONObject((LinkedTreeMap) cdListItem.getIsCaseOf(), (JSONObject) restArray[i].get("administration"))) {
                check = false;
            }
            if(!cdListItem.getShortName().equals(restArray[i].get("shortName"))) {
                check = false;
            }        
        }
        return check;
    }

    public static boolean compareCDResponse(ConceptDescription model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        boolean check = false;
        if(!compareJSONObject((LinkedTreeMap) model.getAdministration(), (JSONObject) restObject.get("administration"))) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getCategory(), (JSONObject) restObject.get("category"))) {
            check = false;
        }
        if(!compareJSONArray(model.getDescriptions(), new JSONArray(restObject.get("descriptions").toString()))) {
            check = false;
        }
        if(!compareJSONArray(model.getEmbeddedDataSpecifications(), new JSONArray(restObject.get("embeddedDataSpecifications").toString()))) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getIdentification(), (JSONObject) restObject.get("identification"))) {
            check = false;
        }
        if(!model.getIdShort().equals(restObject.get("idShort"))) {
            check = false;
        }        
        if(!compareJSONObject((LinkedTreeMap) model.getIsCaseOf(), (JSONObject) restObject.get("isCaseOf"))) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getModelType(), (JSONObject) restObject.get("modelType"))) {
            check = false;
        }
        return false;
    }

}
