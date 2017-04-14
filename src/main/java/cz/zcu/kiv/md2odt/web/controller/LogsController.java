package cz.zcu.kiv.md2odt.web.controller;

import cz.zcu.kiv.md2odt.web.dto.Log;
import cz.zcu.kiv.md2odt.web.service.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @version 2017-04-14
 * @author Patrik Harag
 */
@Controller
public class LogsController {

    @Autowired
    private LogStorage logStorage;

    @RequestMapping(value = {"/logs"}, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List<Log> showAll(Model model) {
        return logStorage.getAll();
    }

}
