package com.actionprime.lam.action.mapper;

import com.actionprime.lam.action.dto.ActionDto;
import com.actionprime.lam.action.entity.Action;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = VariableMapper.class, componentModel = "spring")
public interface ActionMapper {
    Action toEntity(ActionDto actionDto);

    @AfterMapping
    default void linkVariables(@MappingTarget Action action) {
        action.getVariables().forEach(variable -> variable.setAction(action));
    }


    ActionDto toDto(Action action);
}