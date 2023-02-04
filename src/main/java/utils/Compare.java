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
            }
        } catch(NullPointerException nullPointer) {
            if(!(rest.equals("null") || rest == null)) {
                System.err.println(model.toString() + " : " + rest.toString());
                return false;
            }
        }
        return true;
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean compareGeneric(Object model, Object rest) {
        boolean check = true;
        try {
            if(model.getClass().equals(LinkedTreeMap.class)) {
                if(!compareJSONObject((LinkedTreeMap) model, new JSONObject(rest.toString()).toString())) {
                    check = false;
                }
            } else if(model.getClass().equals(ArrayList.class)) {
                if(!compareJSONArray(convertObjectToList(model), rest.toString())) {
                    check = false;
                }
            } else if(model.getClass().equals(String.class)) {
                if(!compareValues(model.toString(), rest.toString())) {
                    check = false;
                }
            } else {
                if(model != (new JSONObject(rest.toString()))) {
                    if(!(model.toString().replaceAll("0.0", "0")).equals(new JSONObject(rest.toString()).toString())) {
                        System.err.println(model.toString().replaceAll("0.0", "0") + " : " + new JSONObject(rest.toString()).toString());
                        check = false;
                    }
                }
            }
        } catch(NullPointerException nullPointer) {
            if(!(rest.equals("null") || rest == null)) {
                System.err.println(model.toString() + " : " + rest.toString());
                return false;
            }
        }
        return check;
    }
    
    public static boolean compareResponse(Object[] model, JSONObject rest, String[] keys) {
        for(int i = 0; i < keys.length; i++) {
            if(!compareGeneric(model[i], rest.get(keys[i]).toString())) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }
    
    public static boolean compareAASResponse(AssetAdministrationShell aas, String rest) {
        JSONObject restObject = new JSONObject(rest);
        Object[] aasModel = new Object[] {
                aas.getAAS(), aas.getAsset()
        };
        String[] aasKeys = new String[] {
                "AAS", "Asset"
        };
        return compareResponse(aasModel, restObject, aasKeys);
    }
    
    public static boolean compareSubmodelListResponse(List<SubmodelListItem> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        String[] keys = new String[] {
                "id", "idShort", "kind"
        };
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = new JSONObject(object.toString());
            i++;
        }
        i = 0;
        for(SubmodelListItem submodelListItem: model) {
            Object[] modelArray = new Object[] {
                    submodelListItem.getId(),
                    submodelListItem.getIdShort(),
                    submodelListItem.getKind()
            };
            if(!compareResponse(modelArray, restArray[i], keys)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean compareAssetsResponse(List<Asset> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        String[] keys = new String[] {
                "identification", "idShort"
        };
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = (JSONObject) object;
            i++;
        }
        i = 0;
        for(Asset asset : model) {
            Object[] modelArray = new Object[] {
                    asset.getIdentification(),
                    asset.getIdShort()
            };
            if(!compareResponse(modelArray, restArray[i], keys)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean compareSubmodelResponse(Submodel model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        Object[] modelArray = new Object[] {
                model.getAdministration(),
                model.getCategory(),
                model.getDescriptions(),
                model.getHasDataSpecification(),
                model.getIdentification(),
                model.getIdShort(),
                model.getKind(),
                model.getModelType(),
                model.getQualifiers(),
                model.getSemanticId()
        };
        String[] keys = new String[] {
                "administration", "category", "descriptions",
                "hasDataSpecification", "identification", "idShort", "kind",
                "modelType", "qualifiers", "semanticId"
        };
        for(int i = 0; i < keys.length; i++) {
            if(!compareResponse(modelArray, restObject, keys)) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareElementListResponse(List<SubmodelelementListItem> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        String[] keys = new String[] {
                "idShorts", "semId", "semIdType", "shortName", "typeName",
                "unit", "value"
        };
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = (JSONObject) object;
            i++;
        }
        i = 0;
        for(SubmodelelementListItem submodelElementListItem : model) {
            Object[] modelArray = new Object[] {
                    submodelElementListItem.getIdShorts(),
                    submodelElementListItem.getSemId(),
                    submodelElementListItem.getSemIdType(),
                    submodelElementListItem.getShortName(),
                    submodelElementListItem.getTypeName(),
                    submodelElementListItem.getUnit(),
                    submodelElementListItem.getValue()
            };
            if(!compareResponse(modelArray, restArray[i], keys)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean compareElementResponse(SubmodelElement model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        Object[] modelArray = new Object[] {
                model.getElem(),
                model.getParent(),
                model.getWrapper()
        };
        String[] keys = new String[] {
                "elem", "parent", "wrapper"
        };
        if(!compareResponse(modelArray, restObject, keys)) {
            return false;
        }
        return true;
    }

    public static boolean compareCDListResponse(List<ConceptDescriptionListItem> model, String rest) {
        JSONArray jsonArray = new JSONArray(rest);
        JSONObject[] restArray = new JSONObject[jsonArray.length()];
        int i = 0;
        for(Object object : jsonArray) {
            restArray[i] = (JSONObject) object;
            i++;
        }
        i = 0;
        for(ConceptDescriptionListItem cdListItem : model) {
            Object[] modelArray = new Object[] {
                    cdListItem.getIdentification(),
                    cdListItem.getIdShort(),
                    cdListItem.getIsCaseOf(),
                    cdListItem.getShortName()
            };
            String[] keys = new String[] {
                    "identification", "idShort", "isCaseOf", "shortName"
            };
            if(!compareResponse(modelArray, restArray[i], keys)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean compareCDResponse(ConceptDescription model, String rest) {
        JSONObject restObject = new JSONObject(rest);
        Object[] modelArray = new Object[] {
                model.getAdministration(),
                model.getCategory(),
                model.getDescriptions(),
                model.getEmbeddedDataSpecifications(),
                model.getIdentification(),
                model.getIdShort(),
                model.getIsCaseOf(),
                model.getModelType()
        };
        String[] keys = new String[] {
                "administration", "category", "descriptions",
                "embeddedDataSpecifications", "identification", "idShort",
                "isCaseOf", "modelType"
        };
        if(!compareResponse(modelArray, restObject, keys)) {
            return false;
        }
        return true;
    }

}
