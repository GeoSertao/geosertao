package edu.ifpb.geosertao.geosertao.core.harvest;

/**
 *
 * @author Fabio
 */
public abstract class ResourceType {

    public static final int WEB_MAP_SERVICE = 0;
    public static final int WEB_FEATURE_SERVICE = 1;
    public static final int WEB_COVERAGE_SERVICE = 2;
    public static final int WMS_FEATURE_TYPE = 3;
    public static final int WFS_FEATURE_TYPE = 4;
    public static final int WCS_COVERAGE = 5;
    public static final int CATALOG_SERVICE = 6;
    public static final int CATALOG_SERVICE_RECORD = 7;
    public static final int WEB_PAGE = 8;

    public static String getResourceName(int resourceType) {
        switch (resourceType) {
            case WEB_MAP_SERVICE:
                return "Web Map Service";
            case WEB_FEATURE_SERVICE:
                return "Web Feature Service";
            case WEB_COVERAGE_SERVICE:
                return "Web Coverage Service";
            case WMS_FEATURE_TYPE:
                return "Web Map Service Feature Type";
            case WFS_FEATURE_TYPE:
                return "Web Feature Service Feature Type";
            case WCS_COVERAGE:
                return "Web Coverage Service Coverage";
            case CATALOG_SERVICE:
                return "Catalog Service";
            case CATALOG_SERVICE_RECORD:
                return "Catalog Service Metadata Record";
            case WEB_PAGE:
                return "Web Page";

        }
        return null;

    }

}
