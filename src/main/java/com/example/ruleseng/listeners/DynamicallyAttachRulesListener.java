package com.example.ruleseng.listeners;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.models.Context;
import com.example.ruleseng.repositories.CustomRuleRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicallyAttachRulesListener implements RulesEngineListener {

    private final RuleConverter ruleConverter;
    private final ObjectMapper mapper;
    private final String ADDITIONAL_RULES = "additional_rules";

    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {

        log.info("registering dynamic rules before evaluation");

        if (!Objects.isNull(facts.get(ADDITIONAL_RULES))) {

            List<CustomRule> additionalRules = mapper.convertValue(facts.get(ADDITIONAL_RULES), ArrayList.class);
            for (Object rule: additionalRules) {
                CustomRule customRule = mapper.convertValue(rule, CustomRule.class);
                rules.register(ruleConverter.convertToRule(customRule));
            }
        }
    }

    @Override
    public void afterExecute(Rules rules, Facts facts) {
        log.info("de-registering dynamic rules before evaluation");

        if (!Objects.isNull(facts.get(ADDITIONAL_RULES))) {

            List<CustomRule> additionalRules = mapper.convertValue(facts.get(ADDITIONAL_RULES), ArrayList.class);
            for (Object rule : additionalRules) {
                CustomRule customRule = mapper.convertValue(rule, CustomRule.class);
                rules.unregister(customRule.getName());
            }
        }
    }
}
