package com.actionprime.lam.action.controller;

import com.actionprime.lam.action.dto.ActionDto;
import com.actionprime.lam.action.dto.GetActionByNameRequest;
import com.actionprime.lam.action.service.ActionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing actions.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/action")
@Tag(name = "Action API")
public class ActionController {

    private final ActionService actionService;

    /**
     * GET /api/v1/action : get all actions.
     *
     * @return the list of actions
     */
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get all actions", description = "returns a list of all the actions available in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "all actions retrieved")
    })
    List<ActionDto> getAllActions() {
        return actionService.getAllActions();
    }

    /**
     * GET /api/v1/action/{id} : get an action by id.
     *
     * @param id the id of the action to retrieve
     * @return the action with the given id or 404 if not found
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get action by id", description = "returns an action with the id provided from the database if found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "action successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "action not found"),
    })
    ActionDto getActionById(@PathVariable Long id) {
        return actionService.getActionById(id);
    }

    /**
     * POST /api/v1/action/name : get an action by name.
     *
     * @param request the request containing the name of the action to retrieve
     * @return the action with the given name or 404 if not found
     */
    @PostMapping("/name")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get action by name", description = "returns an action with the name provided from the database if found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "action successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "action not found"),
    })
    ActionDto getActionByName(@RequestBody GetActionByNameRequest request) {
        return actionService.getActionByName(request.name());
    }

    /**
     * POST /api/v1/action : create an action.
     *
     * @param actionDto the action to create
     * @return the created action
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create action", description = "creates an action in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "action successfully created"),
            @ApiResponse(responseCode = "400", description = "action already exists"),
    })
    ActionDto createAction(@RequestBody ActionDto actionDto) {
        return actionService.createAction(actionDto);
    }

    /**
     * PUT /api/v1/action : update an action.
     *
     * @param actionDto the action to update
     * @return the updated action
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update action", description = "updates an action in the database, the id must be provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "action successfully updated"),
            @ApiResponse(responseCode = "400", description = "action already exists"),
    })
    ActionDto updateAction(@RequestBody ActionDto actionDto) {
        return actionService.updateAction(actionDto);
    }

    /**
     * DELETE /api/v1/action/{id} : delete an action.
     *
     * @param id the id of the action to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete action", description = "deletes an action in the database by providing its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "action successfully deleted"),
    })
    void deleteAction(@PathVariable("id") long id) {
        actionService.deleteAction(id);
    }
}
