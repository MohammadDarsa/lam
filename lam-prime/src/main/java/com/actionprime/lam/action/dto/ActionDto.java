package com.actionprime.lam.action.dto;

import com.actionprime.lam.action.type.Visibility;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.actionprime.lam.action.entity.Action}
 */
public record ActionDto(Long id, String name, String description, Visibility visibility,
                        Set<VariableDto> variables) implements Serializable {
}