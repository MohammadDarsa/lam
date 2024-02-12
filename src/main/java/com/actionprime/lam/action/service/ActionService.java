package com.actionprime.lam.action.service;

import com.actionprime.lam.action.dto.ActionDto;

import java.util.List;

public interface ActionService {
    List<ActionDto> getAllActions();
    ActionDto getActionByName(String name);
    ActionDto getActionById(long id);
    ActionDto updateAction(ActionDto request);
    ActionDto createAction(ActionDto request);
    void deleteAction(long id);

}
