package com.actionprime.lam.action.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.actionprime.lam.action.entity.Variable}
 */
public record VariableDto(Long id, String name, String defaultValue, String description,
                          String modifier) implements Serializable {
}