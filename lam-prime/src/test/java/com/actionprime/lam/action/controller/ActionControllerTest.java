package com.actionprime.lam.action.controller;

import com.actionprime.lam.action.dto.ActionDto;
import com.actionprime.lam.action.dto.GetActionByNameRequest;
import com.actionprime.lam.action.dto.VariableDto;
import com.actionprime.lam.action.entity.Action;
import com.actionprime.lam.action.entity.Variable;
import com.actionprime.lam.action.repository.ActionRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.Set;

import static com.actionprime.lam.action.type.Visibility.PUBLIC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Full integration tests for the action controller
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class ActionControllerTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    static final ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ActionRepository actionRepository;

    @BeforeAll
    public static void beforeAll() {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
        actionRepository.deleteAll();
    }

    @Test
    void get_all_added_actions() throws Exception {
        Action action = saveAction();

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/action")
                                .contentType("application/json")
                                .accept("application/json"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].id").value(action.getId()));
    }

    @Test
    void get_action_by_id() throws Exception {
        long id = saveAction().getId();

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/action/" + id)
                                .contentType("application/json")
                                .accept("application/json"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void get_action_by_id_not_found() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/action/0")
                                .contentType("application/json")
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    void get_action_by_name() throws Exception {
        Action action = saveAction();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/action/name")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(new GetActionByNameRequest(action.getName())))
                                .accept("application/json"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(action.getId()));
    }

    @Test
    void get_action_by_name_not_found() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/action/name")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(new GetActionByNameRequest("any")))
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    void add_an_action_with_variables() throws Exception {
        ActionDto actionDto = getDummyActionDto();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/action")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(actionDto))
                                .accept("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.variables").isArray())
                .andExpect(jsonPath("$.variables[0].id").exists());
    }

    @Test
    void add_an_action_name_already_exists() throws Exception {
        saveAction();
        ActionDto actionDto = getDummyActionDto();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/action")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(actionDto))
                                .accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_an_action_name_already_exists() throws Exception {
        Action action = saveAction("test");
        saveAction();
        ActionDto actionDto = getDummyActionDto(action.getId());

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/action")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(actionDto))
                                .accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_an_action() throws Exception {
        Action action = saveAction("test");
        ActionDto actionDto = getDummyActionDto(action.getId());

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/action")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(actionDto))
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("book flight"));
    }

    @Test
    void delete_an_action() throws Exception {
        Action action = saveAction();

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/action/" + action.getId())
                                .contentType("application/json")
                                .accept("application/json"))
                .andExpect(status().isNoContent());
        Optional<Action> optionalAction = actionRepository.findById(action.getId());
        assertThat(optionalAction.isEmpty()).isTrue();
    }

    ActionDto getDummyActionDto() {
        VariableDto variableDto = new VariableDto(null, "price", "0.0", "price of the flight", "convert to a decimal number if its a string");
        return new ActionDto(null, "book flight", "books a flight", PUBLIC, Set.of(variableDto));
    }

    ActionDto getDummyActionDto(long id) {
        VariableDto variableDto = new VariableDto(id, "price", "0.0", "price of the flight", "convert to a decimal number if its a string");
        return new ActionDto(id, "book flight", "books a flight", PUBLIC, Set.of(variableDto));
    }

    Action getDummyAction(String name) {
        Variable variable = new Variable().setName("price").setDefaultValue("0.0").setDescription("price of the flight").setModifier("convert to a decimal number if its a string");
        return new Action().setName(name).setDescription("books a flight").setVisibility(PUBLIC).setVariables(Set.of(variable));
    }

    Action saveAction() {
        return actionRepository.save(getDummyAction("book flight"));
    }

    Action saveAction(String name) {
        return actionRepository.save(getDummyAction(name));
    }
}
