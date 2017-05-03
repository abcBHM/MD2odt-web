package cz.zcu.kiv.md2odt.web.controller;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;
import cz.zcu.kiv.md2odt.web.dto.LogEntryNew;
import cz.zcu.kiv.md2odt.web.dto.Request;
import cz.zcu.kiv.md2odt.web.service.Converter;
import cz.zcu.kiv.md2odt.web.service.ConverterException;
import cz.zcu.kiv.md2odt.web.service.LogCollector;
import cz.zcu.kiv.md2odt.web.service.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Converter controller.
 *
 * @version 2017-05-03
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

    @Autowired
    private LogStorage storage;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void uploadHandler(@ModelAttribute Request request, HttpServletResponse response)
            throws IOException, ConverterException {

        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        LogEntryNew log = logging.collectLogs(
                () -> converter.convert(request, out)
        );

        save(log);

        if (log.getExceptionObj() != null)
            throw new ConverterException(log.getExceptionObj());

        response.addHeader("Content-disposition", CONTENT_DISPOSITION);
        response.setContentType(CONTENT_TYPE);

        out.writeTo(response.getOutputStream());
        response.flushBuffer();
    }

    private void save(LogEntry log) {
        Thread thread = new Thread(() -> {
            storage.add(log);
        }, "Log saving thread");

        thread.start();
    }

}
