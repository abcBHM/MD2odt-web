package cz.zcu.kiv.md2odt.web.controller;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;
import cz.zcu.kiv.md2odt.web.service.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @version 2017-04-16
 * @author Patrik Harag
 */
@Controller
public class LogsController {

    @Autowired
    private LogStorage logStorage;

    @RequestMapping(value = {"/logs-raw"}, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List<LogEntry> logsRaw(Model model) {
        return logStorage.getAll();
    }

    @RequestMapping(value = {"/logs"})
    public String logs(Model model) {
        model.addAttribute("entries", logStorage.getAll());

        return "logs";
    }

}
