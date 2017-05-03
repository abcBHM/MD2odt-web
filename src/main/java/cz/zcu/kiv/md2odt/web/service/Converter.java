package cz.zcu.kiv.md2odt.web.service;

import cz.zcu.kiv.md2odt.web.dto.Request;
import java.io.OutputStream;

/**
 * Interface for Markdown to ODT converter.
 *
 * @version 2017-04-15
 * @author Patrik Harag
 */
public interface Converter {

    /**
     * Converts Markdown to ODT.
     *
     * @param request request
     * @param out output
     */
    void convert(Request request, OutputStream out) throws Exception;

}
