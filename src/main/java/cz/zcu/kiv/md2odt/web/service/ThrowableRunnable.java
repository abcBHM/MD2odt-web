package cz.zcu.kiv.md2odt.web.service;

/**
 * Equivalent to {@link java.lang.Runnable}.
 *
 * @author Patrik Harag
 * @version 2017-04-14
 */
@FunctionalInterface
public interface ThrowableRunnable {

    void run() throws Exception;

}
