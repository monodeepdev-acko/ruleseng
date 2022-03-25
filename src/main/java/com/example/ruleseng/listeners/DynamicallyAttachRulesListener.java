package com.example.ruleseng.listeners;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.models.Context;
import com.example.ruleseng.repositories.CustomRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicallyAttachRulesListener implements RulesEngineListener {

    private final RuleConverter ruleConverter;

    private final String ADDITIONAL_RULES = "additional_rules";

    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {

        log.info("registering dynamic rules before evaluation");

        List<CustomRule> additionalRules = facts.get(ADDITIONAL_RULES);
        for (CustomRule rule: additionalRules) {
            rules.register(ruleConverter.convertToRule(rule));
        }
    }

    @Override
    public void afterExecute(Rules rules, Facts facts) {
        log.info("de-registering dynamic rules before evaluation");

        if (!Objects.isNull(facts.get(ADDITIONAL_RULES))) {

            List<String> nameList = ((List<CustomRule>) facts.get(ADDITIONAL_RULES))
                    .stream().map(x -> x.getName()).collect(Collectors.toList());

            for (String ruleName: nameList) {
                rules.unregister(ruleName);
            }
        }
    }
}
