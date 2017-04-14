package cz.zcu.kiv.md2odt.web.service;

import cz.zcu.kiv.md2odt.Converter;
import cz.zcu.kiv.md2odt.MD2odt;
import cz.zcu.kiv.md2odt.web.config.FileUploadConfig;
import cz.zcu.kiv.md2odt.web.dto.UploadForm;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 *
 * @version 2017-04-14
 * @author Patrik Harag
 */
@Service
public class ConverterService {

    private static final Logger LOGGER = Logger.getLogger(ConverterService.class);

    static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

    private static final int PATTERN_FLAGS = Pattern.UNICODE_CHARACTER_CLASS;
    static final Pattern ZIP_PATTERN = Pattern.compile("(?i).+\\.(zip|jar)", PATTERN_FLAGS);
    static final Pattern MD_PATTERN = Pattern.compile("(?i).+\\.(md|markdown|txt)", PATTERN_FLAGS);

    public void convert(UploadForm form, OutputStream out) throws Exception {
        Converter converter = MD2odt.converter();

        initInput(form, converter);
        initTemplate(form, converter);

        converter
                .setOutput(out)
                .enableAllExtensions();

        LOGGER.info("Converting started");
        converter.convert();
        LOGGER.info("Converting finished");
    }

    private void initInput(UploadForm form, Converter converter) throws IOException {
        MultipartFile input = form.getInput();

        if (input.isEmpty())
            throw new StupidClientException("No input file!");

        if (input.getSize() > FileUploadConfig.MAX_UPLOAD_SIZE)
            throw new StupidClientException("Input is too big!");

        String filename = input.getOriginalFilename();
        if (MD_PATTERN.matcher(filename).matches()) {
            InputStream inputStream = input.getInputStream();
            converter.setInputStream(inputStream, charset(form));
            LOGGER.info("Input type: text file");


        } else if (ZIP_PATTERN.matcher(filename).matches()) {
            InputStream inputStream = input.getInputStream();
            converter.setInputZip(inputStream, charset(form));
            LOGGER.info("Input type: zip");

        } else {
            String supported =
                    "Supported:" + "\n" +
                    "• Text file: " + MD_PATTERN.toString() + ",\n" +
                    "• ZIP file:  " + ZIP_PATTERN.toString();

            throw new StupidClientException("Unknown file type!\n\n" + supported);
        }
    }

    private void initTemplate(UploadForm form, Converter converter) throws IOException {
        MultipartFile template = form.getTemplate();

        if (template.getSize() > FileUploadConfig.MAX_UPLOAD_SIZE)
            throw new StupidClientException("Template is too big!");

        if (template.isEmpty()) {
            LOGGER.info("Template not set");
        } else {
            converter.setTemplate(template.getInputStream());
        }
    }

    private Charset charset(UploadForm form) {
        Charset charset;

        if (form.getEncoding() != null) {
            try {
                charset = Charset.forName(form.getEncoding());

            } catch (Exception e) {
                LOGGER.warn("Unsupported encoding: " + form.getEncoding());
                charset = DEFAULT_ENCODING;
            }
        } else {
            LOGGER.warn("Encoding not set");
            charset = DEFAULT_ENCODING;
        }

        LOGGER.info("Selected encoding: " + form.getEncoding());

        return charset;
    }

}
