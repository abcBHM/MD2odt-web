package cz.zcu.kiv.md2odt.web.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Data object for request.
 *
 * @author Patrik Harag
 * @version 2017-05-03
 */
public class Request {

    private MultipartFile input;
    private MultipartFile template;
    private String encoding;

    public MultipartFile getInput() {
        return input;
    }

    public void setInput(MultipartFile input) {
        this.input = input;
    }

    public MultipartFile getTemplate() {
        return template;
    }

    public void setTemplate(MultipartFile template) {
        this.template = template;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
