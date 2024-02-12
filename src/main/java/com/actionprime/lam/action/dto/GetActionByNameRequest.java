package com.actionprime.lam.action.dto;

import java.io.Serializable;

public record GetActionByNameRequest(String name) implements Serializable {
}
