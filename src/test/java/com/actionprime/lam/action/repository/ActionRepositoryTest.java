package com.actionprime.lam.action.repository;

import com.actionprime.lam.action.entity.Action;
import com.actionprime.lam.action.entity.Variable;
import com.actionprime.lam.action.type.Visibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ActionRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    ActionRepository actionRepository;

    @BeforeEach
    void setup() {
        Variable variable = new Variable().setName("test name").setDescription("test description").setModifier("test modifier").setDefaultValue("test default value");
        Action action = new Action().setName("test name").setDescription("test description").setVisibility(Visibility.PUBLIC).setVariables(Set.of(variable));
        actionRepository.save(action);
    }

    @Test
    void check_if_action_is_present_and_has_a_variable() {
        Action action = actionRepository.findByName("test name").orElseThrow();
        assertThat(action.getName()).isEqualTo("test name");
        assertThat(action.getCreatedDate()).isNotNull();
        assertThat(action.getLastModifiedDate()).isNotNull();
        assertThat(action.getVariables().size()).isEqualTo(1);
    }
}
