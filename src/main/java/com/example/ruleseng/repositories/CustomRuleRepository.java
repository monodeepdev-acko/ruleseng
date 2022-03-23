package com.example.ruleseng.repositories;

import com.example.ruleseng.entities.CustomRule;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomRuleRepository extends CrudRepository<CustomRule, String> {
    Iterable<CustomRule> findByChannel(String channel);
    Optional<CustomRule> findByName(String name);
}