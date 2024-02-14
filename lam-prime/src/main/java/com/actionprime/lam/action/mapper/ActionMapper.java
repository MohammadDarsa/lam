package com.actionprime.lam.action.mapper;

import com.actionprime.lam.action.dto.ActionDto;
import com.actionprime.lam.action.entity.Action;
import org.mapstruct.*;

/**
 * Maps between Action and ActionDto objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = VariableMapper.class, componentModel = "spring")
public interface ActionMapper {

    /**
     * Maps an ActionDto to an Action object.
     *
     * @param actionDto the ActionDto to map
     * @return the mapped Action object
     */
    Action toEntity(ActionDto actionDto);

    /**
     * Links the variables of an Action object to the Action object after mapping.
     *
     * @param action the Action object to link the variables to
     */
    @AfterMapping
    default void linkVariables(@MappingTarget Action action) {
        action.getVariables().forEach(variable -> variable.setAction(action));
    }

    /**
     * Maps an Action object to an ActionDto object.
     *
     * @param action the Action object to map
     * @return the mapped ActionDto object
     */
    ActionDto toDto(Action action);

}