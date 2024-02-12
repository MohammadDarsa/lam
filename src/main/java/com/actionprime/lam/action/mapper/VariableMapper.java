package com.actionprime.lam.action.mapper;

import com.actionprime.lam.action.dto.VariableDto;
import com.actionprime.lam.action.entity.Variable;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VariableMapper {
    Variable toEntity(VariableDto variableDto);

    VariableDto toDto(Variable variable);
}