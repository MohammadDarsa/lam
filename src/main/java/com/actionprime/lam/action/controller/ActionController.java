package com.actionprime.lam.action.controller;

import com.actionprime.lam.action.dto.ActionDto;
import com.actionprime.lam.action.dto.GetActionByNameRequest;
import com.actionprime.lam.action.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/action")
public class ActionController {

    private final ActionService actionService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    List<ActionDto> getAllActions() {
        return actionService.getAllActions();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    ActionDto getActionById(@PathVariable Long id) {
        return actionService.getActionById(id);
    }

    @PostMapping("/name")
    @ResponseStatus(HttpStatus.FOUND)
    ActionDto getActionByName(@RequestBody GetActionByNameRequest request) {
        return actionService.getActionByName(request.name());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ActionDto createAction(@RequestBody ActionDto actionDto) {
        return actionService.createAction(actionDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    ActionDto updateAction(@RequestBody ActionDto actionDto) {
        return actionService.updateAction(actionDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAction(@PathVariable("id") long id) {
        actionService.deleteAction(id);
    }
}
