package com.actionprime.lam.action.repository;

import com.actionprime.lam.action.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long> {
    Optional<Action> findByName(String name);
}