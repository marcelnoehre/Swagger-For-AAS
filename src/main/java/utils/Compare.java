package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
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
            String rest) {
        boolean check = true;
        try {
        for(Iterator iterator = model.keySet().iterator();
                iterator.hasNext();) {
            String key = (String) iterator.next();
                if(model.get(key).getClass().equals(LinkedTreeMap.class)) {
                    if(!compareJSONObject((LinkedTreeMap) model.get(key), new JSONObject(rest).get(key).toString())) {
                        check = false;
                    }
                } else if(model.get(key).getClass().equals(ArrayList.class)) {
                    if(!compareJSONArray(convertObjectToList(model.get(key)), new JSONObject(rest).get(key).toString())) {
                        check = false;
                    }
                } else if(model.get(key).getClass().equals(String.class)) {
                    if(!compareValues(model.get(key).toString(), new JSONObject(rest).get(key).toString())) {
                        check = false;
                    }
                } else {
                    if(model.get(key) != (new JSONObject(rest).get(key))) {
                        if(!(model.get(key).toString().replaceAll("0.0", "0")).equals(new JSONObject(rest).get(key).toString())) {
                            System.err.println(model.get(key).toString().replaceAll("0.0", "0") + " : " + new JSONObject(rest).get(key).toString());
                            check = false;
                        }
                    }
                }
            }
        } catch (NullPointerException nullPointer) { }
        return check;
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean compareJSONArray(List<?> list, String rest) {
        boolean check = true;
        try {
            JSONArray jsonArray = new JSONArray(rest);
            Object[] restArray = new Object[rest.length()];
            int i = 0;
            for(Object object : jsonArray) {
                restArray[i] = object;
                i++;
            }
            i = 0;
            for(Object object : list) {
                try {
                    if(!compareJSONObject((LinkedTreeMap) object, restArray[i].toString())) {
                        check = false;
                    }
                } catch(NullPointerException nullPointer) { }
                i++;
            }   
        } catch(JSONException jsonException) { }
        return check;
    }
    
    public static boolean compareValues(String model, String rest) {
        try {
            model.getClass();
            model = Transform.removeSpecialChars(model);
            rest = Transform.removeSpecialChars(rest);
            if(!model.equals(rest)) {
                System.err.println(model + " : " + rest);
                return false;
            } else {
                return true;
            }
        } catch(NullPointerException nullPointer) {
            return rest == null;
        }
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean compareAASResponse(AssetAdministrationShell model, String rest) {
        boolean check = true;
        JSONObject restObject = new JSONObject(rest);
        if(!compareJSONObject((LinkedTreeMap) model.getAAS(), restObject.get("AAS").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getAsset(), restObject.get("Asset").toString())) {
            check = false;
        }
        return check;
    }
    
    @SuppressWarnings("rawtypes")
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
            if(!compareJSONObject((LinkedTreeMap) submodelListItem.getId(), restArray[i].get("id").toString())) {
                check = false;
            }
            if(!compareValues(submodelListItem.getIdShort().toString(), restArray[i].get("idShort").toString())) {
                check = false;
            }
            if(!compareValues(submodelListItem.getKind().toString(), restArray[i].get("kind").toString())) {
                check = false;
            }
            i++;
        }
        return check;
    }

    @SuppressWarnings("rawtypes")
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
            if(!compareJSONObject((LinkedTreeMap) asset.getIdentification(), restArray[i].get("identification").toString())) {
                check = false;
            }
            if(!compareValues(asset.getIdShort().toString(), restArray[i].get("idShort").toString())) {
                check = false;
            }
        }
        return check;
    }

    @SuppressWarnings("rawtypes")
    public static boolean compareSubmodelResponse(Submodel model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        boolean check = true;
        if(!compareJSONObject((LinkedTreeMap) model.getAdministration(), restObject.get("administration").toString())) {
            check = false;
        }
        if(!compareValues(model.getCategory(), restObject.get("category").toString())) {
            check = false;
        }
        if(!compareJSONArray(model.getDescriptions(), restObject.get("descriptions").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getHasDataSpecification(), restObject.get("hasDataSpecification").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getIdentification(), restObject.get("identification").toString())) {
            check = false;
        }
        if(!compareValues(model.getIdShort(), restObject.get("idShort").toString())) {
            check = false;
        }
        if(!compareValues(model.getKind(), restObject.get("kind").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getModelType(), restObject.get("modelType").toString())) {
            check = false;
        }
        if(!compareJSONArray(model.getQualifiers(), restObject.get("qualifiers").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getSemanticId(), restObject.get("semanticId").toString())) {
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
            if(!compareValues(submodelElementListItem.getIdShorts(), restArray[i].get("idShorts").toString())) {
                check = false;
            }     
            if(!compareValues(submodelElementListItem.getSemId(), restArray[i].get("semId").toString())) {
                check = false;
            } 
            if(!compareValues(submodelElementListItem.getSemIdType(), restArray[i].get("semIdType").toString())) {
                check = false;
            } 
            if(!compareValues(submodelElementListItem.getShortName(), restArray[i].get("shortName").toString())) {
                check = false;
            } 
            if(!compareValues(submodelElementListItem.getTypeName(), restArray[i].get("typeName").toString())) {
                check = false;
            } 
            if(!compareValues(submodelElementListItem.getUnit(), restArray[i].get("unit").toString())) {
                check = false;
            } 
            if(!compareValues(submodelElementListItem.getValue(), restArray[i].get("value").toString())) {
                check = false;
            }
            i++;
        }
        return check;
    }

    @SuppressWarnings("rawtypes")
    public static boolean compareElementResponse(SubmodelElement model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        boolean check = true;
        if(!compareJSONObject((LinkedTreeMap) model.getElem(), restObject.get("elem").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getParent(), restObject.get("parent").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getWrapper(), restObject.get("wrapper").toString())) {
            check = false;
        }
        return check;
    }

    @SuppressWarnings("rawtypes")
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
            if(!compareJSONObject((LinkedTreeMap) cdListItem.getIdentification(), restArray[i].get("identification").toString())) {
                check = false;
            }
            if(!compareValues(cdListItem.getIdShort(), restArray[i].get("idShort").toString())) {
                check = false;
            }            
            if(!compareJSONArray(cdListItem.getIsCaseOf(), restArray[i].get("isCaseOf").toString())) {
                check = false;
            }
            if(!compareValues(cdListItem.getShortName(), restArray[i].get("shortName").toString())) {
                check = false;
            }
            i++;
        }
        return check;
    }

    @SuppressWarnings("rawtypes")
    public static boolean compareCDResponse(ConceptDescription model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        boolean check = true;
        if(!compareJSONObject((LinkedTreeMap) model.getAdministration(), restObject.get("administration").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getCategory(), restObject.get("category").toString())) {
            check = false;
        }
        if(!compareJSONArray(model.getDescriptions(), restObject.get("descriptions").toString())) {
            check = false;
        }
        if(!compareJSONArray(model.getEmbeddedDataSpecifications(), restObject.get("embeddedDataSpecifications").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getIdentification(), restObject.get("identification").toString())) {
            check = false;
        }
        if(!compareValues(model.getIdShort(), restObject.get("idShort").toString().toString())) {
            check = false;
        }          
        if(!compareJSONArray(model.getIsCaseOf(), restObject.get("isCaseOf").toString())) {
            check = false;
        }
        if(!compareJSONObject((LinkedTreeMap) model.getModelType(), restObject.get("modelType").toString())) {
            check = false;
        }
        return check;
    }

}
