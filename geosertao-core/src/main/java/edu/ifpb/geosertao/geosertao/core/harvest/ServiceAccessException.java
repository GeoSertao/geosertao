package edu.ifpb.geosertao.geosertao.core.harvest;

/**
 *
 * @author Fabio
 */
public class ServiceAccessException extends Exception {

    public ServiceAccessException(Exception cause) {
        super(cause);
    }

    public ServiceAccessException(String message) {
        super(message);
    }

}
