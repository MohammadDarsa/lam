package com.actionprime.lam.action.entity;

import com.actionprime.lam.audit.entity.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Variables.
 *
 * @author ActionPrime Team
 */
@Getter
@Setter
@Entity
@Table(name = "variable")
public class Variable extends Auditable {

    /**
     * The name of the variable.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The default value of the variable.
     */
    @Column(name = "default_value")
    private String defaultValue;

    /**
     * A description of the variable.
     */
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Any modifiers for the variable.
     */
    @Lob
    @Column(name = "modifier")
    private String modifier;

    /**
     * The action that this variable belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;
}