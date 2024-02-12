package com.actionprime.lam.action.service.impl;

import com.actionprime.lam.action.dto.ActionDto;
import com.actionprime.lam.action.exception.ActionNameAlreadyExistsException;
import com.actionprime.lam.action.exception.ActionNotFoundException;
import com.actionprime.lam.action.mapper.ActionMapper;
import com.actionprime.lam.action.repository.ActionRepository;
import com.actionprime.lam.action.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActionServiceImplementation implements ActionService {

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;

    @Override
    public List<ActionDto> getAllActions() {
        return actionRepository.findAll().stream().map(actionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ActionDto getActionByName(String name) {
        return actionMapper.toDto(actionRepository.findByName(name).orElseThrow(() -> new ActionNotFoundException("action of name " + name + " not found.")));
    }

    @Override
    public ActionDto getActionById(long id) {
        return actionMapper.toDto(actionRepository.findById(id).orElseThrow(() -> new ActionNotFoundException("action of id " + id + " not found.")));
    }

    @Override
    public ActionDto updateAction(ActionDto request) {
        actionRepository.findByName(request.name()).ifPresent(action -> { if(!action.getId().equals(request.id())) throw new ActionNameAlreadyExistsException("action with the name " + request.name() + " already exists"); });
        return actionMapper.toDto(actionRepository.save(actionMapper.toEntity(request)));
    }

    @Override
    public ActionDto createAction(ActionDto request) {
        //handle unique action name constraint
        actionRepository.findByName(request.name()).ifPresent(action -> { throw new ActionNameAlreadyExistsException("action with the name " + request.name() + " already exists"); });
        return actionMapper.toDto(actionRepository.saveAndFlush(actionMapper.toEntity(request)));
    }

    @Override
    public void deleteAction(long id) {
        actionRepository.deleteById(id);
    }
}
