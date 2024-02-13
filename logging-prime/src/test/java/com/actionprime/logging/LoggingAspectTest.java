package com.actionprime.logging;

import com.actionprime.logging.app.DummyApplication;
import com.actionprime.logging.config.LoggingAspectConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest(classes = {DummyApplication.class, DummyApplication.DummyController.class, DummyApplication.DummyService.class, DummyApplication.DummyRepository.class, DummyApplication.DummyControllerAdvice.class, LoggingAspectConfig.class})
@AutoConfigureMockMvc
public class LoggingAspectTest {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_controller_aop_logging_message(CapturedOutput output) throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/dummy")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        assertThat(output.getOut().contains("DummyApplication$DummyController : Enter: hello() with argument[s] = []")).isTrue();
        assertThat(output.getOut().contains("DummyApplication$DummyController : Exit: hello() with result = Hello World")).isTrue();
    }

    @Test
    public void test_exception_aop_logging_message(CapturedOutput output) throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/dummy/exception")
        ).andExpect(
                MockMvcResultMatchers.status().isInternalServerError()
        );

        assertThat(output.getOut().contains("DummyApplication$DummyController : Enter: exception() with argument[s] = []")).isTrue();
        assertThat(output.getOut().contains("DummyApplication$DummyController : Exception in exception() with cause = 'NULL' and exception = 'exception thrown'")).isTrue();
    }

    @Test
    public void test_service_aop_logging_message(CapturedOutput output) throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/dummy")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        assertThat(output.getOut().contains("DummyApplication$DummyService  : Enter: hello() with argument[s] = []")).isTrue();
        assertThat(output.getOut().contains("DummyApplication$DummyService  : Exit: hello() with result = Hello World")).isTrue();
    }

    @Test
    public void test_repository_aop_logging_message(CapturedOutput output) throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/dummy")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        assertThat(output.getOut().contains("DummyApplication$DummyRepository : Enter: hello() with argument[s] = []")).isTrue();
        assertThat(output.getOut().contains("DummyApplication$DummyRepository : Exit: hello() with result = Hello World")).isTrue();
    }
}
