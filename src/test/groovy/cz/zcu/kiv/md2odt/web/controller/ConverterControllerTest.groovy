package cz.zcu.kiv.md2odt.web.controller

import cz.zcu.kiv.md2odt.web.dto.Request
import org.junit.Test
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 *
 * @version 2017-05-03
 * @author Patrik Harag
 */
class ConverterControllerTest extends HelperAbstractMvcTest {

    ResultActions post(url, obj) {
        Helper.post(mockMvc, url, obj)
    }

    ResultActions get(url) {
        Helper.get(mockMvc, url)
    }


    @Test
    void noInput() {
        def request = new Request(encoding: "UTF-8", input: null, template: null)

        post("/convert/upload", request)
                .andExpect(status().is4xxClientError())
    }

}
