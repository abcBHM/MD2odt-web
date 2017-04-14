package cz.zcu.kiv.md2odt.web.controller;

import cz.zcu.kiv.md2odt.web.dto.UploadForm;
import cz.zcu.kiv.md2odt.web.service.Converter;
import cz.zcu.kiv.md2odt.web.service.ConverterException;
import cz.zcu.kiv.md2odt.web.service.LogCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @version 2017-04-15
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/convert")
public class ConverterController {

    private static final String CONTENT_DISPOSITION = "inline;filename=document.odt";
    private static final String CONTENT_TYPE = "application/vnd.oasis.opendocument.text";

    @Autowired
    private Converter converter;

    @Autowired
    private LogCollector logging;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void uploadHandler(@ModelAttribute UploadForm form, HttpServletResponse response)
            throws IOException, ConverterException {

        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        try {
            logging.collectLogs(() -> converter.convert(form, out));
        } catch (Throwable e) {
            throw new ConverterException(e);
        }

        response.addHeader("Content-disposition", CONTENT_DISPOSITION);
        response.setContentType(CONTENT_TYPE);

        out.writeTo(response.getOutputStream());
        response.flushBuffer();
    }

}
