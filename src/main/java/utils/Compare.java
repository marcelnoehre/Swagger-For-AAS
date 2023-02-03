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
        return false;
    }

    public static boolean compareAssetsResponse(List<Asset> model, String rest) {
        return false;
    }

    public static boolean compareSubmodelResponse(Submodel model, String rest) {
        return false;
    }

    public static boolean compareElementListResponse(List<SubmodelelementListItem> model, String rest) {
        return false;
    }

    public static boolean compareElementResponse(SubmodelElement model, String rest) {
        return false;
    }

    public static boolean compareCDListResponse(List<ConceptDescriptionListItem> model, String rest) {
        return false;
    }

    public static boolean compareCDResponse(ConceptDescription model, String rest) {
        return false;
    }

}
