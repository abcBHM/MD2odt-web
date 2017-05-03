package cz.zcu.kiv.md2odt.web.service;

/**
 * Exception that was caused by wrong input.
 *
 * @author Patrik Harag
 * @version 2017-04-14
 */
public class StupidClientException extends RuntimeException {

    public StupidClientException(String message) {
        super(message, null);
    }

}
