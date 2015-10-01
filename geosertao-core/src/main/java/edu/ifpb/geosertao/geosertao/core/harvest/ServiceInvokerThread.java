package edu.ifpb.geosertao.geosertao.core.harvest;

import java.io.IOException;
import org.geotoolkit.csw.GetRecordsRequest;

/**
 *
 * @author Fabio
 */
public class ServiceInvokerThread implements Runnable {

    private GetRecordsRequest request;
    private IOException exception;

    public ServiceInvokerThread(GetRecordsRequest request) {
        this.request = request;
    }

    public synchronized void run() {
        try {
            getRequest().getResponseStream();

        } catch (IOException e) {
            exception = e;
        }
    }

    public GetRecordsRequest getRequest() {
        return request;
    }

    public void setRequest(GetRecordsRequest request) {
        this.request = request;
    }

    public IOException getException() {
        return exception;
    }

}
