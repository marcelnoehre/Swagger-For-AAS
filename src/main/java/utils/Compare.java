package utils;

import java.util.List;

import swagger2java.model.Asset;
import swagger2java.model.AssetAdministrationShell;
import swagger2java.model.ConceptDescription;
import swagger2java.model.ConceptDescriptionListItem;
import swagger2java.model.Submodel;
import swagger2java.model.SubmodelElement;
import swagger2java.model.SubmodelListItem;
import swagger2java.model.SubmodelelementListItem;

public class Compare {
    
    //TODO
    public static boolean compareAASResponse(AssetAdministrationShell model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }
    
    //TODO
    public static boolean compareSubmodelListResponse(List<SubmodelListItem> model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }

    //TODO
    public static boolean compareAssetsResponse(List<Asset> model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }

    //TODO
    public static boolean compareSubmodelResponse(Submodel model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }

    //TODO
    public static boolean compareElementListResponse(List<SubmodelelementListItem> model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }

    //TODO
    public static boolean compareElementResponse(SubmodelElement model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }

    //TODO
    public static boolean compareCDListResponse(List<ConceptDescriptionListItem> model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }

    //TODO
    public static boolean compareCDResponse(ConceptDescription model, String rest) {
        System.out.println(model.toString());
        System.out.println(rest);
        return false;
    }
}
