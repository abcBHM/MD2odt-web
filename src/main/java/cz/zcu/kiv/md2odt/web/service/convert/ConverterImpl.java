package cz.zcu.kiv.md2odt.web.service.convert;

import cz.zcu.kiv.md2odt.Converter;
import cz.zcu.kiv.md2odt.MD2odt;
import cz.zcu.kiv.md2odt.web.config.ConverterConfig;
import cz.zcu.kiv.md2odt.web.config.FileUploadConfig;
import cz.zcu.kiv.md2odt.web.dto.Request;
import cz.zcu.kiv.md2odt.web.service.StupidClientException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Converter implementation.
 *
 * @version 2017-04-21
 * @author Patrik Harag
 */
@Service
public class ConverterImpl implements cz.zcu.kiv.md2odt.web.service.Converter {

    private static final Logger LOGGER = Logger.getLogger(ConverterImpl.class);

    private static final int PATTERN_FLAGS = Pattern.UNICODE_CHARACTER_CLASS;
    static final Pattern ZIP_PATTERN = Pattern.compile("(?i).+\\.(zip|jar)", PATTERN_FLAGS);
    static final Pattern MD_PATTERN = Pattern.compile("(?i).+\\.(md|markdown|txt)", PATTERN_FLAGS);
    static final Pattern TEMPLATE_PATTERN = Pattern.compile("(?i)(^[^.]+|.+\\.(odt|ott))", PATTERN_FLAGS);

    @Autowired
    private Predicate<URL> resourceFilter;

    @Override
    public void convert(Request request, OutputStream out) throws Exception {
        Converter converter = MD2odt.converter();

        initInput(request, converter);
        initTemplate(request, converter);

        converter
                .setOutput(out)
                .setResourcesLimit(ConverterConfig.RESOURCES_LIMIT)
                .setResourcesPolicy(resourceFilter)
                .enableAllExtensions();

        LOGGER.info("Converting started");
        converter.convert();
        LOGGER.info("Converting finished");
    }

    private void initInput(Request request, Converter converter) throws IOException {
        MultipartFile input = request.getInput();

        if (input.isEmpty())
            throw new StupidClientException("No input file!");

        if (input.getSize() > FileUploadConfig.MAX_UPLOAD_SIZE)
            throw new StupidClientException("Input is too big!");

        String filename = input.getOriginalFilename();
        if (MD_PATTERN.matcher(filename).matches()) {
            InputStream inputStream = input.getInputStream();
            converter.setInput(inputStream, charset(request));
            LOGGER.info("Input type: text file");


        } else if (ZIP_PATTERN.matcher(filename).matches()) {
            InputStream inputStream = input.getInputStream();
            converter.setInputZip(inputStream, charset(request));
            LOGGER.info("Input type: zip");

        } else {
            String supported =
                    "Supported:" + "\n" +
                    "• Text file: " + MD_PATTERN.toString() + ",\n" +
                    "• ZIP file:  " + ZIP_PATTERN.toString();

            throw new StupidClientException("Unknown file type!\n\n" + supported);
        }
    }

    private void initTemplate(Request request, Converter converter) throws IOException {
        MultipartFile template = request.getTemplate();

        if (template.getSize() > FileUploadConfig.MAX_UPLOAD_SIZE)
            throw new StupidClientException("Template is too big!");

        if (template.isEmpty()) {
            LOGGER.info("Template not set");

        } else {
            String filename = template.getOriginalFilename();

            if (TEMPLATE_PATTERN.matcher(filename).matches()) {
                converter.setTemplate(template.getInputStream());
                LOGGER.info("Template set");
            } else {
                String supported = "Supported: " + TEMPLATE_PATTERN.toString();
                throw new StupidClientException("Unknown file type!\n\n" + supported);
            }
        }
    }

    private Charset charset(Request request) {
        Charset charset;

        if (request.getEncoding() != null) {
            try {
                charset = Charset.forName(request.getEncoding());

            } catch (Exception e) {
                LOGGER.warn("Unsupported encoding: " + request.getEncoding());
                charset = ConverterConfig.DEFAULT_ENCODING;
            }
        } else {
            LOGGER.warn("Encoding not set");
            charset = ConverterConfig.DEFAULT_ENCODING;
        }

        LOGGER.info("Selected encoding: " + request.getEncoding());

        return charset;
    }

}
