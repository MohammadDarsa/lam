package com.actionprime.lam.action.mapper;

import com.actionprime.lam.action.dto.VariableDto;
import com.actionprime.lam.action.entity.Variable;
import org.mapstruct.*;

/**
 * A mapper for converting between {@link VariableDto} and {@link Variable} objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VariableMapper {

    /**
     * Maps a {@link VariableDto} to a {@link Variable} object.
     *
     * @param variableDto the variable DTO
     * @return the variable entity
     */
    Variable toEntity(VariableDto variableDto);

    /**
     * Maps a {@link Variable} object to a {@link VariableDto} object.
     *
     * @param variable the variable entity
     * @return the variable DTO
     */
    VariableDto toDto(Variable variable);

}