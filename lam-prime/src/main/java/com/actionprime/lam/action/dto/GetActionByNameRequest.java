package com.actionprime.lam.action.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.actionprime.lam.action.entity.Action}
 */
public record GetActionByNameRequest(String name) implements Serializable {
}
