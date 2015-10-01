/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.geosertao.geosertao.core.harvest;

import java.io.IOException;
import org.geotoolkit.csw.GetRecordsRequest;

/**
 *
 * @author Fabio
 */
public class ServiceInvoker {

    private GetRecordsRequest request;

    private long maxWaitingTime;

    private int numberOfTries;

    public ServiceInvoker(GetRecordsRequest request, long maxWaitingTime, int numberOfTries) {
        this.request = request;
        this.maxWaitingTime = maxWaitingTime;
        this.numberOfTries = numberOfTries;
    }

    public void executeService() throws IOException {
        int tries = 0;
        try {
            ServiceInvokerThread invokerThread = new ServiceInvokerThread(request);
            Thread thread = new Thread(invokerThread);
            thread.start();
        } catch (Exception e) {
        }

    }

}
