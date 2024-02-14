package com.actionprime.lam.action.service;

import com.actionprime.lam.action.dto.ActionDto;

import java.util.List;

/**
 * This interface defines the methods for interacting with the Action table in the database.
 */
public interface ActionService {

    /**
     * Returns a list of all actions in the database.
     *
     * @return a list of actions
     */
    List<ActionDto> getAllActions();

    /**
     * Returns an action by its name.
     *
     * @param name the name of the action
     * @return the action with the given name
     */
    ActionDto getActionByName(String name);

    /**
     * Returns an action by its ID.
     *
     * @param id the ID of the action
     * @return the action with the given ID
     */
    ActionDto getActionById(long id);

    /**
     * Updates an action in the database.
     *
     * @param request the updated action
     * @return the updated action
     */
    ActionDto updateAction(ActionDto request);

    /**
     * Creates a new action in the database.
     *
     * @param request the new action
     * @return the new action
     */
    ActionDto createAction(ActionDto request);

    /**
     * Deletes an action from the database.
     *
     * @param id the ID of the action
     */
    void deleteAction(long id);

}
