package com.actionprime.lam.action.repository;

import com.actionprime.lam.action.entity.Variable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableRepository extends JpaRepository<Variable, Long> {
}