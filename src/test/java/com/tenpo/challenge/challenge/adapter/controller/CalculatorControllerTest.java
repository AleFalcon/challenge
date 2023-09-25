package com.tenpo.challenge.challenge.adapter.controller;

import com.tenpo.challenge.challenge.adapter.controller.CalculatorController;
import com.tenpo.challenge.challenge.application.port.in.GetCalculationPort;
import com.tenpo.challenge.challenge.application.port.in.GetLogPort;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.LogDomain;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetCalculationPort getCalculationPort;
    @MockBean
    private GetLogPort getLogPort;
    private MockFactoryDomain mockFactoryDomain;
    private static final String URI = "/api/v1/calculator";
    private static final Double FIRST_NUMBER = 5.1;
    private static final Double SECOND_NUMBER = 3.1;

    @BeforeEach
    void setUp() {
        mockFactoryDomain = new MockFactoryDomain();
    }

    @DisplayName("When receive a request, get a percentage and calculate a operation, and return a result")
    @Test
    void testGetResultSuccess() throws Exception {

        CalculationResultDomain calculationResultDomain = mockFactoryDomain.getCalculationResultDomain();
        when(getCalculationPort.execute(FIRST_NUMBER, SECOND_NUMBER)).thenReturn(calculationResultDomain);

        mockMvc.perform(
                        get(URI)
                                .param("number1", FIRST_NUMBER.toString())
                                .param("number2", SECOND_NUMBER.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.jsonPath("$.status").value(200),
                        MockMvcResultMatchers.jsonPath("$.message").value("OK"),
                        MockMvcResultMatchers.jsonPath("$.data.percentage").value(calculationResultDomain.getPercentage()),
                        MockMvcResultMatchers.jsonPath("$.data.result").value(calculationResultDomain.getResult())
                );
    }

    @DisplayName("When receive a request but this not contain all params, response an error")
    @Test
    void testGetResultBadRequest() throws Exception {
        mockMvc.perform(
                        get(URI)
                                .param("number1", FIRST_NUMBER.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.jsonPath("$.status").value(400),
                        MockMvcResultMatchers.jsonPath("$.message").value("Required request parameter: number2"),
                        MockMvcResultMatchers.jsonPath("$.timestamp").isNotEmpty()
                );

        mockMvc.perform(
                        get(URI)
                                .param("number2", SECOND_NUMBER.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.jsonPath("$.status").value(400),
                        MockMvcResultMatchers.jsonPath("$.message").value("Required request parameter: number1"),
                        MockMvcResultMatchers.jsonPath("$.timestamp").isNotEmpty()
                );
    }

    @DisplayName("When receive a request but one parameter invalid, response an error")
    @Test
    void testGetResultWithParamInvalidBadRequest() throws Exception {
        String number2 = "unString";
        mockMvc.perform(
                        get(URI)
                                .param("number1", FIRST_NUMBER.toString())
                                .param("number2", number2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.jsonPath("$.status").value(400),
                        MockMvcResultMatchers.jsonPath("$.message").value("Invalid number format: " + number2),
                        MockMvcResultMatchers.jsonPath("$.timestamp").isNotEmpty()
                );
    }

    @DisplayName("When receive a request for view logs, and return a list logs")
    @Test
    void testGetLogsSuccess() throws Exception {
        Page<LogDomain> logsDomain = mockFactoryDomain.getPageLogDomain();
        when(getLogPort.execute(PageRequest.of(1, 1))).thenReturn(logsDomain);
        mockMvc.perform(
                        get(URI.concat("/logs"))
                                .param("page", "1")
                                .param("size","1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.jsonPath("$.status").value(200),
                        MockMvcResultMatchers.jsonPath("$.message").value("OK"),
                        MockMvcResultMatchers.jsonPath("$.data.content").isNotEmpty()
                );
    }
}
