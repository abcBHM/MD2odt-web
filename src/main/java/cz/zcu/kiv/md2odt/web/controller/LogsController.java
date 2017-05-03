package cz.zcu.kiv.md2odt.web.controller;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;
import cz.zcu.kiv.md2odt.web.service.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Logs controller.
 *
 * @version 2017-04-21
 * @author Patrik Harag
 */
@Controller
public class LogsController {

    private static final String JSON = "application/json; charset=UTF-8";

    @Autowired
    private LogStorage logStorage;

    // JSON

    @RequestMapping(value = "/data/logs", produces = JSON)
    @ResponseBody
    public List<LogEntry> logsRaw() {
        return logStorage.getAll();
    }

    @RequestMapping(value = "/data/logs/{id}", produces = JSON)
    @ResponseBody
    public List<LogEntry> logsRaw(@PathVariable int id) {
        // TODO: add getById to LogStorage
        return logStorage.getAll().stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
    }

    // HTML

    @RequestMapping(value = "/logs")
    public String logs(Model model) {
        model.addAttribute("entries", logsRaw());

        return "logs";
    }

    @RequestMapping(value = "/logs/{id}")
    public String logs(Model model, @PathVariable int id) {
        model.addAttribute("entries", logsRaw(id));

        return "logs";
    }

}
