package cz.zcu.kiv.md2odt.web.controller;

import cz.zcu.kiv.md2odt.web.dto.UploadForm;
import cz.zcu.kiv.md2odt.web.service.ConverterService;
import cz.zcu.kiv.md2odt.web.service.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @version 2017-04-14
 * @author Patrik Harag
 */
@RestController
@RequestMapping("/convert")
public class ConverterController {

    private static final String CONTENT_DISPOSITION = "inline;filename=document.odt";
    private static final String CONTENT_TYPE = "application/vnd.oasis.opendocument.text";

    @Autowired
    private ConverterService converter;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadHandler(@ModelAttribute UploadForm form, HttpServletResponse response)
            throws IOException, ConverterException {

        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        try {
            converter.convert(form, out);
        } catch (Throwable e) {
            throw new ConverterException(e);
        }

        response.addHeader("Content-disposition", CONTENT_DISPOSITION);
        response.setContentType(CONTENT_TYPE);

        out.writeTo(response.getOutputStream());
        response.flushBuffer();
    }

}
