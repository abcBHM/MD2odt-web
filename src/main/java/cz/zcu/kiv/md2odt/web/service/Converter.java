package cz.zcu.kiv.md2odt.web.service;

import cz.zcu.kiv.md2odt.web.dto.UploadForm;

import java.io.OutputStream;

/**
 *
 * @version 2017-04-15
 * @author Patrik Harag
 */
public interface Converter {

    void convert(UploadForm form, OutputStream out) throws Exception;

}
