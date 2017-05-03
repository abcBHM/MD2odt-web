package cz.zcu.kiv.md2odt.web.controller

import cz.zcu.kiv.md2odt.web.dto.Request
import org.junit.Test
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 *
 * @version 2017-05-03
 * @author Patrik Harag
 */
class LogControllerTest extends HelperAbstractMvcTest {

    ResultActions post(url, obj) {
        Helper.post(mockMvc, url, obj)
    }

    ResultActions get(url) {
        Helper.get(mockMvc, url)
    }


    @Test
    void empty() {
        get("/data/logs")
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$").isArray())
    }

    @Test
    void logs() {
        post("/convert/upload", new Request(encoding: "UTF-8", input: null, template: null))

        get("/data/logs")
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$").isArray())
                .andExpect(jsonPath("\$[0]").exists())
    }

}
