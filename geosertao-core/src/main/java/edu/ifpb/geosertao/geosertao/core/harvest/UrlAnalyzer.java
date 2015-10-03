/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.geosertao.geosertao.core.harvest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UrlAnalyzer {

    public static int classify(String url) {
        try {
            return classify(new URL(url));
        } catch (MalformedURLException e) {
            return ResourceType.WEB_PAGE;
        }

    }

    public static int classify(URL url) {
        String urlSt = url.getPath() + "/" + url.getQuery();
        String serverUrl = urlSt.toLowerCase();

        int index = serverUrl.indexOf("getmap");
        if (index != -1) {
            return ResourceType.WMS_FEATURE_TYPE;
        }

        index = serverUrl.indexOf("getfeature");
        if (index != -1) {
            return ResourceType.WFS_FEATURE_TYPE;
        }

        index = serverUrl.indexOf("getcoverage");
        if (index != -1) {
            return ResourceType.WCS_COVERAGE;
        }

        index = serverUrl.indexOf("getrecordbyid");
        if (index != -1) {
            return ResourceType.CATALOG_SERVICE_RECORD;
        }

        index = serverUrl.indexOf("wms");
        if (index != -1) {
            return ResourceType.WEB_MAP_SERVICE;
        }

        index = serverUrl.indexOf("wfs");
        if (index != -1) {
            return ResourceType.WEB_FEATURE_SERVICE;
        }

        index = serverUrl.indexOf("wms");
        if (index != -1) {
            return ResourceType.WEB_MAP_SERVICE;
        }

        index = serverUrl.indexOf("wcs");
        if (index != -1) {
            return ResourceType.WEB_COVERAGE_SERVICE;
        }

        index = serverUrl.indexOf("csw");
        if (index != -1) {
            return ResourceType.CATALOG_SERVICE;
        }

        index = serverUrl.indexOf("mapserver");
        if (index != -1) {
            int layerIndex = url.toString().toLowerCase().indexOf("getmap");
            if (layerIndex == -1) {
                return ResourceType.WEB_MAP_SERVICE;
            } else {
                return ResourceType.WMS_FEATURE_TYPE;
            }
        }
        index = serverUrl.indexOf("geoserver");
        if (index != -1) {
            int layerIndex = url.toString().toLowerCase().indexOf("getfeature");
            if (layerIndex == -1) {
                return ResourceType.WEB_FEATURE_SERVICE;
            } else {
                return ResourceType.WFS_FEATURE_TYPE;
            }
        }

        index = serverUrl.indexOf("ows");
        if (index != -1) {
            return ResourceType.WEB_MAP_SERVICE;

        }
        return ResourceType.WEB_PAGE;

    }

    public static List<String> findServiceUrl(String url) {
        List<String> result = new ArrayList();
        try {
            int index = url.toLowerCase().indexOf("geoserver");
            if (index != -1) {
                System.out.println("Base:" + url.substring(0, index + 10));
                String wmsUrl = url.substring(0, index + 10) + "/wms";
                String wfsUrl = url.substring(0, index + 10) + "/wfs";
                result.add(wmsUrl);
                result.add(wfsUrl);
            }
            if (url.endsWith("mapserver")) {
                String wmsUrl = url + "";
            }

        } catch (Exception e) {
        }
        return result;

    }

    public static String getTypeAsString(String url) {
        int type = UrlAnalyzer.classify(url);
        return getTypeAsString(type);
    }

    public static String getTypeAsString(int type) {
        return ResourceType.getResourceName(type);
    }

    public static String getServiceUrl(String url) {
        int index = url.toLowerCase().indexOf("service=wms");
        if (index != -1) {
            return url.substring(0, index);
        }
        index = url.toLowerCase().indexOf("service=wfs");
        if (index != -1) {
            return url.substring(0, index);
        }
        index = url.toLowerCase().indexOf("request=getcapabilities");
        if (index != -1) {
            return url.substring(0, index);
        }
        index = url.toLowerCase().indexOf("version");
        if (index != -1) {
            return url.substring(0, index);
        }
        index = url.toLowerCase().indexOf("request=getmap");
        if (index != -1) {
            return url.substring(0, index);
        }
        if (url.lastIndexOf("&") == url.length() - 1) {
            return url.substring(0, url.length() - 1);
        }
        if (url.lastIndexOf("?") == url.length() - 1) {
            return url.substring(0, url.length() - 1);
        }

        return url;
    }

    public static void main(String[] args) {
        String url = "http://176.34.130.8/arcgis/services/InspireServices/DoEInspirePSGeological/MapServer/InspireFeatureDownloadService?request=getCapabilities&";
        String result = getServiceUrl(url);
        System.out.println("URL -> " + url);
        System.out.println("Result -> " + result);
    }

}
