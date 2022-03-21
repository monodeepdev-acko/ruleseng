package com.example.ruleseng.services;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.repositories.CustomRuleRepository;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RulesService {

    private final RuleConverter ruleConverter;
    private final Rules rules;
    private final CustomRuleRepository ruleRepository;

    public Iterable<CustomRule> findAll() {
        return ruleRepository.findAll();
    }

    public Iterable<CustomRule> findByChannel(String channel) {
        return ruleRepository.findByChannel(channel);
    }

    public CustomRule findById(String name) {
        Optional<CustomRule> maybeRule = ruleRepository.findById(name);
        return maybeRule.orElse(null);
    }

    public void registerChannelRules(String channel) {
        for (CustomRule customRule : findByChannel(channel)) {
            rules.register(ruleConverter.convertToRule(customRule));
        }
    }

    public void unregisterChannelRules(String channel) {
        for (CustomRule customRule : findByChannel(channel)) {
            rules.unregister(customRule.getName());
        }
    }

    public void registerNewRules(List<Rule> ruleList) {
        for (Rule rule : ruleList) {
            rules.register(rule);
        }
    }

    public CustomRule save(CustomRule rule) {

        try {
            ruleRepository.save(rule);
            rules.register(ruleConverter.convertToRule(rule));
        } catch (Exception e) {
            throw new IllegalArgumentException("Name should be unique: " + rule);
        }
        return rule;
    }

    public void delete(String name) {
        ruleRepository.deleteById(name);
    }
}
